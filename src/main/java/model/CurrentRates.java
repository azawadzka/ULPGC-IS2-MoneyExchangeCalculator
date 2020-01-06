package model;

import java.util.ArrayList;
import java.util.List;

public class CurrentRates {

    private Time time;
    private List<Rate> rates;

    public CurrentRates() {
        this.time = new Time();
        this.rates = new ArrayList<Rate>();
    }

    public void add(Rate r) {
        rates.add(r);
    }

    public List<Rate> getRates() {
        return rates;
    }

    public Time getTime() {
        return time;
    }
}
