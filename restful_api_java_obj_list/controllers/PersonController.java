package br.com.certacon.restful_api_java_obj_list.controllers;

import br.com.certacon.restful_api_java_obj_list.data.vo.v1.PersonVO;
import br.com.certacon.restful_api_java_obj_list.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public CollectionModel<EntityModel<PersonVO>> findAll() {
        List<PersonVO> people = service.findAll();
        List<EntityModel<PersonVO>> peopleWithLinks = people.stream()
                .map(person -> EntityModel.of(person,
                        linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel()))
                .collect(Collectors.toList());

        return CollectionModel.of(peopleWithLinks,
                linkTo(methodOn(PersonController.class).findAll()).withSelfRel());
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public EntityModel<PersonVO> findById(@PathVariable(value = "id") Long id) {
        PersonVO person = service.findById(id);
        return EntityModel.of(person,
                linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
    }


//    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
//            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
//    public EntityModel<PersonVO> create(@RequestBody PersonVO person) {
//        System.out.println("Create method called"); // Log para debug
//        PersonVO createdPerson = service.create(person);
//        return EntityModel.of(createdPerson,
//                linkTo(methodOn(PersonController.class).findById(createdPerson.getKey())).withSelfRel());
//    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {
        return ResponseEntity.ok(service.create(person));
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public EntityModel<PersonVO> update(@RequestBody PersonVO person) {
        PersonVO updatedPerson = service.update(person);
        return EntityModel.of(updatedPerson,
                linkTo(methodOn(PersonController.class).findById(updatedPerson.getKey())).withSelfRel());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}