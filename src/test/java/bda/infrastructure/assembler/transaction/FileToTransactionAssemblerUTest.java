package bda.infrastructure.assembler.transaction;

import bda.domain.Transaction;
import bda.infrastructure.assembler.transaction.FileToTransactionAssembler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileToTransactionAssemblerUTest {

    private static final String GBP = "GBP";
    private static final String DEFENCE_LTD = "Defence ltd.";

    private FileToTransactionAssembler fileToTransactionAssembler = new FileToTransactionAssembler();

    @Test
    public void testAssemble() {
        // setup
        double transactionAmount = 827.4293638063384;
        String fileLine = "Defence ltd.,GBP,827.4293638063384";

        Transaction expectedTransaction = new Transaction(DEFENCE_LTD, GBP, transactionAmount);

        // act
        Transaction transaction = fileToTransactionAssembler.assemble(fileLine);

        // assert
        assertEquals(expectedTransaction, transaction);
    }

}
