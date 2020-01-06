package model.exceptions;

public class NoCurrenciesFoundException extends Exception {

    String info = "No currencies have been found!";

    public NoCurrenciesFoundException() {}

    public String getInfo() {
        return info;
    }
}
