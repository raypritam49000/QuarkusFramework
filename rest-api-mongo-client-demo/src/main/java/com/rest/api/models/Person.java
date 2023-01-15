package com.rest.api.models;

public class Person {
    private String id;
    private String name;
    private String city;
    private String salary;

    public Person(String id, String name, String city, String salary) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.salary = salary;
    }

    public Person(String name, String city, String salary) {
        this.name = name;
        this.city = city;
        this.salary = salary;
    }

    public Person() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
