package view.console;

import controller.Controller;
import model.Currencies;
import model.Currency;
import model.exceptions.IdenticalCurrenciesException;
import view.CurrencyMatcher;
import model.exceptions.NoCurrenciesFoundException;
import model.exceptions.NoRatesFoundException;
import view.MoneyExchangeInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInterfaceImpl implements MoneyExchangeInterface {

    Currencies currencies;
    String c1;
    String c2;
    float value;

    public static void main(String[] args) {
        new ConsoleInterfaceImpl();
    }

    public ConsoleInterfaceImpl() {
        Controller.init(this);
        while(true) {
            Controller.startNewSession();
            Controller.requestCalculation();
        }
    }

    @Override
    public void initializeUserInterface(Currencies currencies) {
        this.currencies = currencies;
        printAvailableCurrencies();
    }

    @Override
    public void prepareForInput() {

        System.out.print("Choose the original currency. Type the currency code: ");
        c1 = currencyCodeFromUser();
        while (!(checkCurrencyFormat(c1) && checkIfCurrencyAvailable(c1))) {
            System.err.println("The code is incorrect or not available. Type another code: ");
            c1 = currencyCodeFromUser();
        }
        System.out.print("Choose the desired currency. Type the currency code: ");
        c2 = currencyCodeFromUser();
        while (!(checkCurrencyFormat(c2) && checkIfCurrencyAvailable(c2))) {
            System.err.println("The code is incorrect or not available. Type another code: ");
            c2 = currencyCodeFromUser();
        }
        System.out.print("Type a numeric amount of money: ");
        value = valueFromUser();
    }

    @Override
    public String getCurrencyFrom() {
        return c1;
    }

    @Override
    public String getCurrencyTo() {
        return c2;
    }

    @Override
    public float getAmount() {
        return value;
    }

    @Override
    public void displayRate(String result) {
        System.out.println(value + " " + c1 + " is " + result + " " + c2 + "\n");
    }

    @Override
    public void handleNoCurrenciesFound(NoCurrenciesFoundException e) {
        System.err.println(e.getInfo());
    }

    @Override
    public void handleNoRatesFound(NoRatesFoundException e) {
        System.err.println(e.getInfo());
    }

    @Override
    public void handleIdenticalCurrencies(IdenticalCurrenciesException e) {
        System.err.println(e.getInfo());
    }

    private void printAvailableCurrencies() {
        System.out.print("Available currencies: ");
        for (Currency c : currencies.getCurrencies()) {
            System.out.print(c.getName() + " ");
        }
        System.out.print("\n");
    }

    private boolean checkCurrencyFormat(String str) {
        String pattern = "[A-Z]{3}";
        return str.matches(pattern);
    }

    private boolean checkIfCurrencyAvailable(String str) {
        CurrencyMatcher cm = new CurrencyMatcher(currencies);
        return cm.convertStringToCurrency(str) != null;
    }

    private String currencyCodeFromUser() {
        Scanner scanner = new Scanner(System.in);
        CurrencyMatcher cm = new CurrencyMatcher(currencies);
        while (true) {
            try {
                return scanner.nextLine();
            }
            catch (java.util.InputMismatchException e) {
                System.err.print("The code was incorrect. Please type the correct code: ");
                scanner.nextLine();
            }
        }
    }

    private float valueFromUser() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextFloat();
            } catch (InputMismatchException e) {
                System.err.print("The number was incorrect. Please type the correct number: ");
                scanner.nextLine();
            }
        }
    }
}
