package com.bank.payment.cardservicesprovider.service;

import com.bank.payment.cardservicesprovider.dto.CardRequestDTO;
import com.bank.payment.cardservicesprovider.dto.CardResponseDTO;
import com.bank.payment.cardservicesprovider.entity.Card;
import com.bank.payment.cardservicesprovider.exception.ResourceNotFoundException;
import com.bank.payment.cardservicesprovider.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public CardResponseDTO createCard(CardRequestDTO requestDTO) {
        Card card = new Card();
        card.setCardHolderName(requestDTO.getCardHolderName());
        card.setCardNumber(requestDTO.getCardNumber());
        card.setCardType(requestDTO.getCardType());

        Card savedCard = cardRepository.save(card);
        return mapToResponseDTO(savedCard);
    }

    @Override
    public CardResponseDTO getCardById(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found with id: " + id));

        return mapToResponseDTO(card);
    }

    @Override
    public List<CardResponseDTO> getAllCards() {
        return cardRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    @Override
    public CardResponseDTO updateCard(Long id, CardRequestDTO requestDTO) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found with id: " + id));

        card.setCardHolderName(requestDTO.getCardHolderName());
        card.setCardNumber(requestDTO.getCardNumber());
        card.setCardType(requestDTO.getCardType());

        Card updatedCard = cardRepository.save(card);
        return mapToResponseDTO(updatedCard);
    }

    @Override
    public void deleteCard(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found with id: " + id));

        cardRepository.delete(card);
    }

    private CardResponseDTO mapToResponseDTO(Card card) {
        CardResponseDTO dto = new CardResponseDTO();

        dto.setId(card.getId());
        dto.setCardHolderName(card.getCardHolderName());
        dto.setCardNumber(card.getCardNumber());
        dto.setCardType(card.getCardType());
        dto.setStatus(card.getStatus());
        dto.setCreatedAt(card.getCreatedAt());
        dto.setUpdatedAt(card.getUpdatedAt());

        return dto;
    }
}

