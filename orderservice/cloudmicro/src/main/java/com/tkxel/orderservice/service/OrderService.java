package com.tkxel.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tkxel.orderservice.dto.Payment;
import com.tkxel.orderservice.dto.TransactionRequest;
import com.tkxel.orderservice.dto.TransactionResponse;
import com.tkxel.orderservice.entity.Order;
import com.tkxel.orderservice.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Log4j2
@Service
@RefreshScope
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    @Lazy
    RestTemplate restTemplate;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;
    public TransactionResponse saveOrder(TransactionRequest request) {
        try {
            String response = "";
            Order order = request.getOrder();
            Payment payment = request.getPayment();
            payment.setOrderId(order.getId());
            payment.setAmount(order.getPrice());
            log.info("Payment Object Received is: {}",new ObjectMapper().writeValueAsString(request));
            Payment paymentResponse = restTemplate.postForObject(ENDPOINT_URL , payment , Payment.class);
            response = paymentResponse.getPaymentStatus().equals("success") ? "Payment processing successful and order placed" : "There is failure in payment api, order added to cart";
            log.info("Payment Response Received from Order Service Call is: {}", new ObjectMapper().writeValueAsString(request));
            orderRepository.save(order);
            return new TransactionResponse(order , payment.getAmount() , paymentResponse.getTransactionId() , response);
        }
        catch(Exception e){
            log.error(e);
        }
        return null;
    }

    public Order findOrder(Integer orderId){
        Optional<Order> searchedOrder = orderRepository.findById(orderId);
        if(searchedOrder==null){
            return new Order();
        }
        return searchedOrder.get();
    }

}
