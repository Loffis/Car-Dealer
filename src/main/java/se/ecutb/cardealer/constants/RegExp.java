package se.ecutb.cardealer.constants;

public class RegExp {

    // 3-20 characters, only a-z and 0-9. First character must be a-z.
    public static final String USERNAME_REGEXP_PATTERN = "^([a-z]+[a-z0-9]{2,19})$";
    // 2-4 numbers, - is optional, 5-8 numbers
    public static final String PHONE_REGEXP_PATTERN = "^(([0-9]){2,4}-?([0-9]){5,8})$";
    //
    public static final String REGNUMBER_PATTERN = "^([A-Za-z]){3}([0-9]){2}([A-Za-z0-9])$";

}
