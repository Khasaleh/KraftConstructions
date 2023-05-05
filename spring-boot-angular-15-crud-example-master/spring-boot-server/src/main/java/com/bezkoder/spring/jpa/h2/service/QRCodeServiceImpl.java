package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.QRCode;
import com.bezkoder.spring.jpa.h2.dto.QRCodeDTO;
import com.bezkoder.spring.jpa.h2.mapper.QrCodeMapperImpl;
import com.bezkoder.spring.jpa.h2.repository.QRCodeRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

@Service
public class QRCodeServiceImpl implements QRCodeService {
    @Autowired
    private QrCodeMapperImpl qrCodeMapper;

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Override
    public QRCodeDTO createQRCode(QRCodeDTO qrCodeDTO) throws Exception {
        // Create QR code and save it to the file system
        String folderPath = "path/to/folder/";

        String filename = UUID.randomUUID().toString() + ".png";
        String filePath = folderPath + File.separator + filename;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeDTO.getQrCodeText(), BarcodeFormat.QR_CODE, 350, 350);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        QRCode qrCode = new QRCode();
        qrCode.setQrCodeText(qrCodeDTO.getQrCodeText());
        qrCodeDTO.setFilePath(filePath);
        qrCode.setFilePath(qrCodeDTO.getFilePath());

        QRCode savedQRCode = qrCodeRepository.save(qrCode);

        return qrCodeMapper.toQrCodeDTO(savedQRCode);
    }


    public QRCode getQrCodeById(Long id) {
        Optional<QRCode> optionalQrCode = qrCodeRepository.findById(id);
        return optionalQrCode.orElse(null);
    }
}