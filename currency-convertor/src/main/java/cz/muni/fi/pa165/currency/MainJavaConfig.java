package cz.muni.fi.pa165.currency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
// import org.springframework.config.java.context.JavaConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

@Configuration
@ComponentScan("cz.muni.fi.pa165.currency")
@EnableAspectJAutoProxy
public class MainJavaConfig {

    public static void main(String [] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CurrencyConvertorImpl.class, ExchangeRateTableImpl.class);
        CurrencyConvertor convertor = context.getBean(CurrencyConvertorImpl.class);
        convertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), new BigDecimal("1"));
    }
}
