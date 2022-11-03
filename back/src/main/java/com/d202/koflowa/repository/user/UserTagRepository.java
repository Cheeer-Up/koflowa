package com.d202.koflowa.repository.user;

import com.d202.koflowa.domain.Tag;
import com.d202.koflowa.domain.common.TagStatus;
import com.d202.koflowa.domain.user.User;
import com.d202.koflowa.domain.user.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserTagRepository extends JpaRepository<UserTag, Long> {

    public Optional<UserTag> findByUserAndTagAndTagStatus(User user, Tag tag, TagStatus tagStatus);
}
