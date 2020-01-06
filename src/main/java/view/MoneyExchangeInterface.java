package view;

import model.Currencies;
import model.exceptions.NoCurrenciesFoundException;
import model.exceptions.NoRatesFoundException;

public interface MoneyExchangeInterface {

    void initializeUserInterface(Currencies currencies);
    void enableInput();
    String getCurrencyFrom();
    String getCurrencyTo();
    float getAmount();
    void displayRate(String s);
    void handleNoCurrenciesFound(NoCurrenciesFoundException e);
    void handleNoRatesFound(NoRatesFoundException e);
}
