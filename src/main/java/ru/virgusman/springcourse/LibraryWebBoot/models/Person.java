package ru.virgusman.springcourse.LibraryWebBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Person")
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Поле обязательно к заполнению")
    @Pattern(regexp = "([А-ЯЁ][а-яА-ЯёЁ\\-]+\\s){2}[А-ЯЁ][а-яё]+",
            message = "Должен быть формат (Фамилия Имя Отчество) на русском языке")
    @Column(name = "fullname")
    private String fullName;  //ФИО
    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    @Digits(integer = 4, fraction = 0, message = "Год должен быть указан в цифровом формате (1900)")
    @Column(name = "yearofbirth")
    private int yearOfBirth;  //Год рождения

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person(int id, String fullName, int yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }
}
