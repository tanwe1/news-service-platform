package com.sdnware.news.dao.mongodb;

import com.sdnware.news.pojo.mongodb.ProbeInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 适用于实体映射已指定collections的操作
 */
public interface ProbeMongoRepository extends MongoRepository<ProbeInfo, ObjectId>{
}
