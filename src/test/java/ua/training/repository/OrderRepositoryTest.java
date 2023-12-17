package ua.training.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.training.model.*;
import ua.training.model.enums.OrderStatus;
import ua.training.model.enums.Role;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookTranslateRepository bookTranslateRepository;
    @Autowired
    private OrderRepository orderRepository;

    private User user1;
    private User user2;
    private User user3;
    private Language language1;
    private Language language2;
    private Book book1;
    private Book book2;
    private BookTranslate book1TranslateUa;
    private BookTranslate book1TranslateEn;
    private BookTranslate book2TranslateUa;
    private BookTranslate book2TranslateEn;
    private Order order11;
    private Order order12;
    private Order order13;
    private Order order21;
    private Order order22;

    @Before
    public void setUp() {
        user1 = new User.Builder()
                .login("user1")
                .password("password1")
                .isBlocked(false)
                .role(Role.READER)
                .build();
        user2 = new User.Builder()
                .login("user2")
                .password("password2")
                .isBlocked(false)
                .role(Role.READER)
                .build();
        user3 = new User.Builder()
                .login("user3")
                .password("password3")
                .isBlocked(false)
                .role(Role.ADMIN)
                .build();
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        language1 = new Language("uk");
        language2 = new Language("en");
        languageRepository.save(language1);
        languageRepository.save(language2);

        book1 = new Book.Builder()
                .publicationDate(LocalDate.now())
                .price(new BigDecimal(120))
                .amount(20)
                .build();
        book2 = new Book.Builder()
                .publicationDate(LocalDate.now())
                .price(new BigDecimal(10))
                .amount(100)
                .build();

        book1 = bookRepository.save(book1);
        book2 = bookRepository.save(book2);

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
                .language(language1)
                .editionName("Edition")
                .languageOfBook("Українська")
                .build();
        book2TranslateEn = new BookTranslate.Builder()
                .title("Book2")
                .book(book2)
                .authorsString("Author2")
                .description("Description2")
                .language(language1)
                .editionName("Edition")
                .languageOfBook("Українська")
                .build();
        bookTranslateRepository.save(book1TranslateUa);
        bookTranslateRepository.save(book1TranslateEn);
        bookTranslateRepository.save(book2TranslateUa);
        bookTranslateRepository.save(book2TranslateEn);

        order11 = new Order.Builder()
                .user(user1)
                .book(book1)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .orderStatus(OrderStatus.RECEIVED)
                .build();
        order12 = new Order.Builder()
                .user(user1)
                .book(book1)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .orderStatus(OrderStatus.RECEIVED)
                .build();
        order13 = new Order.Builder()
                .user(user1)
                .book(book2)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .orderStatus(OrderStatus.APPROVED)
                .build();
        order21 = new Order.Builder()
                .user(user2)
                .book(book1)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .orderStatus(OrderStatus.APPROVED)
                .build();
        order22 = new Order.Builder()
                .user(user2)
                .book(book2)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .orderStatus(OrderStatus.RECEIVED)
                .build();
        orderRepository.save(order11);
        orderRepository.save(order12);
        orderRepository.save(order13);
        orderRepository.save(order21);
        orderRepository.save(order22);
    }

    @After
    public void tearDown() {
        userRepository.delete(user1);
        userRepository.delete(user2);
        userRepository.delete(user3);
        languageRepository.delete(language1);
        languageRepository.delete(language2);
        bookRepository.delete(book1);
        bookRepository.delete(book2);
        bookTranslateRepository.delete(book1TranslateUa);
        bookTranslateRepository.delete(book1TranslateEn);
        bookTranslateRepository.delete(book2TranslateUa);
        bookTranslateRepository.delete(book2TranslateEn);
        orderRepository.delete(order11);
        orderRepository.delete(order12);
        orderRepository.delete(order13);
        orderRepository.delete(order21);
        orderRepository.delete(order22);
    }

}