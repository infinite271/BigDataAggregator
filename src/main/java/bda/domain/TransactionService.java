package bda.domain;

import java.util.Map;

public interface TransactionService {

    Map<String, Double> aggregateTransactionsByPartner();

    Map<String, Double> aggregateTransactionsOfPartner();

    void saveAggregatedResults(Map<String, Double> results);

}
