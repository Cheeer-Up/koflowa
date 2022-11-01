package com.d202.koflowa.dto.talktalk;

import com.d202.koflowa.domain.talktalk.TalkTalk;
import com.d202.koflowa.domain.talktalk.TalkTalkChat;
import lombok.*;

public class TalkTalkChatDto {
    @Data
    @RequiredArgsConstructor
    public static class RequestTalkTalkChatDto{
        private Long talk_talk_chat_seq;
        private Long talk_talk_seq;
        private boolean checked;
        private String chat_content;
        private int talk_talk_chat_type;
        private int session_code;

        public TalkTalkChat toEntity(TalkTalk talkTalk) {
            return TalkTalkChat.builder()
                    .talkTalkChatSeq(talk_talk_chat_seq)
                    .talkTalk(talkTalk)
                    .checked(checked)
                    .chatContent(chat_content)
                    .talkTalkChatType(talk_talk_chat_type)
                    .sessionCode(session_code)
                    .build();
        }
    }

    @Getter
    public static class ResponseTalkTalkChatDto{
        private Long talk_talk_chat_seq;
        private TalkTalk talk_talk_seq;
        private boolean checked;
        private String chat_content;
        private String created_time;
        private int talk_talk_chat_type;
        private int session_code                ;

        // boolean -> Boolean 이어야 인식되었다.
        public ResponseTalkTalkChatDto(TalkTalkChat talkTalkChat) {
            this.talk_talk_chat_seq = talkTalkChat.getTalkTalkChatSeq();
            this.talk_talk_seq = talkTalkChat.getTalkTalk();
            this.checked = talkTalkChat.getChecked();
            this.chat_content = talkTalkChat.getChatContent();
            this.created_time = talkTalkChat.getCreatedTime().toString();
            this.talk_talk_chat_type = talkTalkChat.getTalkTalkChatType();
            this.session_code = talkTalkChat.getSessionCode();
        }
    }
}
