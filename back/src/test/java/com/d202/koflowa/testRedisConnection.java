// package com.d202.koflowa;

// import com.d202.koflowa.dto.redisTest.RedisTest;
// import com.d202.koflowa.repository.redisTest.RedisTestRepository;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.redis.core.RedisTemplate;

// import java.time.LocalDateTime;
// import java.util.Optional;

// @SpringBootTest
// public class testRedisConnection {
//     @Autowired
//     private RedisTestRepository redisTestRepository;

//     @Autowired
//     private RedisTemplate redisTemplate;
//     @Test
//     void add(){
//         int a = 10;
//         System.out.println(a);
//         a += 20;
//         System.out.println(a);
//     }
//     @Test
//     void test(){
// //        redisTemplate.getConnectionFactory().getConnection().info().toString();


// //        for(int i = 0; i < 2; i++){
// //            redisTemplate.opsForValue().set(i,"chung" + i);
// //        }
// //
// //        for(int i = 0; i < 2; i++){
// //            String returnValue = (String) redisTemplate.opsForValue().get(i);
// //            System.out.println(returnValue);
// //        }

// //        redisTemplate.opsForValue().set(0,"chung" + 0);
// //        String returnValue = (String) redisTemplate.opsForValue().get("a");
// //        System.out.println(returnValue);


//         RedisTest redisTest = new RedisTest("abcd", "Chung", 22, LocalDateTime.now());

//         redisTestRepository.save(redisTest);

// //        Optional<RedisTest> redisTest2 = redisTestRepository.findById(redisTest.getId());
// //        System.out.println(redisTest2);

// //        redisTestRepository.count();
// //        redisTestRepository.delete(redisTest);
//     }
// }
