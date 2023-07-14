package com.rapid.swifta.RestControllers;

import com.rapid.swifta.DTOs.RequestBodies.GenericRequestBody;
import com.rapid.swifta.DTOs.RequestBodies.RateRequestBody;
import com.rapid.swifta.DTOs.RequestBodies.ServicesRequestBody;
import com.rapid.swifta.DTOs.Responses.ServicesResponse;
import com.rapid.swifta.Entities.Services;
import com.rapid.swifta.Services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/swifta/services")
public class ServicesRestController {

    @Autowired
    private ServicesService servicesService;

    @PostMapping("create")
    public Services createService(@RequestBody ServicesRequestBody servicesRequestBody){
        return servicesService.createService(servicesRequestBody);
    }

    @GetMapping("getbyid")
    public ServicesResponse getServiceById(@RequestParam Integer serviceId){
        return servicesService.getServiceById(serviceId);
    }

    @GetMapping("getall")
    public Page<ServicesResponse> getAllServiceByRandom(Pageable pageable){
        return servicesService.getAllServicesByRandom(pageable);
    }

    @GetMapping("getbyservicetype")
    public Page<ServicesResponse> getAllServiceTypeByRandom(@RequestParam Integer serviceType, Pageable pageable){
        return servicesService.getByServiceType(serviceType, pageable);
    }

    @DeleteMapping("deleteservice")
    public String deleteService(@RequestParam Integer serviceId){
        return servicesService.deleteServiceById(serviceId);
    }

    @PutMapping("rateservice")
    public Services rateService(@RequestBody RateRequestBody requestBody){
        return servicesService.rateService(requestBody);
    }

    @PutMapping("update")
    public Services updateService(@RequestBody GenericRequestBody body){
        return servicesService.updateService(body.getId());
    }
}

