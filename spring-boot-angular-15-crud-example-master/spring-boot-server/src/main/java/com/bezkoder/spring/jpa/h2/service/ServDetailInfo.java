package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsRequestDTO;
import com.bezkoder.spring.jpa.h2.dto.ServiceDetailsResponseDTO;
import com.bezkoder.spring.jpa.h2.entity.ServiceDetails;
import com.bezkoder.spring.jpa.h2.mapper.ServicesDetailsMapper;

public interface ServDetailInfo {

    ServiceDetailsRequestDTO getServiceDetailsById (Long id);
}
