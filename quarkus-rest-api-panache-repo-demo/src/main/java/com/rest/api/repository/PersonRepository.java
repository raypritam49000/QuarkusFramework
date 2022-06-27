package com.rest.api.repository;

import com.rest.api.entity.Person;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public Person findByName(String name){
        return find("name", name).firstResult();
    }

    public List<Person> findAllPerson(){
        return listAll();
    }

    public  Person findById(Long id) {
        return find("id", id).firstResult();
    }

    public Boolean delete(Long id) {
        delete("id", id);
        return true;
    }

    public  void savePerson(Person person){
        persist(person);
    }

}