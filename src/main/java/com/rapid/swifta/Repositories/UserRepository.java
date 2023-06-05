package com.rapid.swifta.Repositories;

import com.rapid.swifta.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM user u  where u.address_user_id in (:digits) and u.role LIKE %:roleName% ORDER BY RAND()")
    Page<User> findAllLocationRandom(List<Integer> digits, String roleName, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM user u ORDER BY RAND()")
    Page<User> findAllByRandom(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT u.*, r.service_type_name FROM user u JOIN user_service_types ur ON u.user_id = ur.user_id JOIN service_type r ON ur.service_type_id = r.service_type_id WHERE r.service_type_id =:serviceType and u.address_user_id in (:addressIds)")
    Page<User> findByServiceTypeAndLocation(List<Integer> addressIds, int serviceType, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM user u WHERE u.role LIKE %:roleName% AND u.address_user_id in (:digits)")
    Page<User> findMerchantsByLocation(List<Integer> digits,String roleName, Pageable pageable);

}
