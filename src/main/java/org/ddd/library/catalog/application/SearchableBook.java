package org.ddd.library.catalog.application;

import org.ddd.library.catalog.domain.Isbn;

public interface SearchableBook {
    BookInformation search(Isbn isbn);
}
