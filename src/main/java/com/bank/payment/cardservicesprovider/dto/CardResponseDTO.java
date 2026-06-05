package com.bank.payment.cardservicesprovider.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CardResponseDTO {
    private Long id;
    private String cardHolderName;
    private String cardNumber;
    private String cardType;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}