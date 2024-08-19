package org.ddd.library.lending.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record LoanId(UUID id) {
    public LoanId {
        Assert.notNull(id, "ID must not be null");
    }

    public LoanId() {
        this(UUID.randomUUID());
    }
}
