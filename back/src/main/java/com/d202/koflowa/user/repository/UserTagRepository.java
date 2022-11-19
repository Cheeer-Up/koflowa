package com.d202.koflowa.user.repository;

import com.d202.koflowa.tag.domain.Tag;
import com.d202.koflowa.common.domain.TagStatus;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.domain.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserTagRepository extends JpaRepository<UserTag, Long> {

    public Optional<UserTag> findByUserAndTagAndTagStatus(User user, Tag tag, TagStatus tagStatus);

    @Query("select u from UserTag u where u.user.seq = :userSeq and u.tag.name = :tagName and u.tagStatus = :tagStatus")
    public Optional<UserTag> findByUserSeqAndTagNameAndTagStatus(Long userSeq, String tagName, TagStatus tagStatus);

    List<UserTag> findByUser_Seq(long userSeq);

    public List<UserTag> findByUserAndTagStatus(User user, TagStatus tagStatus);

    @Query("select ut.tag from UserTag as ut where ut.user = :user and ut.tagStatus = :tagStatus")
    List<Tag> findTagByUserAndTagStatus(User user, TagStatus tagStatus);
}
