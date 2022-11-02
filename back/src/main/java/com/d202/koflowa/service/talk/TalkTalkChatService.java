package com.d202.koflowa.service.talk;

import com.d202.koflowa.domain.talktalk.TalkTalk;
import com.d202.koflowa.domain.talktalk.TalkTalkChat;
import com.d202.koflowa.dto.talktalk.TalkTalkChatDto;
import com.d202.koflowa.dto.talktalk.TalkTalkDto;
import com.d202.koflowa.repository.talk.TalkTalkChatRepository;
import com.d202.koflowa.repository.talk.TalkTalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TalkTalkChatService {

    private final TalkTalkChatRepository talkTalkChatRepository;
    private final TalkTalkRepository talkTalkRepository;

    public List<TalkTalkChatDto.ResponseTalkTalkChatDto> getChatMessageList(TalkTalkDto.RequestTalkTalkDto talkTalkDto){
        System.out.println("들어왔나?" + talkTalkDto.getTalk_talk_seq());
        List<TalkTalkChat> talkChatMessageList = talkTalkChatRepository.findAllByTalkTalkOrderByCreatedTime(talkTalkRepository.findByTalkTalkSeq(talkTalkDto.getTalk_talk_seq()));
        List<TalkTalkChatDto.ResponseTalkTalkChatDto> talkTalkChatDtoList = new ArrayList<>();


        for(int i = 0; i < talkChatMessageList.size(); i++){
            TalkTalkChatDto.ResponseTalkTalkChatDto talkTalkChatDto = new TalkTalkChatDto.ResponseTalkTalkChatDto(talkChatMessageList.get(i));
            talkTalkChatDtoList.add(talkTalkChatDto);
        }

        return talkTalkChatDtoList;
    }

    public TalkTalkChatDto.ResponseTalkTalkChatDto createTalkTalkChat(TalkTalkChatDto.RequestTalkTalkChatDto talkTalkChatDto){
        /* 양방향 매핑하지 않고 프론트 단에서 두 번 호출한다 */
        TalkTalkChat talkTalkChat = talkTalkChatRepository.save(talkTalkChatDto.toEntity(talkTalkRepository.findByTalkTalkSeq(talkTalkChatDto.getTalk_talk_seq())));

        return new TalkTalkChatDto.ResponseTalkTalkChatDto(talkTalkChat);
    }

    public void deleteTalkTalkChat(TalkTalkChatDto.RequestTalkTalkChatDto talkTalkChatDto){
        /* 양방향 매핑하지 않고 프론트 단에서 두 번 호출한다 */
        talkTalkChatRepository.delete(talkTalkChatRepository.findByTalkTalkChatSeq(talkTalkChatDto.getTalk_talk_chat_seq()));
    }
}
