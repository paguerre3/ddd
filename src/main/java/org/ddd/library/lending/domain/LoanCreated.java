package org.ddd.library.lending.domain;

import org.springframework.modulith.NamedInterface;

@NamedInterface
public record LoanCreated(CopyId copyId) {
}
