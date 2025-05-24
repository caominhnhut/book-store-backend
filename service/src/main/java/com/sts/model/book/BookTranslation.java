package com.sts.model.book;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookTranslation{

    private String locale;

    private String bookName;

    private String bookDescription;
}
