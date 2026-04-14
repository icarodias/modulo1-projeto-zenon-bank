package br.com.zenon.fraud;

import java.math.BigDecimal;

import static br.com.zenon.fraud.TransactionType.CASH_OUT;
import static br.com.zenon.fraud.TransactionType.PAYMENT;

public class Main {
    public static void main(String[] args) {
        TransactionCustomer transactionCustomerOrigin1 = new TransactionCustomer(
                "C1231006815", BigDecimal.valueOf(170136.0), BigDecimal.valueOf(160296.36));
        TransactionCustomer transactionCustomerRecipient1 =  new TransactionCustomer(
                "M1979787155", BigDecimal.ZERO, BigDecimal.ZERO);
        Transaction transaction1 = new Transaction(
                1, PAYMENT, BigDecimal.valueOf(9839.64), transactionCustomerOrigin1, transactionCustomerRecipient1, false, false);

        TransactionCustomer transactionCustomerOrigin2 =
                new TransactionCustomer("C1280323807", BigDecimal.valueOf(850002.52), BigDecimal.ZERO);
        TransactionCustomer transactionCustomerRecipient2 =
                new TransactionCustomer("C873221189", BigDecimal.valueOf(6510099.11), BigDecimal.valueOf(7360101.63));
        Transaction transaction2 = new Transaction(
                743, CASH_OUT, BigDecimal.valueOf(850002.52), transactionCustomerOrigin2, transactionCustomerRecipient2, true, false);

        Transaction[] transactions = new Transaction[]{transaction1, transaction2};

        for (int i = 0; i < transactions.length; i++) {
            System.out.println("Transaction " + (i + 1) + ":\n"+transactions[i]);
        }

    }
}