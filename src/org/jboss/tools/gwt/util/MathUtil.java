package org.jboss.tools.gwt.util;

import java.text.DecimalFormat;

/**
 * Created by karthikmarupeddi on 8/13/14.
 */
public class MathUtil {

    public static Double formatDouble(Double valueToFormat) {
        long rounded = Math.round(valueToFormat*100);
        return rounded/100.0;
    }
}
