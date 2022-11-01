package com.d202.koflowa.repository.redisTest;

import com.d202.koflowa.dto.redisTest.RedisTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface RedisTestRepository extends CrudRepository<RedisTest, String> {

}
