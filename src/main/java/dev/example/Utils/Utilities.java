package dev.example.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

    public static Date ConvertDate(String date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date convert = null;
        try {
            convert = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convert;
    }
}
