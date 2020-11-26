package team.noname.trumpvis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = {"team.noname.trumpvis.dao"})
@EnableCaching
public class TrumpvisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrumpvisApplication.class, args);
    }

}
