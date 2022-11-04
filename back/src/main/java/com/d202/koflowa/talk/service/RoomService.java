package com.d202.koflowa.talk.service;

import com.d202.koflowa.talk.domain.Room;
import com.d202.koflowa.talk.dto.RoomDto;
import com.d202.koflowa.talk.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    /* 유저 ID에 해당하는 채팅방을 검색 */
    public List<RoomDto.Response> getMyRoomList(Long user1){
        List<Room> roomList = roomRepository.findMyChatRoom(user1);
        List<RoomDto.Response> roomDtoList = new ArrayList<>();

        for(int i = 0; i < roomList.size(); i++){
            RoomDto.Response talkTalkDto = new RoomDto.Response(roomList.get(i));
            roomDtoList.add(talkTalkDto);
        }

        return roomDtoList;
    }

    /* 기존에 있는 방을 검색 후 없으면 생성해서 반환 */
    public List<RoomDto.Response> createRoom(RoomDto.Request roomDto){
        /* 송신자 수신자 양방향 저장 */
        Room room = roomRepository.findSpecificChatRoom(roomDto.getUser1(), roomDto.getUser2());
        Room room2 = roomRepository.findSpecificChatRoom(roomDto.getUser2(), roomDto.getUser1());

        if(room == null){
            room = roomRepository.save(roomDto.toEntity());
        }

        if(room2 == null){
            room2 = roomRepository.save(roomDto.toEntity2());
        }

        List<RoomDto.Response> twoRooms = new ArrayList<>();
        twoRooms.add(new RoomDto.Response(room));
        twoRooms.add(new RoomDto.Response(room2));

        return twoRooms;
    }

    /* 채팅방 삭제 : CONSTRAINS 설정하기 */
    public void deleteRoom(RoomDto.Request roomDto){
        /* 송신자 수신자 양방향 저장 */
        Room room = roomRepository.findByRoomSeq(roomDto.getRoomSeq());

        roomRepository.delete(room);
    }
}
