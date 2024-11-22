package br.com.certacon.restful_api_java_obj_list.mapper_custom;

import br.com.certacon.restful_api_java_obj_list.data.vo.v1.PersonVO;
import br.com.certacon.restful_api_java_obj_list.data.vo.v2.PersonVOV2;
import br.com.certacon.restful_api_java_obj_list.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PersonMapper {

    public PersonVO toPersonVO(Person person) {
        PersonVO vo = new PersonVO();
        vo.setKey(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());
        return vo;
    }

    public Person toPerson(PersonVO personVO) {
        Person entity = new Person();
        entity.setId(personVO.getKey());
        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());
        return entity;
    }

    public List<PersonVO> toPersonVOList(List<Person> persons) {
        List<PersonVO> vos = new ArrayList<>();
        for (Person person : persons) {
            vos.add(toPersonVO(person));
        }
        return vos;
    }

    public PersonVOV2 convertEntityToVo(Person person) {
        PersonVOV2 vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setAddress(person.getAddress());
        vo.setBirthDay(new Date());
        vo.setGender(person.getGender());
        return vo;
    }

    public Person convertVoToEntity(PersonVOV2 person) {
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return entity;
    }
}
