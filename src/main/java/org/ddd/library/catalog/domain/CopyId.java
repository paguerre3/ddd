package org.ddd.library.catalog.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record CopyId(UUID id) {

    public CopyId {
        // Default "compact" constructor DO validations using "default record argument id".
        Assert.notNull(id,  "ID must not be null");
    }

    public CopyId() {
        this(UUID.randomUUID());
    }
}
