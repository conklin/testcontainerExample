package dev.mconklin.testcontainerExample;

import org.junit.Assert;
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
    void shouldPass() {
        Jedis jedis = new Jedis(redis.getContainerIpAddress(), redis.getMappedPort(6379));
        jedis.append("foo", "bar");
        Assert.assertEquals(jedis.get("foo"), "bar");
    }

    @Test
    void shouldFail() {
        Jedis jedis = new Jedis(redis.getContainerIpAddress(), redis.getMappedPort(6379));
        jedis.append("foo", "bar");
        Assert.assertEquals(jedis.get("foo"), "zar");
    }

}
