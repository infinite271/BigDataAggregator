package bda.application;

import bda.application.aspect.Benchmark;
import bda.domain.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Aggregator {

    @Autowired
    private TransactionManager transactionManager;

    @Benchmark
    public void aggregateByPartner() {
        transactionManager.aggregateTransactionsByPartner();
        transactionManager.printAggregatedResults();
    }

    @Benchmark
    public void aggregateOfPartner() {
        transactionManager.aggregateTransactionsOfPartner();
        transactionManager.printAggregatedResults();
    }

}
