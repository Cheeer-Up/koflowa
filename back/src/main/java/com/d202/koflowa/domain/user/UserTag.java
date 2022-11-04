package com.d202.koflowa.domain.user;


import com.d202.koflowa.domain.Tag;
import com.d202.koflowa.domain.common.TagStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_tag")
public class UserTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_tag_seq", columnDefinition = "bigint unsigned")
    private Long seq;

    @ManyToOne()
    @JoinColumn(name = "user_seq")
    @JsonBackReference
    private User user;

    @ManyToOne()
    @JoinColumn(name = "tag_seq")
    @JsonBackReference
    private Tag tag;

    @Column(name = "tag_status", length = 10)
    @Enumerated(EnumType.STRING)
    private TagStatus tagStatus;
}
