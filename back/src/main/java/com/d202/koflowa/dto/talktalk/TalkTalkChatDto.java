package com.d202.koflowa.dto.talktalk;

import com.d202.koflowa.domain.talktalk.TalkTalk;
import com.d202.koflowa.domain.talktalk.TalkTalkChat;
import lombok.*;

public class TalkTalkChatDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class Request{
        private Long talk_talk_chat_seq;
        private TalkTalk talk_talk_seq;
        private Long talk_talk_user;
        private boolean talk_talk_user1_deleted;
        private boolean talk_talk_user2_deleted;
        private String chat_content;
        private int talk_talk_chat_type;
        private int session_code;

        public TalkTalkChat toEntity() {
            TalkTalkChat talkTalkChat = TalkTalkChat.builder()
                    .talkTalkChatSeq(talk_talk_chat_seq)
                    .talkTalkSeq(talk_talk_seq)
                    .talkTalkUser(talk_talk_user)
                    .talkTalkUser1Deleted(talk_talk_user1_deleted)
                    .talkTalkUser2Deleted(talk_talk_user2_deleted)
                    .chatContent(chat_content)
                    .talkTalkChatType(talk_talk_chat_type)
                    .sessionCode(session_code)
                    .build();
            return talkTalkChat;
        }
    }

    @Getter
    private static class Response{
        private Long talk_talk_chat_seq;
        private TalkTalk talk_talk_seq;
        private Long talk_talk_user;
        private boolean talk_talk_user1_deleted;
        private boolean talk_talk_user2_deleted;
        private String chat_content;
        private String created_time;
        private int talk_talk_chat_type;
        private int session_code;

        // boolean -> Boolean 이어야 인식되었다.
        public Response(TalkTalkChat talkTalkChat) {
            this.talk_talk_chat_seq = talkTalkChat.getTalkTalkChatSeq();
            this.talk_talk_seq = talkTalkChat.getTalkTalkSeq();
            this.talk_talk_user = talkTalkChat.getTalkTalkUser();
            this.talk_talk_user1_deleted = talkTalkChat.getTalkTalkUser1Deleted();
            this.talk_talk_user2_deleted = talkTalkChat.getTalkTalkUser2Deleted();
            this.chat_content = talkTalkChat.getChatContent();
            this.created_time = talkTalkChat.getCreatedTime().toString();
            this.talk_talk_chat_type = talkTalkChat.getTalkTalkChatType();
            this.session_code = talkTalkChat.getSessionCode();
        }
    }
}
