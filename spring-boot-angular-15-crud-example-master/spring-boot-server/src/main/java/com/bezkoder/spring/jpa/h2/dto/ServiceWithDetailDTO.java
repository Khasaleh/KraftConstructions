package com.bezkoder.spring.jpa.h2.dto;


import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ServiceWithDetailDTO {

    private String serviceName;
    private String description;
    private String author;
    private LocalDateTime updateDate;


}
