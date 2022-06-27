package com.rest.api.config;

import com.rest.api.entity.Person;
import com.rest.api.models.PersonDto;


public interface PersonMapper {

    public static PersonDto personToPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.id);
        personDto.setName(person.name);
        personDto.setCity(person.city);
        personDto.setSalary(person.salary);
        return personDto;
    }

    public static Person personDtoToPerson(PersonDto personDto) {
        Person person = new Person();
        person.id = personDto.getId();
        person.name = personDto.getName();
        person.city = personDto.getCity();
        person.salary = personDto.getSalary();
        return person;
    }
}
