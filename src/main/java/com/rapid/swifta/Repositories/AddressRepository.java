package com.rapid.swifta.Repositories;

import com.rapid.swifta.Entities.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByStreetNameContainingOrAreaContainingOrStateContainingOrCountryContaining(String street, String area, String State, String country);

    @Query(nativeQuery = true, value = "Select * from address where area like %?1% or country like %?1% or street_name like %?1% order by rand()")
    Page<Address> findAllByRandomAndLocation(String query, Pageable pageable);

    @Query(nativeQuery = true, value = "Select * from address where area like %?1% or country like %?1% or street_name like %?1%")
    List<Address> findAllByQuery(String query);

    @Query(nativeQuery = true, value = "Select * from address where country like %?1%  or  (country like %?1% and state like %?2%) or (country like %?1% and state like %?2% and area like %?3%) or  (country like %?1% and state like %?2% and area like %?3% and street_name like %?4%)")
    List<Address> findAllByQueryingColumns(String country, String state, String area, String street);

    List<Address> findAllByCountryContaining(String country);
    List<Address> findAllByCountryContainingAndStateContaining(String country, String state);
    List<Address> findAllByCountryContainingAndStateContainingAndAreaContaining(String country, String state, String area);
    List<Address> findAllByCountryContainingAndStateContainingAndAreaContainingAndStreetNameContaining(String country, String state, String area, String streetName);

}
