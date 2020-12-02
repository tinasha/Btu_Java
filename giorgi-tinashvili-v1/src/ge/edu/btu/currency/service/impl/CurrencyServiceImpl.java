package ge.edu.btu.currency.service.impl;

import ge.edu.btu.currency.service.CurrencyService;

public class CurrencyServiceImpl implements CurrencyService {
    double rate;
    @Override
    public double setExchangeRate(double value) {

        rate = value;
        return value;
    }
    @Override
    public double convert(double amount) {
        return amount/rate;
    }


}
