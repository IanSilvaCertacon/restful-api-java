package br.com.certacon.restful_api_java_obj_list.data.vo.v2;

import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Table(name = "person")
public class PersonVOV2 implements Serializable {


    private Long id;
    private String firtsName;
    private String lastName;
    private String addres;
    private String gender;

    private Date birthDay;

    public PersonVOV2() {
    }

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

    public Date getBirthDay() { return birthDay;}

    public void setBirthDay(Date birthDay) { this.birthDay = birthDay;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonVOV2 that = (PersonVOV2) o;
        return Objects.equals(id, that.id) && Objects.equals(firtsName, that.firtsName) && Objects.equals(lastName, that.lastName) && Objects.equals(addres, that.addres) && Objects.equals(gender, that.gender) && Objects.equals(birthDay, that.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firtsName, lastName, addres, gender, birthDay);
    }
}
