package com.rapid.swifta.ServicesImpl;

import com.rapid.swifta.DTOs.RequestBodies.RateRequestBody;
import com.rapid.swifta.DTOs.RequestBodies.ServicesRequestBody;
import com.rapid.swifta.DTOs.Responses.ServicesResponse;
import com.rapid.swifta.Entities.Services;
import com.rapid.swifta.Entities.User;
import com.rapid.swifta.Exceptions.ResourceNotFoundException;
import com.rapid.swifta.Repositories.ServiceRepository;
import com.rapid.swifta.Services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Services createService(ServicesRequestBody servicesRequestBody) {
        Services newService = new Services();
        return dataTransfer(servicesRequestBody, newService);
    }


    @Override
    public ServicesResponse getServiceById(Integer serviceId) {
        Services existingService = serviceRepository.findById(serviceId)
                .orElseThrow(()-> new ResourceNotFoundException("Service with ID " + serviceId + " could not be found"));
        return servicesResponse(existingService);
    }

    @Override
    public Page<ServicesResponse> getAllServicesByRandom(Pageable pageable) {
        Page<Services> servicesPage = serviceRepository.findAllByRandom(pageable);
        return servicesResponsePage(servicesPage);
    }

    @Override
    public String deleteServiceById(Integer serviceId) {
        serviceRepository.deleteById(serviceId);
        return "Successfully deleted";
    }

    @Override
    public Page<ServicesResponse> getByServiceType(Integer serviceTypeId, Pageable pageable) {
        Page<Services> servicesPage = serviceRepository.findAllByServiceTypeId(serviceTypeId, pageable);
        return servicesResponsePage(servicesPage);
    }

    @Override
    public Services updateService(Integer serviceId) {
        return null;
    }

    @Override
    public Services rateService(RateRequestBody rateRequestBody) {

        Services existingService = serviceRepository.findById(rateRequestBody.getId()).orElseThrow(()-> new ResourceNotFoundException("Service with ID " + rateRequestBody.getId() + " could not be found"));
        float massRating = existingService.getRateCount() * existingService.getServiceRating();
        int newRateCount = existingService.getRateCount() + 1;
        float newMassRating = rateRequestBody.getRating() + massRating;
        float newRating = newMassRating / newRateCount;
        existingService.setServiceRating(newRating);
        existingService.setRateCount(newRateCount);
        return serviceRepository.save(existingService);
    }

    @Override
    public String uploadAttachment() {
        return null;
    }

    private Services dataTransfer(ServicesRequestBody servicesRequestBody, Services newService) {

        newService.setUserId(servicesRequestBody.getUserId());
        newService.setServiceTitle(servicesRequestBody.getServiceTitle());
        newService.setServiceSummary(servicesRequestBody.getServiceSummary());
        newService.setServicesOffered(servicesRequestBody.getServiceOffered());
        newService.setServiceTypeId(servicesRequestBody.getServiceTypeId());
        newService.setServiceCharge(servicesRequestBody.getServiceCharge());

        return serviceRepository.save(newService);
    }


    public ServicesResponse servicesResponse(Services service){

        return ServicesResponse.builder()
                .servicesId(service.getServicesId())
                .userId(service.getUserId())
                .serviceTitle(service.getServiceTitle())
                .serviceSummary(service.getServiceSummary())
                .serviceOffered(service.getServicesOffered())
                .serviceTypeId(service.getServicesId())
                .rateCount(service.getRateCount())
                .serviceRating(service.getServiceRating())
                .jobsCompleted(service.getJobsCompleted())
                .serviceCharge(service.getServiceCharge())
                .serviceComments(service.getServiceComments())
                .build();
    }

    public Page<ServicesResponse> servicesResponsePage(Page<Services> services){

        return services.map(servicesResponse ->
                ServicesResponse.builder()
                .servicesId(servicesResponse.getServicesId())
                .userId(servicesResponse.getUserId())
                .serviceTitle(servicesResponse.getServiceTitle())
                .serviceSummary(servicesResponse.getServiceSummary())
                .serviceOffered(servicesResponse.getServicesOffered())
                .serviceTypeId(servicesResponse.getServiceTypeId())
                .rateCount(servicesResponse.getRateCount())
                .serviceRating(servicesResponse.getServiceRating())
                .jobsCompleted(servicesResponse.getJobsCompleted())
                .serviceCharge(servicesResponse.getServiceCharge())
                //TODO Add Attachments
                .serviceComments(servicesResponse.getServiceComments())
                .build());
    }
}
