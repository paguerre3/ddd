package org.ddd.library.catalog;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.Assert;

@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private BookId id;
    private String title;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "isbn"))
    private Isbn isbn;

    public Book(String title, Isbn isbn) {
        Assert.notNull(title, "Title must not be null");
        Assert.notNull(isbn, "ISBN must not be null");
        // adding aggregations:
        this.id = new BookId();
        this.title = title;
        this.isbn = isbn;
    }
}
