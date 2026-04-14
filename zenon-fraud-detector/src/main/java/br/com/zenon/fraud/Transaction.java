package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(Integer step,
                          TransactionType type,
                          BigDecimal amount,
                          TransactionCustomer origin,
                          TransactionCustomer recipient,
                          Boolean isFraud,
                          Boolean isFlaggedFraud) {

    @Override
    public String toString() {
        return String.format("""
                        \tstep: %s
                        \ttype: %s
                        \tamount: %s
                        \tnameOrig: %s
                        \toldbalanceOrg: %s
                        \tnewbalanceOrig: %s
                        \tnameDest: %s
                        \toldbalanceDest: %s
                        \tnewbalanceDest: %s
                        \tisFraud: %s
                        \tisFlaggedFraud: %s
                        """,
                step, type, amount,
                origin.name(), origin.oldBalance(), origin.newBalance(),
                recipient.name(), recipient.oldBalance(), recipient.newBalance(),
                isFraud, isFlaggedFraud);
    }
}
