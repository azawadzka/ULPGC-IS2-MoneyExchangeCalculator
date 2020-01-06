package model.CurrenciesLoaderImpl;

import com.google.gson.Gson;
import model.Currencies;
import model.CurrenciesLoader;
import model.Currency;
import model.exceptions.NoCurrenciesFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JSONCurrenciesLoader implements CurrenciesLoader {

    @Override
    public Currencies load() throws NoCurrenciesFoundException {

        try (Reader reader = new FileReader("data sources\\currencies.json")) {
            Gson gson = new Gson();
            Currency[] currencies = gson.fromJson(reader, Currency[].class);
            if (currencies.length == 0)
                throw new NoCurrenciesFoundException();
            return new Currencies(currencies);
        } catch (IOException e) {
            throw new NoCurrenciesFoundException();
        }
    }

}

