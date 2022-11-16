package com.d202.koflowa.question.repository;

import com.d202.koflowa.question.domain.Question;
import com.d202.koflowa.question.domain.QuestionTag;
import com.d202.koflowa.tag.domain.Tag;
import com.d202.koflowa.user.dto.UserTagCntDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionTagRepository extends JpaRepository<QuestionTag, Long> {
    Optional<List<QuestionTag>> findByTag(Tag tag);

    @Query("select qt.question from QuestionTag as qt where qt.tag.name =:tagName")
    Page<Question> findQuestionByTagName(String tagName, Pageable pageable);

    @Query("select qt.tag.name from QuestionTag as qt where qt.question.seq =:seq")
    List<String> findTagNameByQuestionSeq(Long seq);

//    @Query("select ql.tag.name, count(ql.question) " +
//            "from Question as q join QuestionTag as ql " +
//            "on q = ql.question  where q.userSeq = :id group by ql.tag order by count (ql.question)" )
    List<QuestionTag> findAllByQuestion_Seq(Long id);
}
