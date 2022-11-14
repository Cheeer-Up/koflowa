package com.d202.koflowa.user.service;

import com.d202.koflowa.user.domain.ReputationLog;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.dto.ReputationLogDto;
import com.d202.koflowa.user.repository.ReputationLogRepository;
import com.d202.koflowa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReputationService {
    private final ReputationLogRepository reputationLogRepository;
    private final UserRepository userRepository;
    final int THRESHOLD = 150;
    public ReputationLogDto.Response saveLog(User user, String msg, int score, Long questionSeq){
        ReputationLog log =  ReputationLog.builder()
                .score(score).
                user(user)
                .message(msg)
                .questionSeq(questionSeq)
                .build();

        ReputationLog save = reputationLogRepository.save(log);
        int reputationScore = user.getReputationScore();
        if(reputationScore < THRESHOLD && reputationScore + score >= THRESHOLD){
            // 채팅 로그
        }
        User repositoryUser = userRepository.findBySeq(user.getSeq()).get();
        repositoryUser.putReputationScore(reputationScore + score);
        return new ReputationLogDto.Response(save);
    }
}
