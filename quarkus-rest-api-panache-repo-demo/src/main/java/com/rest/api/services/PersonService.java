package com.rest.api.services;

import com.rest.api.models.PersonDto;

import java.util.List;

public interface PersonService {
    public List<PersonDto> getAllPersons();
    public PersonDto getPerson(Long id);
    public PersonDto createPerson(PersonDto person);
    public Boolean deletePerson(Long id);
    public PersonDto updatePerson(Long id,PersonDto person);
}
