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
public class TalkService {
    private final TalkTalkRepository talkTalkRepository;

    /* 유저 ID에 해당하는 채팅방을 검색 */
    public List<TalkTalkDto.Response> getTalkTalkList(Long userSeq){
        List<TalkTalk> talkTalkList = talkTalkRepository.findAllByTalkTalkUser1OrTalkTalkUser2(userSeq);
        List<TalkTalkDto.Response> talkTalkDtoList = new ArrayList<>();
        for(int i = 0; i < talkTalkList.size(); i++){
            TalkTalkDto.Response talkTalkDto = new TalkTalkDto.Response(talkTalkList.get(i));
            talkTalkDtoList.add(talkTalkDto);
        }

        return talkTalkDtoList;
    }

    public TalkTalkDto.Response createTalkTalk(TalkTalkDto.Request talkTalkDto){
        TalkTalk talkTalk = talkTalkRepository.findAllByTalkTalkUser1AndTalkTalkUser2(talkTalkDto.getTalk_talk_user1(), talkTalkDto.getTalk_talk_user2());

        if(talkTalk == null){
            talkTalk = talkTalkRepository.save(talkTalkDto.toEntity());
        }

        return new TalkTalkDto.Response(talkTalk);
    }
    
}
