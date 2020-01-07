package controller;

import model.Currencies;
import model.CurrenciesLoaderImpl.JSONCurrenciesLoader;
import model.exceptions.IdenticalCurrenciesException;
import view.CurrencyMatcher;
import model.RateRequest;
import model.exceptions.NoCurrenciesFoundException;
import model.exceptions.NoRatesFoundException;
import view.MoneyExchangeInterface;

public class Controller {

    private static MoneyExchangeInterface ui;
    private static Currencies currencies;

    public static void init(MoneyExchangeInterface view) {
        ui = view;
    }

    public static void startNewSession() {
        try {
            currencies = new JSONCurrenciesLoader().load();
            ui.initializeUserInterface(currencies);
            ui.prepareForInput();
        } catch (NoCurrenciesFoundException e) {
            ui.handleNoCurrenciesFound(e);
            System.exit(1);
        }
    }

    public static void requestCalculation() {
        try {
            CurrencyMatcher cm = new CurrencyMatcher(currencies);
            RateRequest rr = new RateRequest(
                    currencies,
                    cm.convertStringToCurrency(ui.getCurrencyFrom()),
                    cm.convertStringToCurrency(ui.getCurrencyTo()),
                    ui.getAmount());
            float calc = rr.calculate();
            ui.displayRate(Float.toString(calc));
        } catch (NoRatesFoundException e) {
            ui.handleNoRatesFound(e);
        } catch (IdenticalCurrenciesException e) {
            ui.handleIdenticalCurrencies(e);
        }
    }
}
