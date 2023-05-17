package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.Entity.Services;
import com.bezkoder.spring.jpa.h2.dto.ServiceHomePageRequestDto;
import com.bezkoder.spring.jpa.h2.dto.ServiceHomePageResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ServiceHomePageMapper {

    private ServiceHomePageResponseDto entityToDto(Services services){
       return ServiceHomePageResponseDto.builder().serviceName(services.getServiceName()).afterImageUrl(services.getServiceDetails().getAfterImageUrl()).description(services.getServiceDetails().getDescription()).build();
    }
}
