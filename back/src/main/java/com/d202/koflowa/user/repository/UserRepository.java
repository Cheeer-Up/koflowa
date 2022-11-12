package com.d202.koflowa.user.repository;

import com.d202.koflowa.user.domain.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findBySeq(Long seq);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u.refreshToken FROM User u WHERE u.email=:email")
    String getRefreshTokenById(@Param("email") Long email);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.refreshToken=:token WHERE u.email=:email")
    void updateRefreshToken(@Param("email") String email, @Param("token") String token);

    Optional<User> findByRefreshToken(String refreshToken);
}
