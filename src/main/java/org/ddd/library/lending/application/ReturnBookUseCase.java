package org.ddd.library.lending.application;

import org.ddd.library.UseCase;
import org.ddd.library.lending.domain.LoanId;
import org.ddd.library.lending.domain.LoanRepository;

@UseCase
public class ReturnBookUseCase {
    private final LoanRepository loanRepo;

    public ReturnBookUseCase(LoanRepository loanRepo) {
        this.loanRepo = loanRepo;
    }

    public void execute(LoanId loanId) {
       var loan = loanRepo.findByIdOrThrow(loanId);
       loan.returnAkaClose();
    }
}
