package org.ddd.library.catalog.domain;

import org.springframework.util.Assert;

public record BarCode(String code) {
    public BarCode {
        // business DO verification:
        Assert.notNull(code, "Bar code must not be null");
    }
}
