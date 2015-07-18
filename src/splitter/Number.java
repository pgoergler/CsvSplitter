package splitter;

/**
 *
 * @author paul
 */
public class Number {

    public static Long castToLong(Object o) {
        return castToLong(o, null);
    }

    public static Long castToLong(Object o, Long defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        try {
            Integer test = (Integer) o;
            return test.longValue();
        } catch (ClassCastException ex0) {
        }

        try {
            Long test = (Long) o;
            return test;
        } catch (ClassCastException ex0) {
        }

        try {
            String test = (String) o;
            return Long.parseLong(test);
        } catch (ClassCastException ex0) {
        } catch (NumberFormatException ex0) {
        }

        return defaultValue;
    }

    public static Double castToDouble(Object o) {
        return castToDouble(o, null);
    }

    public static Double castToDouble(Object o, Double defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        if (o instanceof Double) {
            return (Double) o;
        }

        try {
            Integer test = (Integer) o;
            return test.doubleValue();
        } catch (ClassCastException ex0) {
        }

        try {
            Long test = (Long) o;
            return test.doubleValue();
        } catch (ClassCastException ex0) {
        }

        try {
            String test = (String) o;
            return Double.parseDouble(test);
        } catch (ClassCastException ex0) {
        } catch (NumberFormatException ex0) {
        }

        return defaultValue;
    }

    public static Integer castToInteger(Object o) {
        return castToInteger(o, null);
    }

    public static Integer castToInteger(Object o, Integer defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        try {
            Integer test = (Integer) o;
            return test;
        } catch (ClassCastException ex0) {
        }

        try {
            Long test = (Long) o;
            return test.intValue();
        } catch (ClassCastException ex0) {
        }

        try {
            String test = (String) o;
            return Integer.parseInt(test);
        } catch (ClassCastException ex0) {
        } catch (NumberFormatException ex0) {
        }

        return defaultValue;
    }

    public static Boolean castToBoolean(Object o) {
        return castToBoolean(o, null);
    }

    public static Boolean castToBoolean(Object o, Boolean defaultValue) {
        if (o == null) {
            return defaultValue;
        }
        try {
            Boolean test = (Boolean) o;
            return test;
        } catch (ClassCastException ex0) {
        }

        try {
            Integer test = castToInteger(o);
            if (test != null) {
                return test != 0;
            }
        } catch (ClassCastException ex0) {
        }

        try {
            Long test = castToLong(o);
            if (test != null) {
                return test != 0;
            }
        } catch (ClassCastException ex0) {
        }
        try {
            String test = (String) o;
            return test.toLowerCase().equals("yes") || test.toLowerCase().equals("true") || test.toLowerCase().equals("oui");
        } catch (ClassCastException ex0) {
        }

        return defaultValue;
    }
}
