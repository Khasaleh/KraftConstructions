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

import javax.validation.Valid;

@RestController
@RequestMapping("/api/qr-code")
public class QRCodeController {
    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    QrCodeMapperImpl qrCodeMapper;



    @PostMapping("/generate")
    @PreAuthorize("hasRole('" + Roles.ROLE_ADMIN + "')")
    public ResponseEntity<QRCodeDTO> generateQrCode(@Valid @RequestBody QRCodeDTO qrCodeDTO) {
        try {
            QRCode qrCode = qrCodeService.saveQrCode(qrCodeDTO, "uploads/Qrcode");
            QRCodeDTO savedQrCodeDTO = qrCodeMapper.toQrCodeDTO(qrCode);
            return ResponseEntity.ok(savedQrCodeDTO);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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