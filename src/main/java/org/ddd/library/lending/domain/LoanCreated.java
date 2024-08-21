package org.ddd.library.lending.domain;

import org.jmolecules.event.annotation.Externalized;
import org.jmolecules.event.types.DomainEvent;

@Externalized("loans.LoanCreated")
public record LoanCreated(CopyId copyId) implements DomainEvent {
}
