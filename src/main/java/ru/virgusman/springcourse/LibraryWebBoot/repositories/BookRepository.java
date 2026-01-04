package ru.virgusman.springcourse.LibraryWebBoot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.virgusman.springcourse.LibraryWebBoot.models.Book;
import ru.virgusman.springcourse.LibraryWebBoot.models.Person;


import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByNameAndAuthorAndYear(String name, String author, int year);

    List<Book> findByOwner(Person owner);

    List<Book> findByNameContainingIgnoreCaseOrAuthorContainingIgnoreCase(String name, String author);


}
