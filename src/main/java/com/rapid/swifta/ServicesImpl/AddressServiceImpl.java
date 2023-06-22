package com.rapid.swifta.ServicesImpl;


import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Exceptions.ResourceNotFoundException;
import com.rapid.swifta.Repositories.AddressRepository;
import com.rapid.swifta.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;


    @Override
    public Address getById(Integer addressId) {
        return addressRepository.findById(addressId).orElseThrow(()->
                new ResourceNotFoundException("Address with ID " + addressId + " was not found"));
    }
}
