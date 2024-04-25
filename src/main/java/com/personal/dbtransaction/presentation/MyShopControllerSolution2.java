package com.personal.dbtransaction.presentation;

import com.personal.dbtransaction.application.BuyProductServiceSolution3;
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

    private BuyProductServiceSolution3 buyProductServiceSolution3;

    @PostMapping("/products/buy")
    public ResponseEntity<OrderEntity> buyProduct(@RequestBody BuyProductRequest request) {
        return new ResponseEntity<>(buyProductServiceSolution3.buy(request), HttpStatus.OK);
    }
}