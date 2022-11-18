package com.d202.koflowa.user.repository;

import com.d202.koflowa.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findBySeq(Long seq);

    @Query("SELECT user FROM User user WHERE user.seq=:userSeq")
    User findBySeq2(@Param("userSeq") Long userSeq);
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
