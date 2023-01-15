package com.rest.api.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.List;

@MongoEntity(collection = "person")
public class Person extends PanacheMongoEntityBase {
    @BsonId
    public ObjectId id;
    public String name;
    public String city;
    public String salary;

    public static Boolean savePerson(Person person) {
        persist(person);
        return true;
    }

    public static List<Person> getAllPersons() {
        return listAll();
    }

    public static Person getPerson(ObjectId id) {
        return findById(id);
    }

    public static Boolean deletePerson(ObjectId id) {
        return deleteById(id);
    }

    public static Boolean updatePerson(Person person) {
        Person.persistOrUpdate(person);
        return true;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
