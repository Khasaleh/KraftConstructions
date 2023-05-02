package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.QRCode;
import com.bezkoder.spring.jpa.h2.dto.QRCodeDTO;

public interface QRCodeMapper {
    
    QRCodeDTO toQrCodeDTO(QRCode qrCode);
    
    QRCode toQrCode(QRCodeDTO qrCodeDTO);
}