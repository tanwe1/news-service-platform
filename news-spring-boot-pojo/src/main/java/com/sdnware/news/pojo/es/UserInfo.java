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
@Document(indexName = "idx_sys", type = "tb_user", replicas = 0)
public class UserInfo implements Serializable {

    @Getter @Setter @Id private String id;
    @Getter @Setter @Field(type = FieldType.Text) private String username;
    @Getter @Setter @Field(type = FieldType.Text) private String password;

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
