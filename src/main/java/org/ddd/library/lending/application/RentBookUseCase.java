package org.ddd.library.lending.application;

import org.ddd.library.UseCase;
import org.ddd.library.lending.domain.CopyId;
import org.ddd.library.lending.domain.Loan;
import org.ddd.library.lending.domain.LoanRepository;
import org.ddd.library.lending.domain.UserId;

@UseCase
public class RentBookUseCase {
    private final LoanRepository loanRepo;

    public RentBookUseCase(LoanRepository loanRepo) {
        this.loanRepo = loanRepo;
    }

    public void execute(CopyId copyId, UserId userId) {
        // Catalog DomainEventListener ensures
        // that a "rented copy" is not rented again:
        loanRepo.save(new Loan(copyId, userId, loanRepo));
    }
}
