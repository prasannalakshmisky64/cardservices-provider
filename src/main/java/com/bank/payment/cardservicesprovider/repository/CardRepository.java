package com.bank.payment.cardservicesprovider.repository;

import com.bank.payment.cardservicesprovider.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}