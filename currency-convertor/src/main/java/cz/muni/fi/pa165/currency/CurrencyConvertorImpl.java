package cz.muni.fi.pa165.currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Currency;
import javax.inject.Named;


/**
 * This is base implementation of {@link CurrencyConvertor}.
 *
 * @author petr.adamek@embedit.cz
 */
@Named
public class CurrencyConvertorImpl implements CurrencyConvertor {

    private final ExchangeRateTable exchangeRateTable;

    private final Logger logger = LoggerFactory.getLogger(CurrencyConvertorImpl.class);

    public CurrencyConvertorImpl(ExchangeRateTable exchangeRateTable) {
        this.exchangeRateTable = exchangeRateTable;
    }

    @Override
    public BigDecimal convert(Currency sourceCurrency, Currency targetCurrency, BigDecimal sourceAmount) {
        logger.trace("Called method CurrencyConvertorImpl#convert");

        if (sourceCurrency == null) {
            throw new IllegalArgumentException("Argument sourceCurrency is null");
        }

        if (targetCurrency == null) {
            throw new IllegalArgumentException("Argument targetCurrency is null");
        }

        if (sourceAmount == null) {
            throw new IllegalArgumentException("Argument sourceAmount is null");
        }

        try {
            BigDecimal rate = exchangeRateTable.getExchangeRate(sourceCurrency, targetCurrency);

            if (rate == null) {
                logger.warn("Unknown exchange rate");
                throw new RuntimeException("Unknown exchange rate");
            }
            return sourceAmount.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (ExternalServiceFailureException e) {
            logger.error("External service failure exception!");
            throw new RuntimeException("Cannot convert source amount");
        }
    }

}
