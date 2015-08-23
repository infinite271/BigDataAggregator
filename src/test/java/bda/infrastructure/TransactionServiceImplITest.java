package bda.infrastructure;

import bda.Main;
import bda.domain.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringApplicationConfiguration(classes = Main.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionServiceImplITest {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testAggregateTransactionsByPartner() {
        // act
        Map<String, Double> results = transactionService.aggregateTransactionsByPartner();

        // assert
        assertNotNull(results);
    }

    @Test
    public void testAggregateTransactionsOfPartner() {
        // act
        Map<String, Double> results = transactionService.aggregateTransactionsOfPartner();

        // assert
        assertNotNull(results);
        assertEquals(1, results.size());
    }

}
