package org.ddd.library.lending.domain;

import org.jmolecules.event.annotation.Externalized;
import org.jmolecules.event.types.DomainEvent;

@Externalized("loans.LoanClosed")
public record LoanClosed(CopyId copyId) implements DomainEvent {
}
