package com.personal.dbtransaction.presentation;

import com.personal.dbtransaction.application.BuyProductServiceSolution1;
import com.personal.dbtransaction.domain.BuyProductRequest;
import com.personal.dbtransaction.domain.model.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/api/myshop")
@RequiredArgsConstructor
public class MyShopControllerSolution1 {

    private final BuyProductServiceSolution1 buyProductServiceSolution1;

    @PostMapping("/products/buy")
    public ResponseEntity<OrderEntity> buyProduct(@RequestBody BuyProductRequest request) {
        OrderEntity order = buyProductServiceSolution1.buy(request);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}