package ua.training.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.training.model.Book;
import ua.training.model.BookTranslate;
import ua.training.model.Language;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookTranslateRepository extends CrudRepository<BookTranslate, Long> {
    List<BookTranslate> findBookTranslatesByTitleAndAuthorsString(String title, String authorsString);
    Optional<BookTranslate> findByBookAndLanguage(Book book, Language language);
    void deleteAllByBook(Book book);
}