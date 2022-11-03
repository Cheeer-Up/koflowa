package com.d202.koflowa.repository.user;

import com.d202.koflowa.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findBySeq(Long seq);
}
