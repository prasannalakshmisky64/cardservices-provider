package com.bank.payment.cardservicesprovider.service;

import com.bank.payment.cardservicesprovider.dto.CardRequestDTO;
import com.bank.payment.cardservicesprovider.dto.CardResponseDTO;

import java.util.List;

public interface CardService {

    CardResponseDTO createCard(CardRequestDTO requestDTO);

    CardResponseDTO getCardById(Long id);

    List<CardResponseDTO> getAllCards();

    CardResponseDTO updateCard(Long id, CardRequestDTO requestDTO);

    void deleteCard(Long id);
}