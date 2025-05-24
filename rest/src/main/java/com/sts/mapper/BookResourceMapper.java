package com.sts.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sts.dto.book.request.BookRequest;
import com.sts.dto.book.request.ParagraphRequest;
import com.sts.dto.book.response.BookResponse;
import com.sts.model.book.Book;
import com.sts.model.book.Paragraph;

@Mapper(componentModel = "spring")
public interface BookResourceMapper{

    Book toBook(BookRequest bookRequest);

    @Mapping(target = "createdAt", source = "book.createdAt")
    @Mapping(target = "updatedAt", source = "book.updatedAt")
    BookResponse toBookResponse(Book book);

    Paragraph toParagraph(ParagraphRequest paragraphRequest);

}
