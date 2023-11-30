package ua.training.converter;

import ua.training.dto.BookDto;
import ua.training.dto.BookTranslateDto;
import ua.training.model.Book;
import ua.training.model.BookTranslate;
public class BookConverter {

    /**
     */
    public BookTranslateDto convertDtoIntoBookTranslate(BookTranslate bookTranslate) {
        BookTranslateDto bookTranslateDto = new BookTranslateDto();
        bookTranslateDto.setTitle(bookTranslate.getTitle());
        bookTranslateDto.setAuthorsString(bookTranslate.getAuthorsString());
        bookTranslateDto.setDescription(bookTranslate.getDescription());
        bookTranslateDto.setBookLanguage(bookTranslate.getLanguageOfBook());
        bookTranslateDto.setEdition(bookTranslate.getEditionName());
        return bookTranslateDto;
    }

    /**
     */
    public BookDto convertBookIntoDto(Book book) {
        return new BookDto(book.getPublicationDate(), book.getPrice(), book.getAmount());
    }

}
