package com.atguigu.springcloud.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}") //获取8004
    private String serverPort;

    @RequestMapping("/payment/zk")
    public String paymentzk(){

        return "springcloud with zookeeper:"+serverPort+"\t"+ UUID.randomUUID().toString();
    }


}
