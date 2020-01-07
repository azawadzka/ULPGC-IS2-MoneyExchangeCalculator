package model.exceptions;

public class IdenticalCurrenciesException extends Exception {

    String info = "The currencies have to be different!";

    public IdenticalCurrenciesException() {}

    public String getInfo() {
        return info;
    }
}
