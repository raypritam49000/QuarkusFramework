package com.rest.api.repository;

import com.rest.api.entity.Person;
import io.quarkus.mongodb.panache.PanacheMongoRepository;


import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonRepository implements PanacheMongoRepository<Person> {

}