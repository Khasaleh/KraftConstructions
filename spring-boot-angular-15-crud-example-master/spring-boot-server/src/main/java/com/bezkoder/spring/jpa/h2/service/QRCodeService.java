package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.QRCode;
import com.bezkoder.spring.jpa.h2.dto.QRCodeDTO;

public interface QRCodeService {
    QRCodeDTO createQRCode(String qrCodeText) throws Exception;
//    QRCodeDTO getQRCodeById(Long id) throws Exception;

    public QRCode getQrCodeById(Long id);
}