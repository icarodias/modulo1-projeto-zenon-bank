package br.com.zenon.fraud;

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
        final int totalLines = 50000;

        System.out.println("1. Total de Fraudes: " + fraudAnalyzer.totalFrauds(fileName, totalLines));
        System.out.println("2. Top 3 Fraudes de Maior Valor:");
        fraudAnalyzer.topThreeFraudsAmount(fileName, totalLines).forEach(System.out::println);
        System.out.println("3. Clientes Suspeitos:");
        fraudAnalyzer.topFiveFraudSuspectCustomers(fileName, totalLines).forEach(System.out::println);
        System.out.println("4. Prejuízo Total: " + fraudAnalyzer.totalFraudsAmount(fileName, totalLines));
        System.out.println("5. Fraudes por Tipo:");
        fraudAnalyzer.totalFraudsByType(fileName, totalLines).forEach((k,v) -> System.out.println("- " + k +":" + v.toString()));
    }
}