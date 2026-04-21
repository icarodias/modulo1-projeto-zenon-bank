package br.com.zenon.fraud;

public class ReportMain {


    public static void main(String[] args) {
        final TransactionIngestor ingestor = new TransactionIngestor();
        final TransactionReport transactionReport = new TransactionReport(ingestor);
        final String fileName = "../data/PS_20174392719_1491204439457_log.csv";


        System.out.println("Total de linhas: " + transactionReport.totalLines(fileName));
        System.out.println("Total de fraudes: " + transactionReport.totalFrauds(fileName));
        System.out.println("Total do valor transacionado: " + transactionReport.totalAmount(fileName));
    }
}
