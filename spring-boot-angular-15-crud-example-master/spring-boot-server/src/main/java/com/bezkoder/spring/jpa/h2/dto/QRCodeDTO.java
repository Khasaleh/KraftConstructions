package com.bezkoder.spring.jpa.h2.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QRCodeDTO {
    private String qrCodeText;
    private String filePath;

    // Getters and setters
}