package com.sdnware.news.pojo.es;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * Created by sdnware on 18-8-16.
 */
@Document(indexName = "idx_news", type = "tb_news", replicas = 0)
public class NewsInfo implements Serializable {

    @Getter @Setter @Id private String id;
    @Getter @Setter @Field(type = FieldType.Text) private String title;
    @Getter @Setter @Field(type = FieldType.Text) private String subTitle;
    @Getter @Setter @Field(type = FieldType.Text) private String publicTime;
    @Getter @Setter @Field(type = FieldType.Text) private String author;
    @Getter @Setter @Field(type = FieldType.Text) private String content;
    @Getter @Setter @Field(type = FieldType.Integer) private Integer state;
    @Getter @Setter @Field(type = FieldType.Long) private Long readCount;

    @Override
    public String toString() {
        return "NewsInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", publicTime='" + publicTime + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", state=" + state +
                ", readCount=" + readCount +
                '}';
    }
}
