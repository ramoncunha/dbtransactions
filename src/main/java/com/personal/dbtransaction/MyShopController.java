package com.personal.dbtransaction;

import com.personal.dbtransaction.model.CustomerEntity;
import com.personal.dbtransaction.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myshop")
@RequiredArgsConstructor
public class MyShopController {

    private final CustomerRepository customerRepository;

    @PostMapping("/customers")
    public ResponseEntity<CustomerEntity> createUser() {
        var customer = CustomerEntity.builder()
                .firstName("Ramon")
                .lastName("Cunha")
                .email("my@email.com")
                .build();
        CustomerEntity savedCustomer = customerRepository.save(customer);

        return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
    }
}
