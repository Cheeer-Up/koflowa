package com.d202.koflowa.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tag")
public class Tag extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_seq")
    private Long seq;

    @Column(name = "tag_name", length = 50, nullable = false)
    private String name;

    @Column(name = "tag_discription", nullable = false)
    private String discription;
}
