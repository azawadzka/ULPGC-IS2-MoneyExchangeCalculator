package model;

import model.RatesLoaderImpl.JSONRatesLoader;
import model.exceptions.NoRatesFoundException;

public class RateRequest {

    Currencies currencies;
    CurrentRates rates;
    Currency from;
    Currency to;
    float value;

    public RateRequest(Currencies currencies, Currency from, Currency to, float value) throws NoRatesFoundException {
        if (from == null || to == null || value == 0) throw new NoRatesFoundException();

        this.from = from;
        this.to = to;
        this.value = value;
        this.currencies = currencies;
        this.rates = new JSONRatesLoader(currencies).load();
    }

    public float calculate() throws NoRatesFoundException {
        float rate = findRate(from, to);
        return Calculation.calculate(rate, value);
    }

    private float findRate(Currency from, Currency to) throws NoRatesFoundException {
        for (Rate r : rates.getRates()) {
            if (r.getFrom().equals(from) && r.getTo().equals(to))
                return r.getValue();
        }
        throw new NoRatesFoundException();
    }

}
