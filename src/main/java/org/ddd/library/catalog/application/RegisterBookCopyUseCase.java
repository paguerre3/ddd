package org.ddd.library.catalog.application;

import jakarta.validation.constraints.NotNull;
import org.ddd.library.UseCase;
import org.ddd.library.catalog.domain.BarCode;
import org.ddd.library.catalog.domain.BookId;
import org.ddd.library.catalog.domain.Copy;
import org.ddd.library.catalog.domain.CopyRepository;

@UseCase
public class RegisterBookCopyUseCase {
    private final CopyRepository copyRepo;

    public RegisterBookCopyUseCase(CopyRepository copyRepo) {
        this.copyRepo = copyRepo;
    }

    public void execute(@NotNull BookId bookId, @NotNull BarCode barCode) {
        copyRepo.save(new Copy(bookId, barCode));
    }
}
