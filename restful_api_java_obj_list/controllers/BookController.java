package br.com.certacon.restful_api_java_obj_list.controllers;

import br.com.certacon.restful_api_java_obj_list.data.vo.v1.BookVO;
import br.com.certacon.restful_api_java_obj_list.services.BookServices;
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
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing book")
public class BookController {

    @Autowired
    private BookServices service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Finds all Book", description = "Finds all Book", tags = {"Book"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
            @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
            )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    public CollectionModel<EntityModel<BookVO>> findAll() {
        List<BookVO> books = service.findAll();
        List<EntityModel<BookVO>> bookWithLinks = books.stream()
                .map(book -> EntityModel.of(book,
                        linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel()))
                .collect(Collectors.toList());

        return CollectionModel.of(bookWithLinks,
                linkTo(methodOn(BookController.class).findAll()).withSelfRel());
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Finds a Book", description = "Finds a Book", tags = {"Book"}, responses = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(description = "No content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    public EntityModel<BookVO> findById(@PathVariable(value = "id") Long id) {
        BookVO book = service.findById(id);
        return EntityModel.of(book,
                linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Adds a new Book",
            description = "Adds a new Book by passing in a JSON, XML, YML representation of the Book!",
            tags = {"Book"},
            responses = {
            @ApiResponse(description = "Updated", responseCode = "200", content =
                    @Content(schema = @Schema(implementation = BookVO.class)
                    )
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    public ResponseEntity<BookVO> create(@RequestBody BookVO book) {
        return ResponseEntity.ok(service.create(book));
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @Operation(summary = "Adds a new Book",
            description = "Adds a new Book by passing in a JSON, XML, YML representation of the Book!",
            tags = {"Book"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200", content =
                            @Content(schema = @Schema(implementation = BookVO.class)
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            })

    public EntityModel<BookVO> update(@RequestBody BookVO book) {
        BookVO updatedBook = service.update(book);
        return EntityModel.of(updatedBook,
                linkTo(methodOn(BookController.class).findById(updatedBook.getKey())).withSelfRel());
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Book",
            description = "Deletes a Book by passing in a JSON, XML, YML representation of the Book!",
            tags = {"Book"},
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