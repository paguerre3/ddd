package org.ddd.library.lending.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Loan extends AbstractAggregateRoot<Loan> {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private LoanId id;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "copy_id"))
    private CopyId copyId;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    private UserId userId;
    private LocalDateTime createdAt;
    private LocalDate expectedReturnDate;
    private LocalDateTime returnedAt;
    @Version
    private Long version;
    private BigDecimal fee;

    Loan(){}

    public Loan(CopyId copyId, UserId userId, LoanRepository loanRepository) {
        Assert.notNull(copyId, "Copy ID cannot be null");
        Assert.notNull(userId, "User ID cannot be null");
        // LoanRepository is Injected here in order to avoid
        // making the call "isAvailable" inside the Application layer using the Repository
        // and therefore the verification call is performed inside the
        // Loan "Root Aggregate" inside the Domain Layer as recommended by DDD,
        // i.e. not exposing the business validation logic into the Application Layer:
        Assert.isTrue(loanRepository.isAvailable(copyId), "Copy with ID %s is not available".formatted(copyId.id()));
        this.id = new LoanId();
        this.copyId = copyId;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
        this.expectedReturnDate = LocalDate.now().plusDays(30);
        this.fee = BigDecimal.ZERO;
        // register business domain event for listener in order to simplify
        // and avoid code dependencies when it's required:
        this.registerEvent(new LoanCreated(this.copyId));
    }

    public void returnAkaClose(){
        Assert.isTrue(this.returnedAt == null, "Loan has already been returned");
        this.returnedAt = LocalDateTime.now();
        if(this.returnedAt.toLocalDate().isAfter(this.expectedReturnDate)) {
            // Calculate the fee:
            final BigDecimal DailyFee = BigDecimal.valueOf(1.5);
            long overdueDays = ChronoUnit.DAYS.between(expectedReturnDate, returnedAt.toLocalDate());
            this.fee = BigDecimal.valueOf(overdueDays).multiply(DailyFee);
        }
        // register business domain event for listener in order to simplify
        // and avoid code dependencies when it's required:
        this.registerEvent(new LoanClosed(this.copyId));
    }
}
