package se.ecutb.cardealer.constants;

public class RegExp {

    // 135-355 mm. Steps of 10 mm.
    public static final String TIRE_WIDTH_PATTERN = "^(1[3-9]5|2[0-9]5|3[0-5]5)$";
    // 30-80 %. Steps of 5.
    public static final String TIRE_PROFILE_PATTERN = "^([3-8][05])$";
    // B-Y
    public static final String TIRE_SPEED_RATING_PATTERN = "^([B-Y])$";
    // 3-20 characters, only a-z and 0-9. First character must be a-z.
    public static final String USERNAME_REGEXP_PATTERN = "^([a-z]+[a-z0-9]{2,19})$";
    // 2-4 numbers, - is optional, 5-8 numbers
    public static final String PHONE_REGEXP_PATTERN = "^(([0-9]){2,4}-?([0-9]){5,8})$";
    //
    public static final String REGNUMBER_PATTERN = "^([A-Za-z]){3}([0-9]){2}([A-Za-z0-9])$";

}
