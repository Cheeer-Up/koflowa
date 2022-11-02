package com.d202.koflowa.config;

import io.lettuce.core.ReadFrom;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@EnableCaching
@Configuration
public class RedisConfig {

    public static String IMAGE = "proxy-img:";
    public static String TOPGAINER = "top-gainer";
    public static String TOPLOSER = "top-loser";
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder().
                readFrom(ReadFrom.REPLICA_PREFERRED).build();

        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.clusterNode("k7d202.p.ssafy.io",6379);
        redisClusterConfiguration.clusterNode("k7d202.p.ssafy.io",7000);
        redisClusterConfiguration.clusterNode("k7d202.p.ssafy.io",7001);

        return new LettuceConnectionFactory("k7d202.p.ssafy.io", 6379);
    }
    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<String, byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
}