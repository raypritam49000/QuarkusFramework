package com.rest.api.repository;

import com.rest.api.entity.Person;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonRepository implements PanacheMongoRepositoryBase<Person, ObjectId> {

}