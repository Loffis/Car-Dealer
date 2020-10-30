package se.ecutb.cardealer.constants;

public class RegExp {

    // 13-24 inches. Steps of 1 inch.
    public static final String RIM_SIZE_PATTERN = "^(1[3-9]|2[0-4])$";
    // 135-355 mm. Steps of 10 mm.
    public static final String TIRE_WIDTH_PATTERN = "^(1[3-9]5|2[0-9]5|3[0-5]5)$";
    // 30-80 %. Steps of 5.
    public static final String TIRE_PROFILE_PATTERN = "^([3-8][05])$";
    // B-Y
    public static final String TIRE_SPEED_RATING_PATTERN = "^([B-Y])$";

}
