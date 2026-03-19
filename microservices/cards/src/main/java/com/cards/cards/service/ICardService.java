package com.cards.cards.service;

import org.springframework.stereotype.Service;

import com.cards.cards.dto.CardsDto;

@Service
public interface ICardService {
    void createCard (String mobileNumber) throws Exception;
    CardsDto fetchCard(String mobileNumber) throws Exception;
    boolean updateCard(CardsDto cardsDto)throws Exception;
    boolean deleteCard(String mobileNumber)throws Exception;
}
