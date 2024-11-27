package br.com.certacon.restful_api_java_obj_list.mapper_custom;

import br.com.certacon.restful_api_java_obj_list.data.vo.v1.BookVO;
import br.com.certacon.restful_api_java_obj_list.data.vo.v2.BookVOV2;
import br.com.certacon.restful_api_java_obj_list.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookMapper {

    public List<BookVO> toBookVOList(List<Book> books) {
        List<BookVO> voList = new ArrayList<>();
        for (Book book : books) {
            voList.add(toBookVO(book));
        }
        return voList;
    }

    public BookVO toBookVO(Book book) {
        BookVO vo = new BookVO();
        vo.setKey(book.getKey());
        vo.setAuthor(book.getAuthor());
        vo.setLaunchDate(book.getLaunchDate());
        vo.setPrice(book.getPrice());
        vo.setTitle(book.getTitle());
        return vo;
    }

    public Book toBook(BookVO bookVO) {
        Book entity = new Book();
        entity.setId(bookVO.getKey());
        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunchDate(bookVO.getLaunchDate());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());
        return entity;
    }

    public BookVOV2 convertEntityToVo(Book book) {
        BookVOV2 vo = new BookVOV2();
        vo.setKey(book.getKey());
        vo.setAuthor(book.getAuthor());
        vo.setLaunchDate(book.getLaunchDate());
        vo.setPrice(book.getPrice());
        vo.setTitle(book.getTitle());
        return vo;
    }

    public Book convertVoToEntity(BookVOV2 bookV2) {
        Book entity = new Book();
        entity.setId(bookV2.getKey());
        entity.setAuthor(bookV2.getAuthor());
        entity.setLaunchDate(bookV2.getLaunchDate());
        entity.setPrice(bookV2.getPrice());
        entity.setTitle(bookV2.getTitle());
        return entity;
    }
}