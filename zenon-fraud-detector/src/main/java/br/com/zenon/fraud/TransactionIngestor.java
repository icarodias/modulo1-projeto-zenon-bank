package br.com.zenon.fraud;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class TransactionIngestor {

    public TransactionIngestor(){
    }

    public List<Transaction> obtainTransactionsFromCSV(String fileName, long amountLines) {

        Path path = Path.of(fileName);
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .skip(1)
                    .limit(amountLines)
                    .map(this::parseToTransaction)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while read file");
        }
    }

    private Optional<Transaction> parseToTransaction(String transactionRaw) {
        final String delimiterCSV = ",";
        int fieldsInTransactionRaw = 11;
        final String[] fields = transactionRaw.split(delimiterCSV);

        try {
            if (fields.length != fieldsInTransactionRaw) {
                throw new IllegalArgumentException("transaction raw not has correct number of fields: " + fields.length);
            }

            if (Arrays.stream(fields).anyMatch(field -> field.trim().isEmpty())) {
                throw new IllegalArgumentException("transaction raw cannot have any empty fields");
            }

            TransactionCustomer transactionCustomerOrigin = new TransactionCustomer(
                    fields[3], new BigDecimal(fields[4]), new BigDecimal(fields[5]));
            TransactionCustomer transactionCustomerRecipient = new TransactionCustomer(
                    fields[6], new BigDecimal(fields[7]), new BigDecimal(fields[8]));

            return Optional.of(new Transaction(
                    Integer.parseInt(fields[0]),
                    TransactionType.valueOf(fields[1]),
                    new BigDecimal(fields[2]),
                    transactionCustomerOrigin,
                    transactionCustomerRecipient,
                    fields[9].equals("1"),
                    fields[10].equals("1")
            ));
        } catch (Exception ex) {
            System.err.println("Error: " + transactionRaw + " | " + ex.getMessage());
            return Optional.empty();
        }
    }
}
