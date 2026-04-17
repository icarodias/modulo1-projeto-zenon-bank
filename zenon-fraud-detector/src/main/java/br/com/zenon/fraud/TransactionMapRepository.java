package br.com.zenon.fraud;

import java.util.Map;
import java.util.Optional;

public class TransactionMapRepository implements TransactionRepository {

    private final Map<String, Transaction> originNameTransactionMap;

    public TransactionMapRepository(TransactionIngestor ingestor, String fileName, long totalLines) {
        this.originNameTransactionMap = ingestor.obtainOriginTransactionMapFromCSV(fileName, totalLines);
    }


    @Override
    public Optional<Transaction> findByTransactionCustomerOriginName(String name) {
        return Optional.ofNullable(originNameTransactionMap.get(name));
    }


}
