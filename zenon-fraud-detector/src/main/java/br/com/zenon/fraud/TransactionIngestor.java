package br.com.zenon.fraud;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class TransactionIngestor {

    public TransactionIngestor(){}

    public List<Transaction> obtainTransactionsFromCSV(String fileName, long amountLines) {
        Path path = Path.of(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .skip(1)
                    .limit(amountLines)
                    .map(this::parseToTransaction)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while read file");
        }
    }

    private Transaction parseToTransaction(String transactionRaw) {
        final String[] fields = transactionRaw.split(",");

        if (fields.length != 11) {
            throw new RuntimeException("Error while parse raw to transaction");
        }

        TransactionCustomer transactionCustomerOrigin = new TransactionCustomer(
                fields[3], new BigDecimal(fields[4]),new BigDecimal(fields[5]));
        TransactionCustomer transactionCustomerRecipient =  new TransactionCustomer(
                fields[6], new BigDecimal(fields[7]),new BigDecimal(fields[8]));

        return new Transaction(
                Integer.valueOf(fields[0]),
                TransactionType.valueOf(fields[1]),
                new BigDecimal(fields[2]),
                transactionCustomerOrigin,
                transactionCustomerRecipient,
                fields[9].equals("1"),
                fields[10].equals("1")
        );
    }
}
