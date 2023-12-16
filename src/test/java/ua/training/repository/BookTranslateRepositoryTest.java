package ua.training.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import ua.training.model.Book;
import ua.training.model.BookTranslate;
import ua.training.model.Language;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookTranslateRepositoryTest {

    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookTranslateRepository bookTranslateRepository;

    private Language language1;
    private Language language2;
    private Language language3;
    private Book book1;
    private Book book2;
    private Book book3;
    private BookTranslate book1TranslateUa;
    private BookTranslate book1TranslateEn;
    private BookTranslate book2TranslateUa;
    private BookTranslate book2TranslateEn;

    @Before
    public void setUp() {
        language1 = new Language("uk");
        language2 = new Language("en");
        language3 = new Language("de");
        languageRepository.save(language1);
        languageRepository.save(language2);
        languageRepository.save(language3);

        book1 = new Book.Builder()
                .publicationDate(LocalDate.now())
                .price(new BigDecimal(120))
                .amount(20)
                .build();
        book2 = new Book.Builder()
                .publicationDate(LocalDate.now().plusYears(3))
                .price(new BigDecimal(10))
                .amount(100)
                .build();
        book3 = new Book.Builder()
                .publicationDate(LocalDate.now())
                .amount(120)
                .price(new BigDecimal(1200))
                .build();
        book1 = bookRepository.save(book1);
        book2 = bookRepository.save(book2);
        book3 = bookRepository.save(book3);

        book1TranslateUa = new BookTranslate.Builder()
                .title("Книга1")
                .book(book1)
                .authorsString("Автор1")
                .description("Опис1")
                .language(language1)
                .editionName("Видання")
                .languageOfBook("Українська")
                .build();
        book2TranslateUa = new BookTranslate.Builder()
                .title("Книга2")
                .book(book2)
                .authorsString("Автор2")
                .description("Опис2")
                .language(language1)
                .editionName("Видання")
                .languageOfBook("Українська")
                .build();
        book1TranslateEn = new BookTranslate.Builder()
                .title("Book1")
                .book(book1)
                .authorsString("Author1")
                .description("Description1")
                .language(language2)
                .editionName("Edition")
                .languageOfBook("Українська")
                .build();
        book2TranslateEn = new BookTranslate.Builder()
                .title("Book2")
                .book(book2)
                .authorsString("Author2")
                .description("Description2")
                .language(language2)
                .editionName("Edition")
                .languageOfBook("Українська")
                .build();
        bookTranslateRepository.save(book1TranslateUa);
        bookTranslateRepository.save(book1TranslateEn);
        bookTranslateRepository.save(book2TranslateUa);
        bookTranslateRepository.save(book2TranslateEn);
    }

    @After
    public void tearDown() {
        languageRepository.delete(language1);
        languageRepository.delete(language2);
        languageRepository.delete(language3);
        bookRepository.delete(book1);
        bookRepository.delete(book2);
        bookRepository.delete(book3);
        bookTranslateRepository.delete(book1TranslateUa);
        bookTranslateRepository.delete(book1TranslateEn);
        bookTranslateRepository.delete(book2TranslateUa);
        bookTranslateRepository.delete(book2TranslateEn);
    }

    @Test
    public void findBookTranslatesByTitleAndAuthorsString() {
        List<BookTranslate> bookTranslates = bookTranslateRepository
                .findBookTranslatesByTitleAndAuthorsString(book1TranslateUa.getTitle(), book1TranslateUa.getAuthorsString());

        assertEquals(1, bookTranslates.size());
    }

    @Test
    public void findByBookAndLanguage() {
        BookTranslate bookTranslate = bookTranslateRepository.findByBookAndLanguage(book1, language1)
                .orElseThrow(() -> new NoSuchElementException("There is no such a book translation"));

        assertEquals(book1TranslateUa.getTitle(), bookTranslate.getTitle());
        assertEquals(book1TranslateUa.getAuthorsString(), bookTranslate.getAuthorsString());
        assertEquals(book1TranslateUa.getLanguageOfBook(), bookTranslate.getLanguageOfBook());
        assertEquals(book1TranslateUa.getDescription(), bookTranslate.getDescription());
        assertEquals(book1TranslateUa.getEditionName(), bookTranslate.getEditionName());
    }

    @Test(expected = NoSuchElementException.class)
    public void findByBookAndNoExistsLanguage() {
        bookTranslateRepository.findByBookAndLanguage(book1, language3)
                .orElseThrow(() -> new NoSuchElementException("There is no such a book translation"));
    }

    @Test(expected = NoSuchElementException.class)
    public void findByNoExistsBookAndLanguage() {
        bookTranslateRepository.findByBookAndLanguage(book3, language1)
                .orElseThrow(() -> new NoSuchElementException("There is no such a book translation"));
    }

    @Test
    public void findAllByLanguage() {
        List<BookTranslate> bookTranslates = bookTranslateRepository
                .findBookTranslatesByTitleAndAuthorsString(book1TranslateUa.getTitle(),
                        book1TranslateUa.getAuthorsString());

        assertEquals(1, bookTranslates.size());
    }

}