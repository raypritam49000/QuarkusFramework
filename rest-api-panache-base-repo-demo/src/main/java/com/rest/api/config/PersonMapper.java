package com.rest.api.config;

import com.rest.api.entity.Person;
import com.rest.api.models.PersonDto;


public interface PersonMapper {

    public static PersonDto personToPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setName(person.getName());
        personDto.setCity(person.getCity());
        personDto.setSalary(person.getSalary());
        return personDto;
    }

    public static Person personDtoToPerson(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setName(personDto.getName());
        person.setCity(personDto.getCity());
        person.setSalary(personDto.getSalary());
        return person;
    }
}
