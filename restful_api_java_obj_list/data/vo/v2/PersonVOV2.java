package br.com.certacon.restful_api_java_obj_list.data.vo.v2;

import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Table(name = "person")
public class PersonVOV2 implements Serializable {


    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    private Date birthDay;

    public PersonVOV2() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public Date getBirthDay() { return birthDay;}

    public void setBirthDay(Date birthDay) { this.birthDay = birthDay;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonVOV2 that = (PersonVOV2) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(gender, that.gender) && Objects.equals(birthDay, that.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, gender, birthDay);
    }
}
