package com.d202.koflowa.user.repository;

import com.d202.koflowa.user.domain.ReputationLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReputationLogRepository extends JpaRepository<ReputationLog, Long> {
    Page<ReputationLog> findByUser_Seq(long userSeq, Pageable pageable);
}
