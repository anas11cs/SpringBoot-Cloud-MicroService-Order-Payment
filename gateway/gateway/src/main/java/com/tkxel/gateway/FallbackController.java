package com.tkxel.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @RequestMapping("/orderFallBack")
    public String orderServiceFallBack(){
        return"Order Service is taking too long to respond or is down.Please try again later";
    }
    @RequestMapping("/paymentFallBack")
    public String paymentServiceFallBack(){
        return "Payment Service is taking too long to respond or is down. Please try again later";
    }
}
