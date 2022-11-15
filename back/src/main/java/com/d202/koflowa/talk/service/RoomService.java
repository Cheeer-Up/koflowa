package com.d202.koflowa.talk.service;

import com.d202.koflowa.talk.domain.Room;
import com.d202.koflowa.talk.dto.RoomDto;
import com.d202.koflowa.talk.exception.Room1NoFoundException;
import com.d202.koflowa.talk.exception.Room2NoFoundException;
import com.d202.koflowa.talk.exception.RoomDeleteFailureException;
import com.d202.koflowa.talk.exception.User1NotFoundException;
import com.d202.koflowa.talk.repository.RoomRepository;
import com.d202.koflowa.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    /* 기존에 있는 방을 검색 후 없으면 생성해서 반환 */
    public RoomDto.Response createRoom(RoomDto.RequestCreate roomDto){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        /* 송신자 수신자 양방향 저장 */
        Room room = roomRepository.findSpecificChatRoom(user.getSeq(), roomDto.getReceiver_seq());

        if(room == null){
            room = roomRepository.save(roomDto.toEntity(user.getSeq()));

            if(room == null){
                throw new Room1NoFoundException();
            }

        /* 논리 삭제 되었다면 복구 해주기 */
        }else if(room.getUser1Seq() == user.getSeq() && room.isUser1Delete()){
            room.setUser1Delete(false);
        }else if(room.getUser2Seq() == user.getSeq() && room.isUser2Delete()){
            room.setUser2Delete(false);
        }

        return new RoomDto.Response(room);
    }

    /* 유저 ID에 해당하는 채팅방을 검색 */
    public List<RoomDto.Response> getMyRoomList(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Room> roomList = roomRepository.findMyChatRoomNotDeleted(user.getSeq());

        if(roomList == null){
            throw new User1NotFoundException();
        }

        List<RoomDto.Response> roomDtoList = new ArrayList<>();

        for(int i = 0; i < roomList.size(); i++){
            RoomDto.Response talkTalkDto = new RoomDto.Response(roomList.get(i));
            roomDtoList.add(talkTalkDto);
        }

        return roomDtoList;
    }

    /* 채팅방 삭제 : 논리 삭제 */
    public void deleteRoom(RoomDto.Request roomDto){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        /* 송신자 수신자 양방향 저장 */
        Room room = roomRepository.findRoomForDelete(roomDto.getRoomSeq(), user.getSeq())
                .orElseThrow(() -> new RoomDeleteFailureException());

        if(room.getUser1Seq() == user.getSeq()){
            room.setUser1Delete(true);
        }else if(room.getUser2Seq() == user.getSeq()){
            room.setUser2Delete(true);
        }
    }
}
