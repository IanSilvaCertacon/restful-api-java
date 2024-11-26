package br.com.certacon.restful_api_java_obj_list.controllers;

import br.com.certacon.restful_api_java_obj_list.data.vo.v1.PersonVO;
import br.com.certacon.restful_api_java_obj_list.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "People", description = "Endpoints for Managing people")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Finds all People", description = "Finds all People", tags = {"People"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
            @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
            )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
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
    @Operation(summary = "Finds a Person", description = "Finds a Person", tags = {"People"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    public EntityModel<PersonVO> findById(@PathVariable(value = "id") Long id) {
        PersonVO person = service.findById(id);
        return EntityModel.of(person,
                linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Adds a new Person",
            description = "Adds a new Person by passing in a JSON, XML, YML representation of the Person!",
            tags = {"People"},
            responses = {
            @ApiResponse(description = "Updated", responseCode = "200", content =
                    @Content(schema = @Schema(implementation = PersonVO.class)
                    )
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {
        return ResponseEntity.ok(service.create(person));
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Adds a new Person",
            description = "Adds a new Person by passing in a JSON, XML, YML representation of the Person!",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200", content =
                            @Content(schema = @Schema(implementation = PersonVO.class)
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })

    public EntityModel<PersonVO> update(@RequestBody PersonVO person) {
        PersonVO updatedPerson = service.update(person);
        return EntityModel.of(updatedPerson,
                linkTo(methodOn(PersonController.class).findById(updatedPerson.getKey())).withSelfRel());
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Person",
            description = "Deletes a Person by passing in a JSON, XML, YML representation of the Person!",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}