package org.ddd.library.catalog;

import org.apache.commons.validator.routines.ISBNValidator;

public record Isbn(String value) {
    private static final ISBNValidator VALIDATOR = new ISBNValidator();

    /**
     * Default "compact" constructor DO validations using "default record argument value".
     * @param value String
     */
    public Isbn {
        // Core business DO verification:
        if (!VALIDATOR.isValid(value)) {
            throw new IllegalArgumentException("Invalid ISBN: " + value);
        }
    }
}
