package org.ddd.library.lending.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record CopyId(UUID id) {
    // Default "compact" constructor validations using "default record argument id".
    public CopyId {
        // Validation logic here
        Assert.notNull(id,  "ID must not be null");
    }

    public CopyId() {
        this(UUID.randomUUID());
    }
}
