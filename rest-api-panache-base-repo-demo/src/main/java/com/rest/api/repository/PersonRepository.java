package com.rest.api.repository;

import com.rest.api.entity.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonRepository implements PanacheRepositoryBase<Person,Long> {

}