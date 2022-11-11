package com.d202.koflowa.user.service;

import com.d202.koflowa.user.domain.ReputationLog;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.dto.ReputationLogDto;
import com.d202.koflowa.user.repository.ReputationLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReputationService {
    private final ReputationLogRepository reputationLogRepository;

    public ReputationLogDto.Response saveLog(User user, String msg, int score, Long questionSeq){
        ReputationLog log =  ReputationLog.builder()
                .score(score).
                user(user)
                .message(msg)
                .questionSeq(questionSeq)
                .build();

        ReputationLog save = reputationLogRepository.save(log);
        return new ReputationLogDto.Response(save);
    }
}
