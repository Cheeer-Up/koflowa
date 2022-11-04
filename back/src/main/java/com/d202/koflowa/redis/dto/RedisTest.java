package com.d202.koflowa.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "redisTest"  )
public class RedisTest {

    @Id
    private String id;
    private String name;
    private Integer age;
    private LocalDateTime createdAt;
}