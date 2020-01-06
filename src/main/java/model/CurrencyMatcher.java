package model;

import model.Currencies;
import model.Currency;

public class CurrencyMatcher {
    
    private Currencies currencies;

    public CurrencyMatcher(Currencies currencies) {
        this.currencies = currencies;
    }
    
    public Currency convertStringToCurrency(String s) {
        for (Currency c : currencies.getCurrencies()) {
            if (c.getName().equals(s)) {
                return c;
            }
        }
        return null;
    }
}
