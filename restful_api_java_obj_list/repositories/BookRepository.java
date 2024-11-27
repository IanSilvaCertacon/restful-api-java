package br.com.certacon.restful_api_java_obj_list.repositories;


import br.com.certacon.restful_api_java_obj_list.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
