package bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * Created by sdnware on 18-8-15.
 */
@SpringBootApplication(scanBasePackages = "com.sdnware.news.controller")
public class NewsWebRunner {

    public static void main(String[] args) {
        SpringApplication.run(NewsWebRunner.class, args);
    }
}
