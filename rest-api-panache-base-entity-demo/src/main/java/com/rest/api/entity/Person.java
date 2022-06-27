package com.rest.api.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;

@Entity
public class Person extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String city;
    public String salary;

    public static Person findByName(String name) {
        return find("name", name).firstResult();
    }

    public static Person findById(Long id) {
        return find("id", id).firstResult();
    }

    public static List<Person> findAllPerson() {
        return findAll().list();
    }

    public static Boolean delete(Long id) {
        delete("id", id);
        return true;
    }

    public static void savePerson(Person person){
        persist(person);
    }

}