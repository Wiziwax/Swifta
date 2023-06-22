package com.rapid.swifta.RestControllers;

import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/address/")
public class AddressRestController {

    @Autowired
    private AddressService addressService;

    @GetMapping("")
    private Address getAddressById(@RequestParam Integer addressId){
        return addressService.getById(addressId);
    }


}
