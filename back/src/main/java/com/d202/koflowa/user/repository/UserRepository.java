package com.d202.koflowa.user.repository;

import com.d202.koflowa.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findBySeq(Long seq);
}
