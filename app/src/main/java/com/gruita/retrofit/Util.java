package com.gruita.retrofit;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by cgruita on 9/23/16.
 */
public class Util {

    /**
     * Returns the small weather resource icon based on a string
     * @param overview
     *          input string
     * @return
     *          the icon represeting the weather
     */
    public static int getOverviewIconResourceFromText(String overview){

        switch (overview) {
            case Const._01D:
                return R.drawable.ic_clear;
            case Const._02D:
                return R.drawable.ic_light_clouds;
            case Const._03D:
                return R.drawable.ic_cloudy;
            case Const._04D:
                return R.drawable.ic_cloudy;
            case Const._09D:
                return R.drawable.ic_light_rain;
            case Const._10D:
                return R.drawable.ic_rain;
            case Const._11D:
                return R.drawable.ic_storm;
            case Const._13D:
                return R.drawable.ic_snow;
            case Const._50D:
                return R.drawable.ic_fog;
            default:
//					error message
                return R.drawable.ic_clear;

        }
    }

    /**
     * Returns the larger icon based on the input string
     * @param overview
     *          input string
     * @return
     *          the large weather resource icon
     */
    public static int getDetailIconResourceFromText(String overview){

        switch (overview) {
            case Const._01D:
                return R.drawable.art_clear;
            case Const._02D:
                return R.drawable.art_light_clouds;
            case Const._03D:
                return R.drawable.art_clouds;
            case Const._04D:
                return R.drawable.art_clouds;
            case Const._09D:
                return R.drawable.art_light_rain;
            case Const._10D:
                return R.drawable.art_rain;
            case Const._11D:
                return R.drawable.art_storm;
            case Const._13D:
                return R.drawable.art_snow;
            case Const._50D:
                return R.drawable.art_fog;
            default:
//					error message
                return R.drawable.art_clear;

        }
    }


    /**
     * based on the number of the day (0 being today), formats the day to be displayed
     * @param dayOfWeek
     *          day of the week, 0 to 6
     * @return
     *          Detailed day
     */
    public static String getDetailedDayFromInt(int dayOfWeek){

        DateTime translatedDay = new DateTime().plusDays(dayOfWeek);
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM dd");
        System.out.println(dtf.print(translatedDay));

        switch (dayOfWeek){
            case 0:
                String month = translatedDay.monthOfYear().getAsText();
                int day = translatedDay.dayOfMonth().get();
                return "Today, \n" + month + " "  +day;
            case 1:
                month = translatedDay.monthOfYear().getAsText();
                day = translatedDay.dayOfMonth().get();
                return "Tomorrow, \n"  + month + " "  +day;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                month = translatedDay.monthOfYear().getAsText();
                day = translatedDay.dayOfMonth().get();
                return getStringDayFromInt(dayOfWeek) + ", " + month + " " + day;
            default:
                return "";
        }
    }

    /**
     * Compact representation of the day
     * @param dayOfWeek
     *          day of the week, 0 to 6
     * @return
     *          Compact day
     */
    public static String getStringDayFromInt(int dayOfWeek){

        DateTime today = new DateTime();
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM dd");
        System.out.println(dtf.print(today));

        switch (dayOfWeek){
            case 0:
                String month = today.monthOfYear().getAsText();
                int day = today.dayOfMonth().get();
                return "Today,"  + month + " "  +day;
            case 1:
                return "Tomorrow";
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return today.plusDays(dayOfWeek).dayOfWeek().getAsText();
            default:
                return "";
        }
    }
}
