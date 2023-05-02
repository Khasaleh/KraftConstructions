package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.QRCode;
import com.bezkoder.spring.jpa.h2.dto.QRCodeDTO;
import com.bezkoder.spring.jpa.h2.mapper.QRCodeMapper;
import com.bezkoder.spring.jpa.h2.repository.QRCodeRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class QRCodeServiceImpl implements QRCodeService {
    @Autowired
    private QRCodeMapper qrCodeMapper;

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Override
    public QRCodeDTO createQRCode(String qrCodeText) throws Exception {
        // Create QR code and save it to the file system
        String filePath = "path/to/folder/qr_code.png"; // Change this path to your desired path
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, 350, 350);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        QRCode qrCode = new QRCode();
        qrCode.setQrCodeText(qrCodeText);
        qrCode.setFilePath(filePath);
        QRCode savedQRCode = qrCodeRepository.save(qrCode);

        return qrCodeMapper.toQrCodeDTO(savedQRCode);
    }

//    @Override
//    public QRCodeDTO getQRCodeById(Long id) throws Exception {
//        Optional<QRCode> qrCodeOptional = qrCodeRepository.findById(id);
//        if (!qrCodeOptional.isPresent()) {
//            throw new Exception("QR code not found");
//        }
//        return qrCodeMapper.toQrCodeDTO(qrCodeOptional.get());
//    }

    public QRCode getQrCodeById(Long id) {
        Optional<QRCode> optionalQrCode = qrCodeRepository.findById(id);
        return optionalQrCode.orElse(null);
    }
}