package com.bezkoder.spring.jpa.h2.service;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import com.bezkoder.spring.jpa.h2.Entity.QRCode;
import com.bezkoder.spring.jpa.h2.repository.QRCodeRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRCodeService {

    private final QRCodeRepository qrCodeRepository;

    public QRCodeService(QRCodeRepository qrCodeRepository) {
        this.qrCodeRepository = qrCodeRepository;
    }

    @SneakyThrows
    public byte[] generateQRCode(String url) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 350, 350, 
                Map.of(EncodeHintType.MARGIN, 0));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        byte[] qrCodeImage = outputStream.toByteArray();
        
        // save the QR code to the database
        QRCode qrCode = new QRCode();
        qrCode.setUrl(url);
        qrCodeRepository.save(qrCode);
        
        return qrCodeImage;
    }
    
    public String getQRCodeUrlById(Long id) {
        QRCode qrCode = qrCodeRepository.findById(id).orElse(null);
        if (qrCode != null) {
            return qrCode.getUrl();
        } else {
            return null;
        }
    }
}
