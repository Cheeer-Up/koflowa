package com.d202.koflowa.kotalk.dto;

import com.d202.koflowa.kotalk.domain.TalkTalk;
import lombok.*;

public class TalkTalkDto {

    @Getter
    @Builder
    public static class RequestTalkTalkDto{
        private Long talk_talk_seq;
        private Long talk_talk_user1;
        private Long talk_talk_user2;
        private String talk_talk_user1_nickname;
        private String talk_talk_user2_nickname;

        public TalkTalk toEntity() {
            TalkTalk talkTalk = TalkTalk.builder()
                    .talkTalkSeq(talk_talk_seq)
                    .talkTalkUser1(talk_talk_user1)
                    .talkTalkUser2(talk_talk_user2)
                    .talkTalkUser1Nickname(talk_talk_user1_nickname)
                    .talkTalkUser2Nickname(talk_talk_user2_nickname)
                    .build();
            return talkTalk;
        }

        /* 유저1과 유저2의 값의 위치를 바꾸어 저장한다 */
        public TalkTalk toEntity2() {
            TalkTalk talkTalk = TalkTalk.builder()
                    .talkTalkSeq(talk_talk_seq)
                    .talkTalkUser1(talk_talk_user2)
                    .talkTalkUser2(talk_talk_user1)
                    .talkTalkUser1Nickname(talk_talk_user2_nickname)
                    .talkTalkUser2Nickname(talk_talk_user1_nickname)
                    .build();
            return talkTalk;
        }
    }

    @Getter
    public static class ResponseTalkTalkDto{
        private Long talk_talk_seq;
        private Long talk_talk_user1;
        private Long talk_talk_user2;
        private String talk_talk_user1_nickname;
        private String talk_talk_user2_nickname;

        public ResponseTalkTalkDto(TalkTalk talkTalk) {
            this.talk_talk_seq = talkTalk.getTalkTalkSeq();
            this.talk_talk_user1 = talkTalk.getTalkTalkUser1();
            this.talk_talk_user2 = talkTalk.getTalkTalkUser2();
            this.talk_talk_user1_nickname = talkTalk.getTalkTalkUser1Nickname();
            this.talk_talk_user2_nickname = talkTalk.getTalkTalkUser2Nickname();
        }
    }
}
