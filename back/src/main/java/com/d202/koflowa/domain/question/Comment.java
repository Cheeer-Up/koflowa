package com.d202.koflowa.domain.question;

import com.d202.koflowa.domain.BaseTimeEntity;
import com.d202.koflowa.domain.common.QAType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_seq", columnDefinition = "bigint unsigned")
    private Long seq;

    @Column(name = "user_seq", columnDefinition = "bigint unsigned")
    private Long userSeq;

    @Column(name = "board_seq", columnDefinition = "bigint unsigned")
    private Long boardSeq;

    @Column(name = "comment_type", length = 10)
    @Enumerated(EnumType.STRING)
    private QAType type;

    @Column(name = "comment_content", length = 500)
    private String content;
}
