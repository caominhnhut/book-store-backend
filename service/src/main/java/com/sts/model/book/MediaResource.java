package com.sts.model.book;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MediaResource{

    private Long id;

    private String linkToResource;

    private String resourceType;

    private String description;
}