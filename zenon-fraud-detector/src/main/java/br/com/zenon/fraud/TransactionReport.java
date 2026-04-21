package br.com.zenon.fraud;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class TransactionReport {


    private final TransactionIngestor ingestor;

    public TransactionReport(TransactionIngestor ingestor) {
        this.ingestor = ingestor;
    }

    public long totalLines(String fileName) {
        try (Stream<Transaction> transactionStream = ingestor.obtainTransactionsFromCSV(fileName)) {
                return transactionStream.count();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while read file");
        }
    }

    public long totalFrauds(String fileName) {
        try (Stream<Transaction> transactionStream = ingestor.obtainTransactionsFromCSV(fileName)) {
            return transactionStream
                    .filter(Transaction::isFraud)
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while read file");
        }
    }

    public BigDecimal totalAmount(String fileName) {
        try (Stream<Transaction> transactionStream = ingestor.obtainTransactionsFromCSV(fileName)) {
            return transactionStream
                    .map(Transaction::amount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while read file");
        }
    }
}
