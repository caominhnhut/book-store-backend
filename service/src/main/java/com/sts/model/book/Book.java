package com.sts.model.book;

import java.time.Instant;
import java.util.List;

import com.sts.util.enums.BookStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book{

    private Long id;

    private String bookCode;

    private String image;

    private BookStatus status;

    private Instant createdAt;

    private Instant updatedAt;

    private List<BookTranslation> translations;

    private List<Paragraph> paragraphs;
}
