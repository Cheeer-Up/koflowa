package com.d202.koflowa.talk.repository;

import com.d202.koflowa.talk.dto.socket.ChatRoomDto;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRoomRepository {
    private Map<String, ChatRoomDto> roomDtoMap;

    @PostConstruct
    private void init(){
        roomDtoMap = new LinkedHashMap<>();
    }

    public List<ChatRoomDto> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<ChatRoomDto> result = new ArrayList<>(roomDtoMap.values());
        Collections.reverse(result);

        return result;
    }

    public ChatRoomDto findRoomById(String id){
        return roomDtoMap.get(id);
    }

    public ChatRoomDto createRoomDto(){
        ChatRoomDto room = ChatRoomDto.create();
        roomDtoMap.put(room.getRoomId(), room);

        return room;
    }
}
