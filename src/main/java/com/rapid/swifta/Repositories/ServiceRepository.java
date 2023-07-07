package com.rapid.swifta.Repositories;

import com.rapid.swifta.Entities.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM services s ORDER BY RAND()")
    Page<Services> findAllByRandom(Pageable pageable);


    @Query(nativeQuery = true, value = "SELECT * FROM services s where s.service_type_id = :serviceTypeId ORDER BY RAND()")
    Page<Services> findAllByServiceTypeId(Integer serviceTypeId, Pageable pageable);
}
