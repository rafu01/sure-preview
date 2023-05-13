package com.sure.surepreview.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreviewDto {

    private String title;

    private String image;

    private String description;
}
