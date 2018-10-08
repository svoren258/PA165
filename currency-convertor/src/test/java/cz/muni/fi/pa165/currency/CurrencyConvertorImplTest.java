package cz.muni.fi.pa165.currency;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Big decimal - IEEE 754 (string = presna reprezentace)
 * double: "Translates a double into a BigDecimal, with rounding according to the context settings." = nepresne
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrencyConvertorImplTest {

    private CurrencyConvertorImpl convertor;

    @Mock
    private ExchangeRateTable exchangeRateTable;

    @Before
    public void setUp() throws ExternalServiceFailureException {
        Mockito.when(exchangeRateTable.getExchangeRate(
                Currency.getInstance("EUR"),
                Currency.getInstance("CZK"))
        ).thenReturn(new BigDecimal("25.7660703"));

        Mockito.when(exchangeRateTable.getExchangeRate(
                Currency.getInstance("USD"),
                Currency.getInstance("CZK"))
        ).thenReturn(new BigDecimal("22.1965728"));

        Mockito.when(exchangeRateTable.getExchangeRate(
                Currency.getInstance("CZK"),
                Currency.getInstance("USD"))
        ).thenThrow(ExternalServiceFailureException.class);

        convertor = new CurrencyConvertorImpl(exchangeRateTable);
    }

    @Test
    public void testConvert() {
        // case 1
        BigDecimal result = convertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), new BigDecimal("1"));
        TestCase.assertEquals("25.77", result.toString());

        // case 2
        result = convertor.convert(Currency.getInstance("USD"), Currency.getInstance("CZK"), new BigDecimal("1"));
        TestCase.assertEquals("22.20", result.toString());

        // case 3
        result = convertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), new BigDecimal("2"));
        TestCase.assertEquals("51.53", result.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithNullSourceCurrency() {
        convertor.convert(null, Currency.getInstance("CZK"), new BigDecimal("1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithNullTargetCurrency() {
        convertor.convert(Currency.getInstance("EUR"), null, new BigDecimal("1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertWithNullSourceAmount() {
        convertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), null);
    }

    @Test(expected = RuntimeException.class)
    public void testConvertWithUnknownCurrency() {
        convertor.convert(Currency.getInstance("CHF"), Currency.getInstance("CZK"), new BigDecimal("1"));
    }

    @Test(expected = RuntimeException.class)
    public void testConvertWithExternalServiceFailure() {
        convertor.convert(Currency.getInstance("CZK"), Currency.getInstance("USD"), new BigDecimal("1"));
    }

}
