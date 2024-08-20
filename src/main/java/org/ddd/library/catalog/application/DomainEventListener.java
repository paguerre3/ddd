package org.ddd.library.catalog.application;

import org.ddd.library.catalog.domain.Copy;
import org.ddd.library.catalog.domain.CopyId;
import org.ddd.library.catalog.domain.CopyRepository;
import org.ddd.library.lending.domain.LoanClosed;
import org.ddd.library.lending.domain.LoanCreated;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class DomainEventListener {
    private final CopyRepository copyRepo;

    public DomainEventListener(CopyRepository copyRep) {
        this.copyRepo = copyRep;
    }

    @ApplicationModuleListener
    public void handle(LoanCreated event) {
        Copy copy = copyRepo.findById(new CopyId(event.copyId().id())).orElseThrow();
        // listen to LoanCreated event from Loan constructor/RentBookUseCase and ensure to mark copy as "unavailable"
        // so the RentBookUseCase doesn't need to do this behaviour when creating a Loan as
        // the verification belongs to Catalog module.
        copy.markAsNotAvailable();
        copyRepo.save(copy);
    }

    @ApplicationModuleListener
    public void handle(LoanClosed event) {
        Copy copy = copyRepo.findById(new CopyId(event.copyId().id())).orElseThrow();
        // listen to LoanClosed event from ReturnBookUseCase and ensure to mark copy as "available"
        // so the RentBookUseCase doesn't need to do this behaviour when creating a Loan as
        // the verification belongs to Catalog module.
        copy.markAsAvailable();
        copyRepo.save(copy);
    }
}
