package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.Entity.QRCode;
import com.bezkoder.spring.jpa.h2.dto.QRCodeDTO;
import com.bezkoder.spring.jpa.h2.mapper.QrCodeMapperImpl;
import com.bezkoder.spring.jpa.h2.repository.QRCodeRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class QRCodeServiceImpl implements QRCodeService {
    @Autowired
    private QrCodeMapperImpl qrCodeMapper;

    @Autowired
    private QRCodeRepository qrCodeRepository;



    public QRCode saveQrCode(QRCodeDTO qrCodeDTO, String path) throws IOException, WriterException {
        QRCode qrCode = qrCodeMapper.toQrCode(qrCodeDTO);
        qrCodeRepository.save(qrCode);

        // Generate QR code image
        String qrCodeText = qrCodeDTO.getQrCodeText();
        int width = 300;
        int height = 300;
        BitMatrix bitMatrix = new MultiFormatWriter().encode(qrCodeText, BarcodeFormat.QR_CODE, width, height);
        BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        // Save QR code image to file
        File file = new File(path + "/" + qrCode.getId() + ".png");
        ImageIO.write(qrCodeImage, "png", file);

        qrCodeDTO.setFilePath(file.getPath());
        qrCode.setFilePath(qrCodeDTO.getFilePath());


        return qrCode;
    }


    public QRCode getQrCodeById(Long id) {
        Optional<QRCode> optionalQrCode = qrCodeRepository.findById(id);
        return optionalQrCode.orElse(null);
    }
}