package bda.infrastructure.assembler.exchangerate;

import bda.domain.CurrencyPair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

@Component
public class FileToExchangeRateAssembler {

    private static final int BASE_CURRENCY_INDEX = 0;
    private static final int QUOTE_CURRENCY_INDEX = 1;
    private static final int AMOUNT_INDEX = 2;

    public Pair<CurrencyPair, Double> assemble(String exchangeRateLine) {
        String[] splitExchangeRate = exchangeRateLine.split(",");
        return Pair.of(CurrencyPair.withoutRate(splitExchangeRate[BASE_CURRENCY_INDEX], splitExchangeRate[QUOTE_CURRENCY_INDEX]),
                Double.parseDouble(splitExchangeRate[AMOUNT_INDEX]));
    }

}
