package org.ddd.library.lending.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, LoanId> {

    @Query("select count(*) = 0 from Loan where copyId = :copyId and returnedAt is null")
    boolean isAvailable(CopyId copyId);

    default Loan findByIdOrThrow(LoanId loanId) {
        return findById(loanId).orElseThrow();
    }
}
