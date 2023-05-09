package com.bezkoder.spring.jpa.h2.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QRCodeDTO {

    @NotNull
    private String qrCodeText;


    @NotNull
    private String filePath;

    // Getters and setters
}