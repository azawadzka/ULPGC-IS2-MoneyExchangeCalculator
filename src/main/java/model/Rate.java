package model;

public class Rate {

    private Currency from;
    private Currency to;
    private float value;

    public Rate(Currency from, Currency to, float value) {
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public float getValue() {
        return value;
    }
}
