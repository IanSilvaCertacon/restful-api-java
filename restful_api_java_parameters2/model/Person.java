package br.com.certacon.restful_api_java_parameters2.model;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {

    private Long id;
    private String firtsName;
    private String lastName;
    private String addres;
    private String gender;

    public Person(){}

    public void setFirtsName(String firtsName) {
        this.firtsName = firtsName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirtsName() {
        return firtsName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddres() {
        return addres;
    }

    public String getGender() {
        return gender;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firtsName, person.firtsName) && Objects.equals(lastName, person.lastName) && Objects.equals(addres, person.addres) && Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firtsName, lastName, addres, gender);
    }
}
