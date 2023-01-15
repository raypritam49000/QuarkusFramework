package com.rest.api.dao;

import com.rest.api.models.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PersonMapper {

    @Select("SELECT * FROM person")
    public abstract List<Person> getAllPersons();

    @Select("SELECT * FROM person WHERE id = #{id}")
    public abstract Person getPerson(Integer id);

    @Delete("DELETE FROM person WHERE id = #{id}")
    public abstract Integer removePerson(Integer id);

    @Insert("INSERT INTO person(name,city,salary) VALUES (#{name}, #{city},#{salary})")
    public abstract Integer createPerson(Person person);

    @Update("update person set name=#{name},city=#{city},salary=#{salary} where id=#{id}")
    public abstract Integer updatePerson(Person person);
}


