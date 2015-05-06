package bda.domain;

import bda.application.aspect.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TransactionManager {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TransactionManager.class);

    private static Map<String, Double> aggregatedResults;

    @Autowired
    private TransactionService transactionService;

    @Log
    public void aggregateTransactionsByPartner() {
        aggregatedResults = transactionService.aggregateTransactionsByPartner();
        transactionService.saveAggregatedResults(aggregatedResults);
    }

    @Log
    public void aggregateTransactionsOfPartner(){
        aggregatedResults = transactionService.aggregateTransactionsOfPartner();
    }

    public void printAggregatedResults() {
        log.info(String.format("Aggregated Results are: %s", aggregatedResults));
    }

}
