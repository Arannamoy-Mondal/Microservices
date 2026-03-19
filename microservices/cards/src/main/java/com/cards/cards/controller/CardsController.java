package com.cards.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cards.cards.constants.CardsConstants;
import com.cards.cards.dto.CardsDto;
import com.cards.cards.service.ICardService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/api")
public class CardsController {
    @Autowired
    private ICardService iCardService;

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
        try {
            iCardService.createCard(mobileNumber);
            return ResponseEntity.status(HttpStatus.CREATED).body(CardsConstants.MESSAGE_201);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetch(
            @Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iCardService.fetchCard(mobileNumber));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(
            @RequestBody CardsDto cardsDto) {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(iCardService.updateCard(cardsDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String mobileNumber) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(iCardService.deleteCard(mobileNumber));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
