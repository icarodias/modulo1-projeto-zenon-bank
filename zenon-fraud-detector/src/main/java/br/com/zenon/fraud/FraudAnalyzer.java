package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class FraudAnalyzer {

    private final TransactionIngestor ingestor;

    public FraudAnalyzer(TransactionIngestor ingestor) {
        this.ingestor = ingestor;
    }


    public long totalFrauds(String fileName, long amountLines) {
        return ingestor.obtainTransactionsFromCSV(fileName, amountLines)
                .stream()
                .filter(Transaction::isFraud)
                .count();
    }

    public List<BigDecimal> topThreeFraudsAmount(String fileName, long amountLines) {
        return ingestor.obtainTransactionsFromCSV(fileName, amountLines)
                .stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .limit(3)
                .map(Transaction::amount)
                .toList();
    }

    public List<String> topFiveFraudSuspectCustomers(String fileName, long amountLines) {
        return ingestor.obtainTransactionsFromCSV(fileName, amountLines)
                .stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .map(Transaction::origin)
                .map(TransactionCustomer::name)
                .distinct()
                .limit(5)
                .toList();
    }

    public BigDecimal totalFraudsAmount(String fileName, long amountLines) {
        return ingestor.obtainTransactionsFromCSV(fileName,amountLines)
                .stream()
                .filter(Transaction::isFraud)
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<TransactionType, Long> totalFraudsByType(String fileName, long amountLines) {
        return ingestor.obtainTransactionsFromCSV(fileName,amountLines)
                .stream()
                .filter(Transaction::isFraud)
                .collect(Collectors.groupingBy(
                        Transaction::type,
                        Collectors.counting())
                );
    }


}
