package in.healthhunt.model.utility;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by abhishekkumar on 4/22/18.
 */

public class HealthHuntUtility {

    public static String getTimeStamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = Calendar.getInstance().getTime();
        String timeStamp = dateFormat.format(date);
        return timeStamp;

    }

    public static String getUTCTimeStamp() {
        String timeStamp = "";
        Date date = new Date();
        DateFormat formatterUTC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatterUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
        timeStamp = formatterUTC.format(date);
        return timeStamp;

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        Date date = Calendar.getInstance().getTime();
//        String timeStamp = dateFormat.format(date);
//        return timeStamp;
    }

    public static String getMD5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String convertToHyperLink(String str) {
        SpannableString content = new SpannableString(str);
        content.setSpan(new UnderlineSpan(), 0, str.length(), 0);
        return content.toString();
    }

    //Convert dp into device specific pixels
    public static int dpToPx(int dp, Context context)
    {
        if(context != null)
        {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            //float logicalDensity = metrics.density;
            return (int) Math.ceil(dp * displayMetrics.density);
//Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        }
        return dp;
    }

    //Convert device pixels into device independent units
    public static int pxToDp(int px, Context context)
    {
        if(context != null)
        {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        }
        return px;
    }
}
