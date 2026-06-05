package com.bank.payment.cardservicesprovider.dto;

import lombok.Data;

@Data
public class CardRequestDTO {
    private String cardHolderName;
    private String cardNumber;
    private String cardType;
}