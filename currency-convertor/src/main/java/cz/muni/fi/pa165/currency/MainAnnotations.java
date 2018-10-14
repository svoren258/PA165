package cz.muni.fi.pa165.currency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

public class MainAnnotations {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("cz.muni.fi.pa165.currency");
        ((AnnotationConfigApplicationContext) context).scan("cz.muni.fi.pa165.currency");
        ((AnnotationConfigApplicationContext) context).refresh();
        CurrencyConvertor convertor =  context.getBean(CurrencyConvertorImpl.class);
        convertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), new BigDecimal("1"));
    }
}