package com.rest.api.service.impl;

import com.rest.api.dto.PersonDto;
import com.rest.api.entity.Person;
import com.rest.api.repository.PersonRepository;
import com.rest.api.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Transactional
public class PersonServiceImpl implements PersonService {

    @Inject
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PersonDto> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        List<PersonDto> personDtoList = persons.stream().map((person -> this.modelMapper.map(person, PersonDto.class))).collect(Collectors.toList());
        return personDtoList;
    }

    @Override
    public PersonDto getPerson(Long id) {
        Person person = personRepository.findById(id).get();
        PersonDto personDto = null;
        if (person != null) {
            personDto = this.modelMapper.map(person, PersonDto.class);
        } else {
            throw new WebApplicationException("Person with id of " + id + " does not exist.", 404);
        }
        return personDto;
    }

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person person = this.modelMapper.map(personDto, Person.class);
        Person savePerson = this.personRepository.save(person);

        if (savePerson != null) {
            return this.modelMapper.map(savePerson, PersonDto.class);
        } else {
            throw new PersistenceException();
        }

    }

    @Override
    public Boolean deletePerson(Long id) {
        Person person = personRepository.findById(id).get();
        if (person == null) {
            throw new WebApplicationException("Person with id of " + id + " does not exist.", 404);
        } else if (person != null) {
            personRepository.delete(person);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PersonDto updatePerson(Long id, PersonDto personDto) {
        Person person = personRepository.findById(id).get();
        if (person == null) {
            throw new WebApplicationException("Person with id of " + id + " does not exist.", 404);
        }
        person.setName(personDto.getName());
        person.setCity(personDto.getCity());
        person.setSalary(personDto.getSalary());

        Person updatePerson = personRepository.save(person);

        if (updatePerson != null) {
            return this.modelMapper.map(updatePerson, PersonDto.class);
        } else {
            throw new PersistenceException();
        }

    }


}
