package com.d202.koflowa.service.talk;


import com.d202.koflowa.domain.talktalk.TalkTalk;
import com.d202.koflowa.dto.talktalk.TalkTalkDto;
import com.d202.koflowa.repository.talk.TalkTalkRepository;
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
    public List<TalkTalkDto.Response> getTalkTalkList(Long userSeq){
        List<TalkTalk> talkTalkList = talkTalkRepository.findMyChatRoom(userSeq);
        List<TalkTalkDto.Response> talkTalkDtoList = new ArrayList<>();

        for(int i = 0; i < talkTalkList.size(); i++){
            TalkTalkDto.Response talkTalkDto = new TalkTalkDto.Response(talkTalkList.get(i));
            talkTalkDtoList.add(talkTalkDto);
        }

        return talkTalkDtoList;
    }

    /* 기존에 있는 방을 검색 후 없으면 생성해서 반환 */
    public List<TalkTalkDto.Response> createTalkTalk(TalkTalkDto.Request talkTalkDto){
        /* 송신자 수신자 양방향 저장 */
        TalkTalk talkTalk = talkTalkRepository.findSpecificChatRoom(talkTalkDto.getTalk_talk_user1(), talkTalkDto.getTalk_talk_user2());
        TalkTalk talkTalk2 = talkTalkRepository.findSpecificChatRoom(talkTalkDto.getTalk_talk_user2(), talkTalkDto.getTalk_talk_user1());

        if(talkTalk == null){
            talkTalk = talkTalkRepository.save(talkTalkDto.toEntity());
        }

        if(talkTalk2 == null){
            talkTalk2 = talkTalkRepository.save(talkTalkDto.toEntity2());
        }

        List<TalkTalkDto.Response> twoRooms = new ArrayList<>();
        twoRooms.add(new TalkTalkDto.Response(talkTalk));
        twoRooms.add(new TalkTalkDto.Response(talkTalk2));

        return twoRooms;
    }

    /* 채팅방 삭제 : CONSTRAINS 설정하기 */
    public void deleteTalkTalk(TalkTalkDto.Request talkTalkDto){
        /* 송신자 수신자 양방향 저장 */
        TalkTalk talkTalk = talkTalkRepository.findByTalkTalkSeq(talkTalkDto.getTalk_talk_seq());

        talkTalkRepository.delete(talkTalk);
    }
}
