package com.rapid.swifta.Repositories;


import com.rapid.swifta.Entities.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Orders, Integer> {

    Page<Orders> findAllByMerchantId(Pageable pageable, Integer merchantId);
    Page<Orders> findAllByClientId(Integer clientId, Pageable pageable);
    Page<Orders> findByClientIdEqualsAndClosedIsTrue(Integer clientId, Pageable pageable);
    Page<Orders> findByMerchantIdEqualsAndClosedIsTrue(Integer merchantId, Pageable pageable);
    Page<Orders> findByClientIdEqualsAndClosedIsFalse(Integer clientId, Pageable pageable);
    Page<Orders> findByMerchantIdEqualsAndClosedIsFalse(Integer merchantId, Pageable pageable);
    Page<Orders> findAllByClientIdContainingOrMerchantIdContainingAndClosedIsFalse(String clientId, String merchantId, Pageable pageable);

    boolean existsByOrderNumber(int uniqueNumber);
}
