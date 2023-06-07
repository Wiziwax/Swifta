package com.rapid.swifta.Services;

import com.rapid.swifta.Entities.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {

    Address getById(Integer addressId);

}
