package model.RatesLoaderImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;
import model.exceptions.NoRatesFoundException;
import view.CurrencyMatcher;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JSONRatesLoader implements RatesLoader {

    Currencies currencies;

    public JSONRatesLoader(Currencies c) {
        this.currencies = c;            // rates will only be found for expected currencies
    }

    @Override
    public CurrentRates load() throws NoRatesFoundException {
        HashMap map = deserialize();    // deserializes JSON to a nested map
        if (map == null || map.isEmpty())
            throw new NoRatesFoundException();
        return mapToRates(map);         // converts map to CurrentRates
    }

    private HashMap deserialize() {
        try (Reader reader = new FileReader("data sources\\randomrates.json")) {
            Gson gson = new Gson();
            // describe the structure of JSON
            Type compoundType = new TypeToken<HashMap<String, HashMap<String, Float>>>(){}.getType();
            // deserialize
            HashMap<String, HashMap<String, Float>> map =
                    gson.fromJson(reader, compoundType);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private CurrentRates mapToRates(HashMap map) {
        CurrentRates cr = new CurrentRates();
        CurrencyMatcher cc = new CurrencyMatcher(currencies);

        Iterator iFrom = map.entrySet().iterator();
        while (iFrom.hasNext()) {
            Map.Entry from = (Map.Entry) iFrom.next();
            HashMap toMap = (HashMap)from.getValue();
            Iterator iTo = toMap.entrySet().iterator();
            while (iTo.hasNext()) {
                Map.Entry to = (Map.Entry) iTo.next();
                Currency c1 = cc.convertStringToCurrency(from.getKey().toString());
                Currency c2 = cc.convertStringToCurrency(to.getKey().toString());
                float value = (float) to.getValue();

                if (c1 != null && c2 != null)
                    cr.add(new Rate(c1, c2, value));
            }
        }
        return cr;
    }
}
