package bda.infrastructure;

import bda.domain.TransactionService;
import bda.infrastructure.assembler.transaction.FileToTransactionAssembler;
import bda.domain.CurrencyPair;
import bda.domain.Transaction;
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
public class TransactionServiceImpl implements TransactionService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Value("${currency}")
    private String quoteCurrency;
    @Value("${partner}")
    private String partner;
    @Value("${transactionsSource}")
    private String transactionsSource;

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private ExchangeRateServiceImpl exchangeRateService;
    @Autowired
    private FileToTransactionAssembler fileToTransactionAssembler;

    @Override
    public void saveAggregatedResults(Map<String, Double> aggregatedResults) {
        try {
            fileRepository.save(aggregatedResults.toString());
        } catch (IOException e) {
            log.error(String.format("An error occurred whilst trying to save Aggregated Transactions %s", e));
        }
    }

    @Override
    public Map<String, Double> aggregateTransactionsByPartner() {
        return new TransactionAggregator() {
            @Override
            protected Map<String, Double> aggregateTransactions(Path path) throws IOException {
                return Files.lines(path)
                        .parallel()
                        .map(fileToTransactionAssembler::assemble)
                        .map(this::convertCurrency)
                        .collect(Collectors.groupingBy(CurrencyPair::getBaseCurrency, Collectors.summingDouble(CurrencyPair::getRate)));
            }
        }.aggregate();
    }

    @Override
    public Map<String, Double> aggregateTransactionsOfPartner() {
        return new TransactionAggregator() {
            @Override
            protected Map<String, Double> aggregateTransactions(Path path) throws IOException {
                return Files.lines(path)
                        .parallel()
                        .filter(line -> line.contains(partner))
                        .map(fileToTransactionAssembler::assemble)
                        .map(this::convertCurrency)
                        .collect(Collectors.groupingBy(CurrencyPair::getBaseCurrency, Collectors.summingDouble(CurrencyPair::getRate)));
            }
        }.aggregate();
    }

    private abstract class TransactionAggregator {

        public Map<String, Double> aggregate() {
            try {
                Path path = Paths.get(Paths.get(".").toAbsolutePath().normalize().toString(), transactionsSource);
                return aggregateTransactions(path);
            } catch (IOException e) {
                log.error(String.format("An error occurred whilst trying to aggregate transactions %s", e));
            }
            return null;
        }

        public CurrencyPair convertCurrency(Transaction transaction) {
            if (transaction.getCurrency().equals(quoteCurrency)) {
                return CurrencyPair.withoutQuoteCurrency(transaction.getPartner(), transaction.getAmount());
            }
            return CurrencyPair.withoutQuoteCurrency(transaction.getPartner(), exchangeRateService
                    .getRateForCurrencyPair(CurrencyPair.withoutRate(transaction.getCurrency(), quoteCurrency)) * (transaction.getAmount()));
        }

        protected abstract Map<String, Double> aggregateTransactions(Path path) throws IOException;

    }

}
