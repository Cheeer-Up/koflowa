package com.d202.koflowa.user.repository;

import com.d202.koflowa.tag.domain.Tag;
import com.d202.koflowa.common.domain.TagStatus;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.domain.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserTagRepository extends JpaRepository<UserTag, Long> {

    public Optional<UserTag> findByUserAndTagAndTagStatus(User user, Tag tag, TagStatus tagStatus);

    List<UserTag> findByUser_Seq(long userSeq);
}
