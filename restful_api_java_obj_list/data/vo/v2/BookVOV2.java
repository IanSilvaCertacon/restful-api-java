package br.com.certacon.restful_api_java_obj_list.data.vo.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.mapstruct.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "author", "launch_date", "price", "title"})
public class BookVOV2 extends RepresentationModel<BookVOV2> implements Serializable {

    @JsonProperty("id")
    private Long key;

    @JsonProperty("author")
    private String author;

    @JsonProperty("launch_date")
    private Date launch_date;

    private Double price;
    private String title;


    public BookVOV2(){}

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getLaunchDate() {
        return launch_date;
    }

    public void setLaunchDate(Date launch_date) {
        this.launch_date = launch_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookVOV2 bookVO = (BookVOV2) o;
        return Objects.equals(key, bookVO.key) && Objects.equals(author, bookVO.author) && Objects.equals(launch_date, bookVO.launch_date) && Objects.equals(price, bookVO.price) && Objects.equals(title, bookVO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, author, launch_date, price, title);
    }
}
