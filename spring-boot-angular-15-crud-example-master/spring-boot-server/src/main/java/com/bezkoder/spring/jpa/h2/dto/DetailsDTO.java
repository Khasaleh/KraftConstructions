package com.bezkoder.spring.jpa.h2.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetailsDTO {

    private String serviceName;
    private String description;
    private String author;
    private LocalDateTime updateDate;

    public DetailsDTO(Long id, String serviceName,String author, String description,LocalDateTime updateDate) {
        this.serviceName=serviceName;
        this.author=author;
        this.description=description;
        this.updateDate=updateDate;
    }

}
