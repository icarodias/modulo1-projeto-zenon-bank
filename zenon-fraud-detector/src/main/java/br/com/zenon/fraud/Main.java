package br.com.zenon.fraud;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        final TransactionIngestor ingestor = new TransactionIngestor();

//        /* file in ../data dir and fileName PS_20174392719_1491204439457_log.csv */
//        final String relativeFilePath = "../data/paysim_with_bad_data.csv";
//        final int amountLines = 1000;
//
//        final List<Transaction> transactions =  ingestor.obtainTransactionsFromCSV(relativeFilePath, amountLines);
//        transactions.stream().limit(10).forEach(System.out::println);

        /* ------------------- */
        final FraudAnalyzer fraudAnalyzer = new FraudAnalyzer(ingestor);
        final String fileName = "../data/PS_20174392719_1491204439457_log.csv";
        final int totalLines = 10_000_000;

        System.out.println("1. Total de Fraudes: " + fraudAnalyzer.totalFrauds(fileName, totalLines));
        System.out.println("2. Top 3 Fraudes de Maior Valor:");
        fraudAnalyzer.topThreeFraudsAmount(fileName, totalLines).forEach(System.out::println);
        System.out.println("3. Clientes Suspeitos:");
        fraudAnalyzer.topFiveFraudSuspectCustomers(fileName, totalLines).forEach(System.out::println);
        System.out.println("4. Prejuízo Total: " + fraudAnalyzer.totalFraudsAmount(fileName, totalLines));
        System.out.println("5. Fraudes por Tipo:");
        fraudAnalyzer.totalFraudsByType(fileName, totalLines).forEach((k,v) -> System.out.println("- " + k +":" + v.toString()));

        /* ------------------- */
//        final String fileName = "../data/PS_20174392719_1491204439457_log.csv";
//        final int totalLines = 1_00_000;
//
//        final TransactionRepository listRepository = new TransactionListRepository(ingestor, fileName, totalLines);
//
//        Optional<Transaction> optTransaction1 = listRepository.findByTransactionCustomerOriginName("C1231006815");
//        optTransaction1.ifPresentOrElse(
//                System.out::println,
//                () -> System.out.println("transaction not found to origin customer: " + "C1231006815" ));
//
//        Optional<Transaction> optTransaction2 = listRepository.findByTransactionCustomerOriginName("C12345");
//        optTransaction2.ifPresentOrElse(
//                System.out::println,
//                () -> System.out.println("transaction not found to origin customer: " + "C12345" ));
//
//        long start = System.nanoTime();
//        listRepository.findByTransactionCustomerOriginName("C1231006815").get();
//        long end = System.nanoTime();
//
//        final TransactionRepository mapRepository = new TransactionMapRepository(ingestor, fileName, totalLines);
//        Optional<Transaction> optTransactionMap1 = mapRepository.findByTransactionCustomerOriginName("C1231006815");
//        optTransactionMap1.ifPresentOrElse(
//                System.out::println,
//                () -> System.out.println("transaction not found to origin customer: " + "C1231006815" ));
//
//        Optional<Transaction> optTransactionMap2 = mapRepository.findByTransactionCustomerOriginName("C12345");
//        optTransactionMap2.ifPresentOrElse(
//                System.out::println,
//                () -> System.out.println("transaction not found to origin customer: " + "C12345" ));
//
//
//        System.out.println("List Repository time: " + (end - start) + "ns");
//        long startMap = System.nanoTime();
//        mapRepository.findByTransactionCustomerOriginName("C1231006815").get();
//        long endMap = System.nanoTime();
//        System.out.println("Map Repository time:  " + (endMap - startMap) + "ns");


    }
}