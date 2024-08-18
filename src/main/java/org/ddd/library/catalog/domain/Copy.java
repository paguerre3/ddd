package org.ddd.library.catalog.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.Assert;

@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Copy {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private CopyId id;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "book_id"))
    private BookId bookId;
    @Embedded
    @AttributeOverride(name = "code", column = @Column(name = "bar_code"))
    private BarCode barCode;
    private boolean available;

    // only for hibernate and isn't public:
    Copy() {}

    public Copy(final BookId bookId, final BarCode barCode) {
        // DO validations:
        Assert.notNull(bookId, "Book ID must not be null");
        Assert.notNull(barCode, "Bar code must not be null");
        this.id = new CopyId();
        this.bookId = bookId;
        this.barCode = barCode;
        this.available = true;
    }

    public void markAsNotAvailable() {
        this.available = false;
    }

    public void markAsAvailable() {
        this.available = true;
    }
}
