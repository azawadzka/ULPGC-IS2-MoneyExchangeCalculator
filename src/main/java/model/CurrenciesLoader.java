package model;

import model.exceptions.NoCurrenciesFoundException;

public interface CurrenciesLoader {
    Currencies load() throws NoCurrenciesFoundException;
}