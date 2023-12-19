package ua.training.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import ua.training.model.Book;
import ua.training.model.BookTranslate;
import ua.training.model.Language;
import ua.training.repository.BookTranslateRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BookTranslateServiceTest {

    @InjectMocks
    private BookTranslateService bookTranslateService;

    @Mock
    private BookTranslateRepository bookTranslateRepository;

    private Language language1;
    private Language language2;
    private Book book1;
    private Book book2;
    private BookTranslate book1TranslateUa;
    private BookTranslate book1TranslateEn;
    private BookTranslate book2TranslateUa;
    private BookTranslate book2TranslateEn;

    @Before
    public void setUp() {
        language1 = new Language("uk");
        language2 = new Language("en");
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
    }

    @Test
    public void addBookTranslate() {
        bookTranslateService.addBookTranslate(book1TranslateUa);

        verify(bookTranslateRepository).save(book1TranslateUa);
    }

    @Test
    public void updateBookTranslate() {
        bookTranslateService.updateBookTranslate(book2TranslateEn);

        verify(bookTranslateRepository).save(book2TranslateEn);
    }

    @Test
    public void findByTitleAndAuthorsString() {
        List<BookTranslate> bookTranslateList1 = Arrays.asList(book1TranslateEn, book2TranslateEn);
        List<BookTranslate> bookTranslateList2 = Arrays.asList(book1TranslateUa, book2TranslateUa);
        List<BookTranslate> bookTranslateList3 = new ArrayList<>();
        when(bookTranslateRepository.findBookTranslatesByTitleAndAuthorsString("Book", "A"))
                .thenReturn(bookTranslateList1);
        when(bookTranslateRepository.findBookTranslatesByTitleAndAuthorsString("Книга", "Ав"))
                .thenReturn(bookTranslateList2);
        when(bookTranslateRepository.findBookTranslatesByTitleAndAuthorsString("Title", "QQ"))
                .thenReturn(bookTranslateList3);

        List<BookTranslate> list1 = bookTranslateRepository
                .findBookTranslatesByTitleAndAuthorsString("Book", "A");
        List<BookTranslate> list2 = bookTranslateRepository
                .findBookTranslatesByTitleAndAuthorsString("Книга", "Ав");
        List<BookTranslate> list3 = bookTranslateRepository
                .findBookTranslatesByTitleAndAuthorsString("Title", "QQ");

        assertEquals(bookTranslateList1.size(), list1.size());
        assertEquals(bookTranslateList2.size(), list2.size());
        assertEquals(bookTranslateList3.size(), list3.size());
    }

}