package com.sww.springcloud.controller;

import com.sww.springcloud.entities.CommonResult;
import com.sww.springcloud.entities.Payment;
import com.sww.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Aeiherumuh
 * @date 2020/10/14
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody(required = false) Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果: " + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功", result);
        } else {
            return new CommonResult(444, "插入数据失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果: " + payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功", payment);
        }else {
            return new CommonResult<>(444, "没有相对应的记录,查询ID: "+id, null);
        }
    }
}