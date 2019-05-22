package com.sdnware.news.pojo.mongodb;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@org.springframework.data.mongodb.core.mapping.Document
public class ProbeInfo {

    @Id public ObjectId id;
    @Field(value="recvtime") public Date recvtime;
    @Field(value="staList") public List<Document> stas;
    @Field(value="apmac") public String mac;
}
