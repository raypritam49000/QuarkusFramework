package com.rest.api.services;

import com.rest.api.dao.PersonMapper;
import com.rest.api.models.Person;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class PersonServiceImpl implements PersonService{

    @Inject
    private PersonMapper personMapper;

    @Override
    public List<Person> getAllPersons() {
        return this.personMapper.getAllPersons();
    }

    @Override
    public Person getPerson(Integer id) {
        return personMapper.getPerson(id);
    }

    @Override
    public Integer removePerson(Integer id) {
        return personMapper.removePerson(id);
    }

    @Override
    public Integer createPerson(Person person) {
        return personMapper.createPerson(person);
    }

    @Override
    public Integer updatePerson(Person person) {
        return personMapper.updatePerson(person);
    }
}
