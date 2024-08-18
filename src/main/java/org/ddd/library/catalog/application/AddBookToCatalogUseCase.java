package org.ddd.library.catalog.application;

import org.ddd.library.UseCase;
import org.ddd.library.catalog.domain.Book;
import org.ddd.library.catalog.domain.BookRepository;
import org.ddd.library.catalog.domain.Isbn;

@UseCase
public class AddBookToCatalogUseCase {
    private final BookSearchService searchService;
    private final BookRepository bookRepository;

    public AddBookToCatalogUseCase(BookSearchService searchService,
                                   BookRepository bookRepository) {
        this.searchService = searchService;
        this.bookRepository = bookRepository;
    }

    public void execute(Isbn isbn) {
        BookInformation bookInfo = this.searchService.search(isbn);
        this.bookRepository.save(new Book(bookInfo.title(), isbn));
    }
}
