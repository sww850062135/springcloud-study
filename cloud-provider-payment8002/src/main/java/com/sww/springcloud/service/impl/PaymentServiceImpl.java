package com.sww.springcloud.service.impl;

import com.sww.springcloud.dao.PaymentDao;
import com.sww.springcloud.entities.Payment;
import com.sww.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Aeiherumuh
 * @date 2020/10/14
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
