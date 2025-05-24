package com.sts.model.book;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParagraphTranslation{

    private String locale;

    private String paragraphContent;
}
