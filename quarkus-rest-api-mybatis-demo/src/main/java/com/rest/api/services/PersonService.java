package com.rest.api.services;

import com.rest.api.models.Person;

import java.util.List;

public interface PersonService {
    public abstract List<Person> getAllPersons();
    public abstract Person getPerson(Integer id);
    public abstract Integer removePerson(Integer id);
    public abstract Integer createPerson(Person person);
    public abstract Integer updatePerson(Person person);
}
