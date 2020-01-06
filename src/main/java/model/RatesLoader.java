package model;

import model.exceptions.NoRatesFoundException;

public interface RatesLoader {
    CurrentRates load() throws NoRatesFoundException;
}
