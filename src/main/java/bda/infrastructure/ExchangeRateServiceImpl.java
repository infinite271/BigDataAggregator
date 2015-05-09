package bda.infrastructure;

import bda.domain.ExchangeRateService;
import bda.infrastructure.assembler.exchangerate.FileToExchangeRateAssembler;
import bda.domain.CurrencyPair;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

    private Map<CurrencyPair, Double> exchangeRates = Maps.newConcurrentMap();

    @Value("${exchangeRatesSource}")
    private String exchangeRatesSource;

    @Autowired
    private FileToExchangeRateAssembler fileToExchangeRateAssembler;

    public Double getRateForCurrencyPair(CurrencyPair currencyPair) {
        if (exchangeRates.isEmpty()) {
            retrieveExchangeRates();
        }

        return exchangeRates.get(currencyPair);
    }

    private void retrieveExchangeRates() {
        try {
            Path path = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString(), exchangeRatesSource);
            exchangeRates = Files.lines(path)
                    .parallel()
                    .map(fileToExchangeRateAssembler::assemble)
                    .collect(Collectors.toConcurrentMap(Pair::getLeft, Pair::getRight));
        } catch (IOException e) {
            log.error(String.format("An error occurred whilst retrieving exchange rates %s", e));
        }
    }

}
