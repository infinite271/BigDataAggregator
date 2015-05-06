package bda.infrastructure.assembler;

import bda.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class FileToTransactionAssembler {

    private static final int PARTNER_INDEX = 0;
    private static final int CURRENCY_INDEX = 1;
    private static final int AMOUNT_INDEX = 2;

    public Transaction assemble(String transactionLine) {
        String[] splitTransactionLine = transactionLine.split(",");
        return new Transaction(splitTransactionLine[PARTNER_INDEX], splitTransactionLine[CURRENCY_INDEX], Double.parseDouble(splitTransactionLine[AMOUNT_INDEX]));
    }

}
