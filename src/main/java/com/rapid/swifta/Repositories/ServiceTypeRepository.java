package com.rapid.swifta.Repositories;


import com.rapid.swifta.Entities.ServiceType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer> {
}
