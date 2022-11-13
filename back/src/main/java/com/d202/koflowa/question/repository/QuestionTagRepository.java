package com.d202.koflowa.question.repository;

import com.d202.koflowa.question.domain.QuestionTag;
import com.d202.koflowa.tag.domain.Tag;
import com.d202.koflowa.user.dto.UserTagCntDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionTagRepository extends JpaRepository<QuestionTag, Long> {
    Optional<List<QuestionTag>> findByTag(Tag tag);

//    @Query("select ql.tag.name, count(ql.question) " +
//            "from Question as q join QuestionTag as ql " +
//            "on q = ql.question  where q.userSeq = :id group by ql.tag order by count (ql.question)" )
    List<QuestionTag> findAllByQuestion_Seq(Long id);
}
