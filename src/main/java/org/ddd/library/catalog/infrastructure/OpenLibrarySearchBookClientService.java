package org.ddd.library.catalog.infrastructure;

import org.ddd.library.catalog.application.BookInformation;
import org.ddd.library.catalog.application.SearchableBook;
import org.ddd.library.catalog.domain.Isbn;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class OpenLibrarySearchBookClientService implements SearchableBook {
    private final RestClient restClient;

    /**
     * Default constructor where IoC injections are performed instead of using autowired annotation.
     *
     * @param builder RestClient.Builder
     */
    public OpenLibrarySearchBookClientService(RestClient.Builder builder) {
        this.restClient = builder.
                baseUrl("https://openlibrary.org/").
                build();
    }

    @Override
    public BookInformation search(Isbn isbn) {
        var openLibResult = restClient.get().uri("isbn/{isbn}.json", isbn.value()).
                retrieve().
                body(OpenLibraryIsbnSearchResult.class);
        return new BookInformation(Optional.ofNullable(openLibResult).
                map(OpenLibraryIsbnSearchResult::title).
                orElse(null));
    }
}
