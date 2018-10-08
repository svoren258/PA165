package cz.muni.fi.pa165.currency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

public class MainJavaConfig {

    public static void main(String [] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("javaConfig.xml");
        CurrencyConvertor convertor = context.getBean(CurrencyConvertor.class);
        convertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), new BigDecimal("1"));
    }
}
