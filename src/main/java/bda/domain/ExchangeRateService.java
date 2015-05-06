package bda.domain;

import bda.model.CurrencyPair;

public interface ExchangeRateService {

    Double getRateForCurrencyPair(CurrencyPair currencyPair);

}
