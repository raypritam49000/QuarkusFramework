package com.rest.api.services;

import com.rest.api.entity.Person;

import java.util.List;

public interface PersonService {
    public abstract Boolean createPerson(Person person);
    public abstract List<Person> getAllPersons();
    public abstract Person getPerson(String id);
    public abstract Boolean deletePerson(String id);
    public abstract Boolean updatedPerson(Person person);
}
