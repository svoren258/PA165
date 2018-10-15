package cz.muni.fi.pa165.currency;

import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Currency;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ExchangeRateTableImpl implements ExchangeRateTable {

    public BigDecimal getExchangeRate(Currency sourceCurrency, Currency targetCurrency) {
        if (sourceCurrency.getCurrencyCode().equals("EUR") && targetCurrency.getCurrencyCode().equals("CZK")) {
            return new BigDecimal("27");
        } else {
            return null;
        }
    }
}
