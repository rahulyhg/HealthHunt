package in.healthhunt.model.beans;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by abhishekkumar on 4/22/18.
 */

public class Utility {

    public static String getTimeStamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = Calendar.getInstance().getTime();
        String timeStamp = dateFormat.format(date);

        return timeStamp;

    }
}
