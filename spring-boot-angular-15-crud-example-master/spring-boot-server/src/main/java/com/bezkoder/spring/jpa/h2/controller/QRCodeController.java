package com.bezkoder.spring.jpa.h2.controller;

import java.io.IOException;

import com.bezkoder.spring.jpa.h2.service.QRCodeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.zxing.WriterException;

@RestController
@RequestMapping("api/qrcode")
public class QRCodeController {
    
    private final QRCodeService qrCodeService;
    
    public QRCodeController(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getQRCodeImage(@PathVariable Long id) {
        String url = qrCodeService.getQRCodeUrlById(id);
        if (url != null) {
            try {
                byte[] qrCodeImage = qrCodeService.generateQRCode(url);
                return ResponseEntity.ok().body(qrCodeImage);
            } catch (WriterException e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
