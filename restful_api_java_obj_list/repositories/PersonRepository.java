package br.com.certacon.restful_api_java_obj_list.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.certacon.restful_api_java_obj_list.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}
