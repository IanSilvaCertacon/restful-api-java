package br.com.certacon.restful_api_java_obj_list.services;

import br.com.certacon.restful_api_java_obj_list.controllers.BookController;
import br.com.certacon.restful_api_java_obj_list.data.vo.v1.BookVO;
import br.com.certacon.restful_api_java_obj_list.data.vo.v2.BookVOV2;
import br.com.certacon.restful_api_java_obj_list.exceptions.ResourceNotFoundException;
import br.com.certacon.restful_api_java_obj_list.mapper_custom.BookMapper;
import br.com.certacon.restful_api_java_obj_list.model.Book;
import br.com.certacon.restful_api_java_obj_list.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private static final Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    @Autowired
    BookMapper mapper; // Usando MapStruct como mapper

    public List<BookVO> findAll() {
        logger.info("Finding all book!");
        List<Book> books = repository.findAll();
        List<BookVO> bookVOs = mapper.toBookVOList(books);
        bookVOs.forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
        return bookVOs;
    }

    public BookVO findById(Long id) {
        logger.info("Finding one book!");
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        BookVO vo = mapper.toBookVO(entity);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public BookVO create(BookVO book) {
        logger.info("Creating one book! Received data: " + book);
        try {
            Book entity = mapper.toBook(book);
            logger.info("Converted to entity: " + entity);
            Book savedEntity = repository.save(entity);
            logger.info("Saved entity: " + savedEntity);
            BookVO result = mapper.toBookVO(savedEntity);
            logger.info("Returning result: " + result);
            return result;
        } catch (Exception e) {
            logger.severe("Error creating book: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public BookVOV2 createV2(BookVOV2 book) {
        logger.info("Creating one book with V2!");
        // Usando o mapper customizado para o mapeamento V2
        Book entity = mapper.convertVoToEntity(book);
        Book savedEntity = repository.save(entity);
        return mapper.convertEntityToVo(savedEntity);
    }

    public BookVO update(BookVO book) {
        logger.info("Updating one book!");
        Book entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        // Salvando a entidade atualizada
        Book savedEntity = repository.save(entity);
        // Convertendo a entidade salva para BookVO
        return mapper.toBookVO(savedEntity);
    }

    public void delete(Long id) {
        logger.info("Deleting one book!");
        // Encontrando a entidade no banco para deletar
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}