package bootstrap;

import com.sdnware.news.datasource.DataSourceBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by sdnware on 18-8-15.
 */
@SpringBootApplication(scanBasePackages = "com.sdnware.news")
@EnableElasticsearchRepositories(basePackages = "com.sdnware.news.dao.es")
@EnableMongoRepositories(basePackages = "com.sdnware.news.dao.mongodb")
@MapperScan(basePackages = "com.sdnware.news.dao.mybatis")
@Import(DataSourceBean.class)
public class NewsServiceRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(NewsServiceRunner.class)
                .web(WebApplicationType.NONE)
                .run(args);

    }
}
