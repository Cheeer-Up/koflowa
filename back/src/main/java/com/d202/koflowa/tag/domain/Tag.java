package com.d202.koflowa.tag.domain;

import com.d202.koflowa.common.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tag")
public class Tag extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_seq")
    private Long seq;

    @Column(name = "tag_name", length = 50, nullable = false)
    private String name;

    @Column(name = "tag_description", nullable = false)
    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
