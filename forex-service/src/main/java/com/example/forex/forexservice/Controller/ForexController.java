package com.example.forex.forexservice.Controller;

import com.example.forex.forexservice.Entity.ExchangeValue;
import com.example.forex.forexservice.Repository.ExchangeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForexController {
    @Autowired
    private Environment environment;
    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to){
      ExchangeValue exchangeValue=exchangeValueRepository.findByFromAndTo(from, to);

      exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

      return exchangeValue;
    }
}
