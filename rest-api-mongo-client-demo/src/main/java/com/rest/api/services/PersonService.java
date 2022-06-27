package com.rest.api.services;

import com.rest.api.models.Person;

import java.util.List;

public interface PersonService {
    public Boolean addPerson(Person person);
    public List<Person> getAllPersons();
    public Person getPerson(String id);
    public Boolean deletePerson(String id);
    public Boolean updatePerson(String id,Person person);
}
