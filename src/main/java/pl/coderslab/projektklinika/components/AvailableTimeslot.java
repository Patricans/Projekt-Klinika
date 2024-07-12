package pl.coderslab.projektklinika.components;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.Pair;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Setter @Getter
public class AvailableTimeslot {
    private Date start, end;
    public String getDisplayName() {
        DateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DateFormat secondFormat = new SimpleDateFormat("HH:mm");
        return fullDateFormat.format(start) + " - " + secondFormat.format(end);
    }
    public String getKey() {
        return Long.valueOf(start.getTime()).toString();
    }
    public AvailableTimeslot() {}
    public AvailableTimeslot(String svalue) {
        Long time = Long.valueOf(svalue);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        this.start = calendar.getTime();
        calendar.add(Calendar.MINUTE, 30);
        this.end = calendar.getTime();
    }


}