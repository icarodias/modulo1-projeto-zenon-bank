package br.com.zenon.fraud;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        final TransactionIngestor ingestor = new TransactionIngestor();

        /* file in ../data dir and fileName PS_20174392719_1491204439457_log.csv */
        final String relativeFilePath = "../data/PS_20174392719_1491204439457_log.csv";
        final int amountLines = 1000;

        final List<Transaction> transactions =  ingestor.obtainTransactionsFromCSV(relativeFilePath, amountLines);
        transactions.stream().limit(10).forEach(System.out::println);
    }
}