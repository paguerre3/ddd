package org.ddd.library.lending.domain;

import org.jmolecules.event.types.DomainEvent;

public record LoanClosed(CopyId copyId) implements DomainEvent {
}
