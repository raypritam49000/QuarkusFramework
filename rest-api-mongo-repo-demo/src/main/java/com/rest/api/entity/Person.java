package com.rest.api.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "person")
public class Person {
    @BsonId
    private ObjectId id;
    private String name;
    private String city;
    private String salary;
}
