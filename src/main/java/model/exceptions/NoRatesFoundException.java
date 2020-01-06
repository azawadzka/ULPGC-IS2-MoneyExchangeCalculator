package model.exceptions;

public class NoRatesFoundException extends Exception {

    String info = "No rates have been found!";

    public NoRatesFoundException() {}

    public String getInfo() {
        return info;
    }
}
