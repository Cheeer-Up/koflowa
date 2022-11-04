//package com.d202.koflowa.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///** * Redis Cluster Config  * */
//@Component
//@ConfigurationProperties(prefix = "spring.redis.cluster")
//public class RedisClusterConfigurationProperties {
//    /**
//     * * spring.redis.cluster.nodes[0]=127.0.0.1:7000
//     * * spring.redis.cluster.nodes[1]=127.0.0.1:7001
//     * * spring.redis.cluster.nodes[2]=127.0.0.1:7002
//     * * spring.redis.cluster.nodes[3]=127.0.0.1:7003
//     * */
//    List<String> nodes;
//    public List<String> getNodes() {
//        return nodes;
//    }
//    public void setNodes(List<String> nodes) {
//        this.nodes = nodes;
//    }
//}