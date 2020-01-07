package view;

import model.Currencies;
import model.exceptions.IdenticalCurrenciesException;
import model.exceptions.NoCurrenciesFoundException;
import model.exceptions.NoRatesFoundException;

public interface MoneyExchangeInterface {

    void initializeUserInterface(Currencies currencies);
    void prepareForInput();
    String getCurrencyFrom();
    String getCurrencyTo();
    float getAmount();
    void displayRate(String s);
    void handleNoCurrenciesFound(NoCurrenciesFoundException e);
    void handleNoRatesFound(NoRatesFoundException e);
    void handleIdenticalCurrencies(IdenticalCurrenciesException e);
}
