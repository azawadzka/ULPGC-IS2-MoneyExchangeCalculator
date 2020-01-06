package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {

    private Date date;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Time() {
        this.date = new Date(); //current date
    }

    public Date getTime() {
        return date;
    }

    public String getFormattedTime() {
        return formatter.format(date);
    }


}
