package org.ddd.library.lending.domain;

import org.jmolecules.event.types.DomainEvent;

public record LoanCreated(CopyId copyId) implements DomainEvent {
}
