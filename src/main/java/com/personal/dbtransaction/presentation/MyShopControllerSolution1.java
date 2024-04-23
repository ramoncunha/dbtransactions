package com.personal.dbtransaction.presentation;

import com.personal.dbtransaction.application.BuyProductService;
import com.personal.dbtransaction.domain.BuyProductRequest;
import com.personal.dbtransaction.domain.model.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/myshop")
@RequiredArgsConstructor
public class MyShopControllerSolution1 {
    private static final Random RANDOM = new Random();

    private final BuyProductService buyProductService;

    @PostMapping("/products/buy")
    public ResponseEntity<OrderEntity> buyProduct(@RequestBody BuyProductRequest request) {
        OrderEntity order = buyProductService.buy(request, getRandomNumber());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    private int getRandomNumber() {
        return RANDOM.nextInt(10) + 1;
    }
}