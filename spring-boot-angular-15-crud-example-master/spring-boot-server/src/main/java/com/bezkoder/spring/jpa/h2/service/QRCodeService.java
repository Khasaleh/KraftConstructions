package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.QRCode;
import com.bezkoder.spring.jpa.h2.dto.QRCodeDTO;
import com.google.zxing.WriterException;

import java.io.IOException;

public interface QRCodeService {
//    QRCodeDTO createQRCode(QRCodeDTO qrCodeDTO) throws Exception;

    public QRCode getQrCodeById(Long id);

    public QRCode saveQrCode(QRCodeDTO qrCodeDTO, String path) throws IOException, WriterException;
}