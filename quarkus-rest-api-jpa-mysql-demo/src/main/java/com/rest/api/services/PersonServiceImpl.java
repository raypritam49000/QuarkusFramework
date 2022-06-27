package com.rest.api.services;

import com.rest.api.config.PersonMapper;
import com.rest.api.entity.Person;
import com.rest.api.models.PersonDto;

import javax.inject.Singleton;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Singleton
@Transactional
public class PersonServiceImpl implements PersonService {

    @Override
    public List<PersonDto> getAllPersons() {
        List<Person> persons = Person.findAllPerson();
        List<PersonDto> personDtoList = persons.stream().map((person -> PersonMapper.personToPersonDto(person))).collect(Collectors.toList());
        return personDtoList;
    }

    @Override
    public PersonDto getPerson(Long id) {
        Person person = Person.findById(id);
        PersonDto personDto = null;
        if (person != null) {
            personDto = PersonMapper.personToPersonDto(person);
        } else {
            throw new WebApplicationException("Person with id of " + id + " does not exist.", 404);
        }
        return personDto;
    }

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person person = PersonMapper.personDtoToPerson(personDto);
        Person.savePerson(person);

        if (person.isPersistent()) {
            Optional<Person> optionalPerson = Person.findByIdOptional(person.id);
            person = optionalPerson.orElseThrow(NotFoundException::new);
            return PersonMapper.personToPersonDto(person);
        } else {
            throw new PersistenceException();
        }

    }

    @Override
    public Boolean deletePerson(Long id) {
        Person person = Person.findById(id);
        if (person == null) {
            throw new WebApplicationException("Person with id of " + id + " does not exist.", 404);
        }
        return Person.delete(person.id);
    }

    @Override
    public PersonDto updatePerson(Long id, PersonDto personDto) {
        Person person = Person.findById(id);
        if (person == null) {
            throw new WebApplicationException("Person with id of " + id + " does not exist.", 404);
        }
        person.name = personDto.getName();
        person.city = personDto.getCity();
        person.salary = personDto.getSalary();

        Person.savePerson(person);

        if (person.isPersistent()) {
            Optional<Person> optionalPerson = Person.findByIdOptional(person.id);
            person = optionalPerson.orElseThrow(NotFoundException::new);
            return PersonMapper.personToPersonDto(person);
        } else {
            throw new PersistenceException();
        }

    }


}
