package com.example.currency.currencyconversion.Controller;

import com.example.currency.currencyconversion.Bean.CurrencyConversionBean;
import com.example.currency.currencyconversion.Service.CurrencyExchangeServiceProxy;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class CurrencyConversionController {
    private Logger logger= Logger.getLogger(String.valueOf(this.getClass()));

    @Autowired
    CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean covertCurrency(@PathVariable String from, @PathVariable String to,
                                                 @PathVariable BigDecimal quantity){
        /*Map<String, String> map=new HashMap<>();
        map.put("from", from);
        map.put("to", to);*/

        /*ResponseEntity<CurrencyConversionBean> responseEntity=new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, map
        );
        CurrencyConversionBean response=responseEntity.getBody();
        return new CurrencyConversionBean(response.getId(),from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());*/

       CurrencyConversionBean response=currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
}
