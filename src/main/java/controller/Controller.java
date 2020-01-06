package controller;

import model.Currencies;
import model.CurrenciesLoaderImpl.JSONCurrenciesLoader;
import model.CurrencyMatcher;
import model.RateRequest;
import model.exceptions.NoCurrenciesFoundException;
import model.exceptions.NoRatesFoundException;
import view.MoneyExchangeInterface;
import view.console.ConsoleInterfaceImpl;

public class Controller {

    private static MoneyExchangeInterface ui;
    private static Currencies currencies;

    public static void init() {
        ui = new ConsoleInterfaceImpl();
    }

    public static void startNewSession() {
        try {
            currencies = new JSONCurrenciesLoader().load();
            ui.initializeUserInterface(currencies);
            ui.enableInput();
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
            System.exit(1);
        }
    }
}
