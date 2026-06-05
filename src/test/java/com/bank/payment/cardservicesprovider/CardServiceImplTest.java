package com.bank.payment.cardservicesprovider;

import com.bank.payment.cardservicesprovider.dto.CardRequestDTO;
import com.bank.payment.cardservicesprovider.dto.CardResponseDTO;
import com.bank.payment.cardservicesprovider.entity.Card;
import com.bank.payment.cardservicesprovider.exception.ResourceNotFoundException;
import com.bank.payment.cardservicesprovider.repository.CardRepository;
import com.bank.payment.cardservicesprovider.service.CardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardServiceImpl cardServiceImpl;

    @Test
    void getCardByIdTest() {
        Card card = new Card();
        card.setId(1L);
        card.setCardHolderName("Prasanna");
        card.setCardNumber("1234567890");
        card.setCardType("VISA");

        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));

        CardResponseDTO result = cardServiceImpl.getCardById(1L);

        assertNotNull(result);
        assertEquals("Prasanna", result.getCardHolderName());
        assertEquals("1234567890", result.getCardNumber());
        assertEquals("VISA", result.getCardType());

        verify(cardRepository, times(1)).findById(1L);
    }

    @Test
    void getCardByIdNotFoundTest() {
        when(cardRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            cardServiceImpl.getCardById(1L);
        });

        verify(cardRepository, times(1)).findById(1L);
    }

    @Test
    void createCardTest() {
        CardRequestDTO requestDTO = new CardRequestDTO();
        requestDTO.setCardHolderName("Prasanna");
        requestDTO.setCardNumber("1234567890");
        requestDTO.setCardType("VISA");

        Card savedCard = new Card();
        savedCard.setId(1L);
        savedCard.setCardHolderName("Prasanna");
        savedCard.setCardNumber("1234567890");
        savedCard.setCardType("VISA");

        when(cardRepository.save(any(Card.class))).thenReturn(savedCard);

        CardResponseDTO result = cardServiceImpl.createCard(requestDTO);

            assertNotNull(result);
        assertEquals("Prasanna", result.getCardHolderName());
        assertEquals("1234567890", result.getCardNumber());
        assertEquals("VISA", result.getCardType());

        verify(cardRepository, times(1)).save(any(Card.class));
    }

    @Test
    void getAllCardsTest() {
        Card card = new Card();
        card.setId(1L);
        card.setCardHolderName("Prasanna");
        card.setCardNumber("1234567890");
        card.setCardType("VISA");

        when(cardRepository.findAll()).thenReturn(List.of(card));

        List<CardResponseDTO> result = cardServiceImpl.getAllCards();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Prasanna", result.get(0).getCardHolderName());

        verify(cardRepository, times(1)).findAll();
    }

    @Test
    void updateCardTest() {
        Card existingCard = new Card();
        existingCard.setId(1L);
        existingCard.setCardHolderName("Old Name");
        existingCard.setCardNumber("1111");
        existingCard.setCardType("MASTER");

        CardRequestDTO requestDTO = new CardRequestDTO();
        requestDTO.setCardHolderName("Prasanna");
        requestDTO.setCardNumber("1234567890");
        requestDTO.setCardType("VISA");

        Card updatedCard = new Card();
        updatedCard.setId(1L);
        updatedCard.setCardHolderName("Prasanna");
        updatedCard.setCardNumber("1234567890");
        updatedCard.setCardType("VISA");

        when(cardRepository.findById(1L)).thenReturn(Optional.of(existingCard));
        when(cardRepository.save(existingCard)).thenReturn(updatedCard);

        CardResponseDTO result = cardServiceImpl.updateCard(1L, requestDTO);

        assertNotNull(result);
        assertEquals("Prasanna", result.getCardHolderName());
        assertEquals("1234567890", result.getCardNumber());
        assertEquals("VISA", result.getCardType());

        verify(cardRepository, times(1)).findById(1L);
        verify(cardRepository, times(1)).save(existingCard);
    }

    @Test
    void deleteCardTest() {
        Card card = new Card();
        card.setId(1L);
        card.setCardHolderName("Prasanna");
        card.setCardNumber("1234567890");
        card.setCardType("VISA");

        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));

        cardServiceImpl.deleteCard(1L);

        verify(cardRepository, times(1)).findById(1L);
        verify(cardRepository, times(1)).delete(card);
    }
}