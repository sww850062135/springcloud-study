package com.sww.springcloud.service;

import com.sww.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Aeiherumuh
 * @date 2020/10/14
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
