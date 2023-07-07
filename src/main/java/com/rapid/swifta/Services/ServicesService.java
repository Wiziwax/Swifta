package com.rapid.swifta.Services;

import com.rapid.swifta.DTOs.RequestBodies.RateRequestBody;
import com.rapid.swifta.DTOs.RequestBodies.ServicesRequestBody;
import com.rapid.swifta.DTOs.Responses.ServicesResponse;
import com.rapid.swifta.Entities.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServicesService {

    Services createService(ServicesRequestBody servicesRequestBody);
    ServicesResponse getServiceById(Integer serviceId);
    Page<ServicesResponse> getAllServicesByRandom(Pageable pageable);
    String deleteServiceById(Integer serviceId);
    Page<ServicesResponse> getByServiceType(Integer serviceTypeId, Pageable pageable);
    Services updateService(Integer serviceId);
    Services rateService(RateRequestBody rateRequestBody);
    String uploadAttachment();

}
