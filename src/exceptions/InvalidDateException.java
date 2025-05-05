package exceptions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InvalidDateException extends Exception {
    
    public InvalidDateException(Date date1, Date date2) {
        super("The date " + formatDate(date1) + " is before " + formatDate(date2) + "\n" +
              "Please enter a valid date!");
    }

    private static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
        return dateFormat.format(date);
    }
}
