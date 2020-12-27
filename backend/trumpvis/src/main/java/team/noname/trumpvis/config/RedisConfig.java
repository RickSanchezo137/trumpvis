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
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import team.noname.trumpvis.pojo.help.HelpEmotionMsg;

@Configuration
public class RedisConfig {
    @Bean("emotionRedisTemplate")
    public RedisTemplate<Object, HelpEmotionMsg> emotionRedistemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, HelpEmotionMsg> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(HelpEmotionMsg.class));

        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        template.setKeySerializer(redisSerializer);
        template.setHashKeySerializer(redisSerializer);
        return template;
    }
    @Primary
    @Bean
    public RedisCacheManager jsonRedisCache(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration cacheConfiguration =
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeValuesWith(RedisSerializationContext.SerializationPair
                                .fromSerializer(new Jackson2JsonRedisSerializer<>(String.class)));
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(cacheConfiguration).build();
    }
}
