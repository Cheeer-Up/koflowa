package com.d202.koflowa.talk.service;

import com.d202.koflowa.talk.domain.Room;
import com.d202.koflowa.talk.dto.RoomDto;
import com.d202.koflowa.talk.exception.*;
import com.d202.koflowa.talk.repository.RoomRepository;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.repository.UserRepository;
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
    private final MessageService messageService;
    private final UserRepository userRepository;

    /* 기존에 있는 방을 검색 후 없으면 생성해서 반환 */
    public RoomDto.Response createRoom(RoomDto.RequestCreate roomDto){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        /* 자기 자신과는 채팅방 생성 불가 */
        if(user.getSeq() == roomDto.getReceiver_seq()){
            throw new RoomNotCreatedException();
        }

        /* 송신자 수신자 양방향 저장 */
        Room room = roomRepository.findSpecificChatRoom(user.getSeq(), roomDto.getReceiver_seq());
        System.out.println("room 중간값 : " + room);
        if(room == null){
            room = roomRepository.save(roomDto.toEntity(user.getSeq()));

            if(room == null){
                throw new Room1NoFoundException();
            }

        /* 논리 삭제 되었다면 복구 해주기 */
        }else if(room.getUser1Seq() == user.getSeq() && room.getUser1Delete()){
            room.setUser1Delete(false);
        }else if(room.getUser2Seq() == user.getSeq() && room.getUser2Delete()){
            room.setUser2Delete(false);
        }

        return new RoomDto.Response(room);
    }

    /* 유저 ID에 해당하는 채팅방을 검색 */
    public List<RoomDto.ResponseWithUser> getMyRoomList(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Room> roomList = roomRepository.findMyChatRoomNotDeleted(user.getSeq());

        if(roomList == null){
            throw new User1NotFoundException();
        }

        List<RoomDto.ResponseWithUser> roomDtoList = new ArrayList<>();

        for(int i = 0; i < roomList.size(); i++){
            Long receiverSeq = -1L;
            if(roomList.get(i).getUser1Seq() == user.getSeq()){
                receiverSeq = roomList.get(i).getUser2Seq();
            }else if(roomList.get(i).getUser2Seq() == user.getSeq()) {
                receiverSeq = roomList.get(i).getUser1Seq();
            }

            User receiver = userRepository.findBySeq2(receiverSeq);
            System.out.println("receiver : " + receiver);
            RoomDto.ResponseWithUser talkTalkDto = new RoomDto.ResponseWithUser(roomList.get(i), receiver);
            talkTalkDto.setWaitingMessageNumber(messageService.countWaitingMessageByRoom(talkTalkDto.getRoomSeq(), user.getSeq()));
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
            room.setUser1Delete(Boolean.TRUE);
        }else if(room.getUser2Seq() == user.getSeq()){
            room.setUser2Delete(Boolean.TRUE);
        }
    }
}
