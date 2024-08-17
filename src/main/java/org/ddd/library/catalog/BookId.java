package org.ddd.library.catalog;

import org.springframework.util.Assert;

import java.util.UUID;

public record BookId(UUID id) {

    /**
     * Default "compact" constructor validations using "default record argument id".
     * @param id UUID
     */
    public BookId {
        Assert.notNull(id,  "ID must not be null");
    }

    /**
     * Default constructor using no argument.
     */
    public BookId() {
        // call compact constructor inside:
        this(UUID.randomUUID());
    }
}
