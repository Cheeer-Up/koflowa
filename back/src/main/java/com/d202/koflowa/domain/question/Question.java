package com.d202.koflowa.domain.question;

import com.d202.koflowa.domain.BaseTimeEntity;
import com.d202.koflowa.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "question")
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_seq", columnDefinition = "bigint unsigned")
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    @JsonBackReference
    private User user;

    @Column(name = "question_title", columnDefinition = "varchar(100)")
    private String title;

    @Column(name = "question_content", columnDefinition = "")
    private String question_content;

}
