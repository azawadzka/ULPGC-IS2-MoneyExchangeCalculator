package view.swing;

import controller.Controller;
import model.Currencies;
import model.Currency;
import model.exceptions.IdenticalCurrenciesException;
import model.exceptions.NoCurrenciesFoundException;
import model.exceptions.NoRatesFoundException;
import view.MoneyExchangeInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.BorderLayout.*;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

public class MainWindow extends JFrame implements MoneyExchangeInterface {

    JComboBox fromComboBox;
    JComboBox toComboBox;
    JTextField inputAmount;
    JButton button;
    JButton restart;
    JLabel resultLabel;

    public static void main(String[] args) {
        new MainWindow();
    }

    public MainWindow() {
        super("Money Exchange");
        setSize(520, 160);
        setResizable(true);
        adjustComponents();

        Controller.init(this);
        Controller.startNewSession();

        this.setVisible(true);
    }

    private void adjustComponents() {

        // main panel
        JPanel mainPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        mainPanel.setSize(500, 150);
        //mainPanel.setBackground(Color.red);
        //panel1.setBackground(Color.blue);
        //panel2.setBackground(Color.green);
        //panel3.setBackground(Color.yellow);

        mainPanel.setLayout(new GridLayout(3, 1));


        this.add(mainPanel);
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        // 1st row
        inputAmount = new JTextField("", 8);
        fromComboBox = new JComboBox();
        JLabel arrowLabel = new JLabel("⟶");
        toComboBox = new JComboBox();

        fromComboBox.setSize(100, 30);

        panel1.add(inputAmount);
        panel1.add(fromComboBox);
        panel1.add(arrowLabel);
        panel1.add(toComboBox);

        // 2nd row
        button = new JButton("Count");
        restart = new JButton("Restart");

        panel2.add(button);
        panel2.add(restart);

        // 3rd row
        resultLabel = new JLabel();
        panel3.add(resultLabel, WEST);

        // action listeners
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkCorrectionOfData())
                    Controller.requestCalculation();
            }
        });

        restart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
    }

    @Override
    public void initializeUserInterface(Currencies currencies) {
        fromComboBox.removeAllItems();
        toComboBox.removeAllItems();
        for (Currency c : currencies.getCurrencies()) {
            fromComboBox.addItem(c.getName());
            toComboBox.addItem(c.getName());
        }
        toComboBox.setSelectedIndex(1);
    }

    @Override
    public void prepareForInput() {
        clear();
    }

    @Override
    public String getCurrencyFrom() {
        return fromComboBox.getSelectedItem().toString();
    }

    @Override
    public String getCurrencyTo() {
        return toComboBox.getSelectedItem().toString();
    }

    @Override
    public float getAmount() { //the correction of format needs to be checked before data request!
        return Float.parseFloat(inputAmount.getText());
    }

    @Override
    public void displayRate(String s) {
        resultLabel.setText(inputAmount.getText() + " " + fromComboBox.getSelectedItem() + " is " + s + " " + toComboBox.getSelectedItem());
    }

    @Override
    public void handleNoCurrenciesFound(NoCurrenciesFoundException e) {
        JOptionPane.showMessageDialog(this, e.getInfo(), "Warning", WARNING_MESSAGE);
    }

    @Override
    public void handleNoRatesFound(NoRatesFoundException e) {
        JOptionPane.showMessageDialog(this, e.getInfo(), "Warning", WARNING_MESSAGE);
    }

    @Override
    public void handleIdenticalCurrencies(IdenticalCurrenciesException e) {
        JOptionPane.showMessageDialog(this, e.getInfo(), "Warning", WARNING_MESSAGE);
    }

    private void handleNumberFormatException() {
        JOptionPane.showMessageDialog(this, "Introduce a correct amount!", "Warning", WARNING_MESSAGE);
    }

    private void clear() {
        inputAmount.setText("Type amount");
        resultLabel.setText("");
    }

    private boolean checkCorrectionOfData() {

        // value
        try {
            Float.parseFloat(inputAmount.getText());
            return true;
        } catch (NumberFormatException e) {
            handleNumberFormatException();
        }
        return false;
    }

}
