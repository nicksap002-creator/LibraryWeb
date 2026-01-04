package ru.virgusman.springcourse.LibraryWebBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле обязательно к заполнению")
    @Column(name = "name")
    private String name;     //Название книги
    @Column(name = "author")
    private String author;   //Автор книги
    @Min(value = 1000, message = "Год выпуска должен быть больше 1000")
    @Digits(integer = 4, fraction = 0, message = "Год должен быть указан в цифровом формате (1900)")
    @Column(name = "year")
    private int year;     //Год издания

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "date_issue")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateIssue;

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public boolean isExpired() {
        return (new Date().getTime() - dateIssue.getTime()) / 86400000 > 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && Objects.equals(name, book.name) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, year);
    }

}
