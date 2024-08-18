package org.ddd.library.catalog.application;

import org.ddd.library.catalog.domain.Isbn;

public interface BookSearchService {
    BookInformation search(Isbn isbn);
}
