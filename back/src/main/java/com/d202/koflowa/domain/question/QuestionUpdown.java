package com.d202.koflowa.domain.question;

import com.d202.koflowa.domain.Tag;
import com.d202.koflowa.domain.common.UDType;
import com.d202.koflowa.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "question_updown")
public class QuestionUpdown {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_updown_seq", columnDefinition = "bigint unsigned")
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_seq")
    @JsonBackReference
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    @JsonBackReference
    private User user;

    @Column(name = "question_updown_type", length = 4)
    private UDType type;
}
