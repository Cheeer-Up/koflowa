package com.d202.koflowa.dto.question;

import com.d202.koflowa.domain.question.Question;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class QuestionDto {
    private Long question_seq;
    private Long user_seq;
    private String title;
    private String abc;
    private String created_time;
    private String updated_time;

    public Question toEntity(){

        return null;
    }
}
