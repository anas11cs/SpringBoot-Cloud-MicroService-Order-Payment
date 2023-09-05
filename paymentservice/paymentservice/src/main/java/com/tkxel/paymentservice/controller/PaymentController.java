package com.tkxel.paymentservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tkxel.paymentservice.entity.Payment;
import com.tkxel.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/doPayment")
    public Payment doPayment (@RequestBody Payment payment) throws JsonProcessingException {
        try {
            log.info("Payment Request Received: ",new ObjectMapper().writeValueAsString(payment));
            return paymentService.doPayment(payment);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
