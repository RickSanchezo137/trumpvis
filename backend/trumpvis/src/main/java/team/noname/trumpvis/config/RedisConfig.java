/*
package team.noname.trumpvis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<Object, String> jsonRedistemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, String> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(String.class));
        return template;
    }
    @Primary
    @Bean
    public RedisCacheManager jsonRedisCache(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration cacheConfiguration =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofHours(1))
                        .serializeValuesWith(RedisSerializationContext.SerializationPair
                                .fromSerializer(new Jackson2JsonRedisSerializer<>(String.class)));
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(cacheConfiguration).build();
    }
}
*/
