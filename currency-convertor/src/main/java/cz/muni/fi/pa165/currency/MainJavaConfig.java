package cz.muni.fi.pa165.currency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
// import org.springframework.config.java.context.JavaConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

public class MainJavaConfig {

    public static void main(String [] args) {
        ApplicationContext context = new JavaConfigApplicationContext(CurrencyConvertor.class, ExchangeRateTable.class);
        CurrencyConvertor convertor = context.getBean(CurrencyConvertorImpl.class);
        convertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), new BigDecimal("1"));
    }
}
