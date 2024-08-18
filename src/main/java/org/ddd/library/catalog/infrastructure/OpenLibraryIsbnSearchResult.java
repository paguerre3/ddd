package org.ddd.library.catalog.infrastructure;

import java.util.List;

// must not be of "public" visibility in order to avoid results inside lower layers as application.
// correct dependency is infra depends on application and not vise versa. this is a protection.
record OpenLibraryIsbnSearchResult(List<String> publishers,
                                          String title,
                                          List<String> isbn13,
                                          int revisions) {
}
