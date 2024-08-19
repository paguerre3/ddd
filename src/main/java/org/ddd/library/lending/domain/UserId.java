package org.ddd.library.lending.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record UserId(UUID id) {
    // default constructor validations using "default record argument id"
    public UserId {
        // validation code here
        Assert.notNull(id, "ID must not be null");
    }

    public UserId(){
        this(UUID.randomUUID());
    }
}
