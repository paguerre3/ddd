package org.ddd.library.catalog;

import org.apache.commons.validator.routines.ISBNValidator;

public record Isbn(String value) {
    private static final ISBNValidator VALIDATOR = new ISBNValidator();

    /**
     * Default "compact" constructor validations using "default record argument value".
     * @param value String
     */
    public Isbn {
        if (!VALIDATOR.isValid(value)) {
            throw new IllegalArgumentException("Invalid ISBN: " + value);
        }
    }
}
