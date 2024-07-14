package net.engineeringdigest.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class JournalApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(JournalApplication.class, args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        System.out.println(environment.getActiveProfiles()[0]);
    }

    @Bean
    public PlatformTransactionManager transactionManager(MongoDatabaseFactory dbFactory) { //MongoDatabaseFactory helps in creating connection with database
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}