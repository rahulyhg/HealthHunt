package in.healthhunt.model.utility;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import in.healthhunt.R;
import in.healthhunt.model.beans.Constants;

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
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
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

    public static String getDateWithFormat(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date date = format.parse(strDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            format = new SimpleDateFormat("d MMM yyyy");
            String currentDate = format.format(calendar.getTime());
            Log.i("TAG12222", "cure " + currentDate);
            return currentDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getCategoryIcon(String categoryName){

        int res = 0;
        if(categoryName != null){

            if(categoryName.equalsIgnoreCase(Constants.NUTRITION)){
                res = R.mipmap.ic_nutrition_icon;
            }
            else if(categoryName.equalsIgnoreCase(Constants.FITNESS)){
                res = R.mipmap.ic_fitness;
            }
            else if(categoryName.equalsIgnoreCase(Constants.ORGANIC_BEAUTY)){
                res = R.mipmap.ic_organic_beauty;
            }
            else if(categoryName.equalsIgnoreCase(Constants.MENTAL_WELLBEING)){
                res = R.mipmap.ic_mental_well;
            }
            else if(categoryName.equalsIgnoreCase(Constants.LOVE)){
                res = R.mipmap.ic_love_icon;
            }
            else {
                res = R.mipmap.ic_all;
            }
        }
        return res;
    }
}
