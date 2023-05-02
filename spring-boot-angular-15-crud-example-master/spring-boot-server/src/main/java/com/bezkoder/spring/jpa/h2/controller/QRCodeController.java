package com.bezkoder.spring.jpa.h2.controller;

import java.io.File;
import java.io.IOException;

import com.bezkoder.spring.jpa.h2.Entity.QRCode;
import com.bezkoder.spring.jpa.h2.dto.QRCodeDTO;
import com.bezkoder.spring.jpa.h2.mapper.QRCodeMapper;
import com.bezkoder.spring.jpa.h2.service.QRCodeService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.google.zxing.WriterException;

@RestController
@RequestMapping("/api/qr-code")
public class QRCodeController {
    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    QRCodeMapper qrCodeMapper;

    @PostMapping
    public ResponseEntity<?> createQRCode(@RequestBody String qrCodeText) {
        try {
            QRCodeDTO qrCodeDTO = qrCodeService.createQRCode(qrCodeText);
            return new ResponseEntity<>(qrCodeDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create QR code", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<QRCodeDTO> getQrCodeById(@PathVariable Long id) {
        QRCode qrCode = qrCodeService.getQrCodeById(id);
        if (qrCode == null) {
            return ResponseEntity.notFound().build();
        }
        QRCodeDTO qrCodeDTO = qrCodeMapper.toQrCodeDTO(qrCode);
        return ResponseEntity.ok(qrCodeDTO);
    }
}