package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(Integer step,
                          TransactionType type,
                          BigDecimal amount,
                          TransactionCustomer origin,
                          TransactionCustomer recipient,
                          Boolean isFraud,
                          Boolean isFlaggedFraud) {

}
