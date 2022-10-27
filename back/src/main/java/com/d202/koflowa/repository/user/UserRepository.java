package com.d202.koflowa.repository.user;

import com.d202.koflowa.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
