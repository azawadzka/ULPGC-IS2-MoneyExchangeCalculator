package view.swing;

import model.Currencies;
import model.exceptions.NoCurrenciesFoundException;
import model.exceptions.NoRatesFoundException;
import view.MoneyExchangeInterface;

import javax.swing.*;

public class MainWindow extends JFrame implements MoneyExchangeInterface {

    JPanel mainPanel = new JPanel();
    JLabel fromLabel = new JLabel("From");
    JLabel toLabel = new JLabel("To");

    public static void main(String[] args) {
        new MainWindow();
    }

    public MainWindow() {
        super("Money Exchange");
        setSize(300, 400);
        setResizable(false);
        setVisible(true);

        mainPanel.add(fromLabel);

    }

    @Override
    public void initializeUserInterface(Currencies currencies) {

    }

    @Override
    public void startProcess() {

    }

    @Override
    public String getCurrencyFrom() {
        return null;
    }

    @Override
    public String getCurrencyTo() {
        return null;
    }

    @Override
    public float getAmount() {
        return 0;
    }

    @Override
    public void displayRate(String s) {

    }

    @Override
    public void handleNoCurrenciesFound(NoCurrenciesFoundException e) {

    }

    @Override
    public void handleNoRatesFound(NoRatesFoundException e) {

    }
}
