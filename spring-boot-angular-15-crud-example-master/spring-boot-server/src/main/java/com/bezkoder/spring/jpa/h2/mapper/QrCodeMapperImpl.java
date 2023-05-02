package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.QRCode;
import com.bezkoder.spring.jpa.h2.dto.QRCodeDTO;
import org.springframework.stereotype.Component;

import javax.persistence.Column;


@Component
public class QrCodeMapperImpl implements QRCodeMapper {

    @Override
    public QRCodeDTO toQrCodeDTO(QRCode qrCode) {
        QRCodeDTO qrCodeDTO = new QRCodeDTO();
        qrCodeDTO.setQrCodeText(qrCode.getQrCodeText());
        qrCodeDTO.setFilePath(qrCode.getFilePath());
        return qrCodeDTO;
    }

    @Override
    public QRCode toQrCode(QRCodeDTO qrCodeDTO) {
        QRCode qrCode = new QRCode();
        qrCode.setQrCodeText(qrCodeDTO.getQrCodeText());
        return qrCode;
    }
}