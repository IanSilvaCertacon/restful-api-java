package br.com.certacon.restful_api_java_obj_list.repositories;


import br.com.certacon.restful_api_java_obj_list.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
