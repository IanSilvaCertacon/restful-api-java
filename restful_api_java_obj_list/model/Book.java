package br.com.certacon.restful_api_java_obj_list.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author", nullable = false, length = 180)
    private String author ;

    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date launchDate;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, length = 250)
    private String title;

    public Book(){}

    public Long getKey() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLaunchDate(Date lauchDate) {
        this.launchDate = lauchDate;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book books = (Book) o;
        return Objects.equals(id, books.id) && Objects.equals(author, books.author) && Objects.equals(launchDate, books.launchDate) && Objects.equals(price, books.price) && Objects.equals(title, books.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, launchDate, price, title);
    }
}


