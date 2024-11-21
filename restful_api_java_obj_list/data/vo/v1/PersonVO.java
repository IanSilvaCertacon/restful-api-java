package br.com.certacon.restful_api_java_obj_list.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "firtsName", "lastName", "addres", "gender"})
public class PersonVO implements Serializable {


    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;


    public PersonVO(){}

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firtsName) {
        this.firstName = firtsName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonVO person = (PersonVO) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(address, person.address) && Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, gender);
    }
}
