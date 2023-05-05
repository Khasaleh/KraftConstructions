package com.bezkoder.spring.jpa.h2.controller;

import java.io.File;
import java.io.IOException;

import com.bezkoder.spring.jpa.h2.Entity.QRCode;
import com.bezkoder.spring.jpa.h2.dto.QRCodeDTO;
import com.bezkoder.spring.jpa.h2.mapper.QrCodeMapperImpl;
import com.bezkoder.spring.jpa.h2.service.QRCodeService;
import com.bezkoder.spring.jpa.h2.util.Roles;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.google.zxing.WriterException;

@RestController
@RequestMapping("/api/qr-code")
public class QRCodeController {
    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    QrCodeMapperImpl qrCodeMapper;

    @PostMapping
  @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<?> createQRCode(@RequestBody QRCodeDTO qrCodeDTO) {
        try {
            QRCodeDTO qrCode = qrCodeService.createQRCode(qrCodeDTO);
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