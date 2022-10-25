package com.d202.koflowa.repository.talk;

import com.d202.koflowa.dto.talktalk.RoomDto;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class RoomRepository {
    private Map<String, RoomDto> roomDtoMap;

    @PostConstruct
    private void init(){
        roomDtoMap = new LinkedHashMap<>();
    }

    public List<RoomDto> findAllRooms(){
        //채팅방 생성 순서 최근 순으로 반환
        List<RoomDto> result = new ArrayList<>(roomDtoMap.values());
        Collections.reverse(result);

        return result;
    }

    public RoomDto findRoomById(String id){
        return roomDtoMap.get(id);
    }

    public RoomDto createRoomDto(){
        RoomDto room = RoomDto.create();
        roomDtoMap.put(room.getRoomId(), room);

        return room;
    }
}
