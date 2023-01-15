package com.rest.api.services;

import com.rest.api.entity.Person;
import com.rest.api.repository.PersonRepository;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class PersonServiceImpl implements PersonService{

    @Inject
    private PersonRepository personRepository;

    @Override
    public Boolean createPerson(Person person) {
        personRepository.persist(person);
        return true;
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.listAll();
    }

    @Override
    public Person getPerson(String id) {
        return personRepository.findById(new ObjectId(id));
    }

    @Override
    public Boolean deletePerson(String id) {
        personRepository.deleteById(new ObjectId(id));
        return true;
    }

    @Override
    public Boolean updatedPerson(Person person) {
        personRepository.update(person);
        return true;
    }
}
