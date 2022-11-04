package com.d202.koflowa.kotalk.service;


import com.d202.koflowa.kotalk.domain.TalkTalk;
import com.d202.koflowa.kotalk.dto.TalkTalkDto;
import com.d202.koflowa.kotalk.repository.TalkTalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TalkTalkService {
    private final TalkTalkRepository talkTalkRepository;

    /* 유저 ID에 해당하는 채팅방을 검색 */
    public List<TalkTalkDto.ResponseTalkTalkDto> getTalkTalkList(Long userSeq){
        List<TalkTalk> talkTalkList = talkTalkRepository.findMyChatRoom(userSeq);
        List<TalkTalkDto.ResponseTalkTalkDto> talkTalkDtoList = new ArrayList<>();

        for(int i = 0; i < talkTalkList.size(); i++){
            TalkTalkDto.ResponseTalkTalkDto talkTalkDto = new TalkTalkDto.ResponseTalkTalkDto(talkTalkList.get(i));
            talkTalkDtoList.add(talkTalkDto);
        }

        return talkTalkDtoList;
    }

    /* 기존에 있는 방을 검색 후 없으면 생성해서 반환 */
    public List<TalkTalkDto.ResponseTalkTalkDto> createTalkTalk(TalkTalkDto.RequestTalkTalkDto talkTalkDto){
        /* 송신자 수신자 양방향 저장 */
        TalkTalk talkTalk = talkTalkRepository.findSpecificChatRoom(talkTalkDto.getTalk_talk_user1(), talkTalkDto.getTalk_talk_user2());
        TalkTalk talkTalk2 = talkTalkRepository.findSpecificChatRoom(talkTalkDto.getTalk_talk_user2(), talkTalkDto.getTalk_talk_user1());

        if(talkTalk == null){
            talkTalk = talkTalkRepository.save(talkTalkDto.toEntity());
        }

        if(talkTalk2 == null){
            talkTalk2 = talkTalkRepository.save(talkTalkDto.toEntity2());
        }

        List<TalkTalkDto.ResponseTalkTalkDto> twoRooms = new ArrayList<>();
        twoRooms.add(new TalkTalkDto.ResponseTalkTalkDto(talkTalk));
        twoRooms.add(new TalkTalkDto.ResponseTalkTalkDto(talkTalk2));

        return twoRooms;
    }

    /* 채팅방 삭제 : CONSTRAINS 설정하기 */
    public void deleteTalkTalk(TalkTalkDto.RequestTalkTalkDto talkTalkDto){
        /* 송신자 수신자 양방향 저장 */
        TalkTalk talkTalk = talkTalkRepository.findByTalkTalkSeq(talkTalkDto.getTalk_talk_seq());

        talkTalkRepository.delete(talkTalk);
    }
}
