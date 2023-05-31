package com.moroTechLibrary.library.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FormatDTO {
    private Long id;
    private String mimeType;
    private String url;
}