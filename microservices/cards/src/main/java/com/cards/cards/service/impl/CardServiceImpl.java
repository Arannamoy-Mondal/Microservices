package com.cards.cards.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cards.cards.constants.CardsConstants;
import com.cards.cards.dto.CardsDto;
import com.cards.cards.entity.Cards;
import com.cards.cards.mapper.CardsMapper;
import com.cards.cards.repo.CardRepo;
import com.cards.cards.service.ICardService;




@Service

public class CardServiceImpl implements ICardService{
    @Autowired
    private CardRepo cardRepo;

    @Override
    public void createCard(String mobileNumber) throws Exception {
       Optional<Cards> cards=cardRepo.findByMobileNumber(mobileNumber);
       if(cards==null){
        throw new Exception("Card already registered with given mobileNumber "+mobileNumber);
       }
       cardRepo.save(createNewCards(mobileNumber));
    }


    public Cards createNewCards(String mobileNumber){
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber)throws Exception {
        Cards cards=cardRepo.findByMobileNumber(mobileNumber).orElse(null);
        if(cards==null){
            throw new Exception("Card not found.");
        }
        return CardsMapper.cardsToCardsDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) throws Exception{
       Cards updateCards=CardsMapper.cardsDtoToCards(new Cards(), cardsDto);
       Cards cards=cardRepo.findByCardNumber(updateCards.getCardNumber()).orElse(null);
       if(cards==null){
        throw new Exception("No card found");
       }
       if(updateCards.getMobileNumber()!=null){
        cards.setMobileNumber(updateCards.getMobileNumber());
       }
       cardRepo.save(cards);
       return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber)throws Exception {
        Cards cards=cardRepo.findByMobileNumber(mobileNumber).orElse(null);
        if(cards==null){
            throw new Exception("No card found");
        }
        cardRepo.deleteById(cards.getCardId());
        return true;
    }

}
