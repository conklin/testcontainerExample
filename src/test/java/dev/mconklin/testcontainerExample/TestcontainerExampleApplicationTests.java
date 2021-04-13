package dev.mconklin.testcontainerExample;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import redis.clients.jedis.Jedis;

@SpringBootTest
@Testcontainers
class TestcontainerExampleApplicationTests {


    @Container
    public GenericContainer redis
            = new GenericContainer(DockerImageName.parse("redis:5.0.3-alpine")).withExposedPorts(6379);


    @Test
    void contextLoads() {
        Jedis jedis = new Jedis(redis.getContainerIpAddress(), redis.getMappedPort(6379));
        jedis.append("foo", "bar");
        System.out.println(jedis.get("foo"));
    }

}
