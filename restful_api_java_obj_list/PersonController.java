package br.com.certacon.restful_api_java_obj_list;

import br.com.certacon.restful_api_java_obj_list.exceptions.ResourceNotFoundException;
import br.com.certacon.restful_api_java_obj_list.model.Person;
import br.com.certacon.restful_api_java_obj_list.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired //junto com o @Service ele linka um com o outro igual o comentario abaixo
    private PersonServices service;
    //private PersonServices service = new PersonServices();

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll(){
         return service.findAll();
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findbyId(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person) {
        return service.create(person);
    }

    @PutMapping (consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person) {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();    }
}


