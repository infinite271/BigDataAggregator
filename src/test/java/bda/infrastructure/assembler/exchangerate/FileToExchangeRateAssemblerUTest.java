package bda.infrastructure.assembler.exchangerate;

import bda.domain.CurrencyPair;
import bda.infrastructure.assembler.exchangerate.FileToExchangeRateAssembler;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

public class FileToExchangeRateAssemblerUTest {

    private static final String USD = "USD";
    private static final String EUR = "EUR";

    private FileToExchangeRateAssembler fileToExchangeRateAssembler = new FileToExchangeRateAssembler();

    @Test
    public void testAssembleWithoutRate() {
        // setup
        String exchangeRate = "0.9251400028";
        String fileLine = "USD,EUR,0.9251400028";

        CurrencyPair currencyPair = CurrencyPair.withoutRate(USD, EUR);
        Pair<CurrencyPair, Double> expectedResult = Pair.of(currencyPair, new Double(exchangeRate));

        // act
        Pair<CurrencyPair, Double> actualResult = fileToExchangeRateAssembler.assemble(fileLine);

        // assert
        Assert.assertEquals(expectedResult, actualResult);
    }

}
