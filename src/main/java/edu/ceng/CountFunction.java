package edu.ceng;

import java.util.function.ToIntFunction;
import java.util.regex.Pattern;

/**
 * Returns the number of words in a string
 */
class CountFunction implements ToIntFunction<String> {

    private static final Pattern pattern = Pattern.compile("\\s+");

    @Override
    public int applyAsInt(String value) {
        return pattern.split(value).length;
    }
}
