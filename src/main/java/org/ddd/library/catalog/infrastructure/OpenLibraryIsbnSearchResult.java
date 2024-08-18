package org.ddd.library.catalog.infrastructure;

import java.util.List;

public record OpenLibraryIsbnSearchResult(List<String> publishers,
                                          String title,
                                          List<String> isbn13,
                                          int revisions) {
}
