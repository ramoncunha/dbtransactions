package com.personal.dbtransaction.presentation;

import com.personal.dbtransaction.application.BuyProductServiceSolution2;
import com.personal.dbtransaction.domain.BuyProductRequest;
import com.personal.dbtransaction.domain.model.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController
//@RequestMapping("/api/myshop")
@RequiredArgsConstructor
public class MyShopControllerSolution2 {

    private final BuyProductServiceSolution2 buyProductServiceSolution2;

    @PostMapping("/products/buy")
    public ResponseEntity<OrderEntity> buyProduct(@RequestBody BuyProductRequest request) {
        OrderEntity order = buyProductServiceSolution2.buy(request);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}