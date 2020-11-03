package com.sww.springcloud.controller;

import com.sww.springcloud.entities.CommonResult;
import com.sww.springcloud.entities.Payment;
import com.sww.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Aeiherumuh
 * @date 2020/10/14
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    /**
     * 服务发现，获取服务信息
     */
    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 端口号
     * 查看负载均衡的效果
     */
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody(required = false) Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果: " + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort: " + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果: " + payment);
        if (payment != null) {
            return new CommonResult<>(200, "查询成功,serverPort: "+ serverPort, payment);
        }else {
            return new CommonResult<>(444, "没有相对应的记录,查询ID: "+id, null);
        }
    }

    /**
     * 服务发现
     * @return
     */
    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        //获取微服务
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*****service: " + service);
        }
        //获取一个微服务下的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t"+ instance.getInstanceId() + "\t" + instance.getHost() + "\t"
                    + instance.getPort()+ "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }
}