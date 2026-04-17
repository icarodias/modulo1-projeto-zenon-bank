package br.com.zenon.fraud;

import java.util.List;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepository {

    private final List<Transaction> transactions;

    public TransactionListRepository(TransactionIngestor ingestor, String fileName, long totalLines) {
        this.transactions = ingestor.obtainTransactionsFromCSV(fileName, totalLines);
    }

    @Override
    public Optional<Transaction> findByTransactionCustomerOriginName(String name) {
        return transactions
                .stream()
                .filter(transaction -> transaction.origin().name().equals(name))
                .findFirst();
    }


}
