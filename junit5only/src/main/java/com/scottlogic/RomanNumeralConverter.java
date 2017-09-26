package com.scottlogic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides a method which converts and integer to String representing the same value in roman numerals.
 */
class RomanNumeralConverter {
    private static final Logger LOG = LoggerFactory.getLogger(RomanNumeralConverter.class);

    String convertToRomanNumerals(int input) {
        LOG.info("Converting " + input + " to roman numerals");
        if (input < 0) {
            throw new IllegalArgumentException("Negative numbers are not supported");
        } else if (input == 0) {
            return "nulla";
        }
        int unconvertedValue = input;
        StringBuilder output = new StringBuilder();
        unconvertedValue = substituteNumeral(unconvertedValue, output, "M", 1000);
        unconvertedValue = substituteNumeral(unconvertedValue, output, "CM", 900);
        unconvertedValue = substituteNumeral(unconvertedValue, output, "D", 500);
        unconvertedValue = substituteNumeral(unconvertedValue, output, "CD", 400);
        unconvertedValue = substituteNumeral(unconvertedValue, output, "C", 100);
        unconvertedValue = substituteNumeral(unconvertedValue, output, "XC", 90);
        unconvertedValue = substituteNumeral(unconvertedValue, output, "L", 50);
        unconvertedValue = substituteNumeral(unconvertedValue, output, "XL", 40);
        unconvertedValue = substituteNumeral(unconvertedValue, output, "X", 10);
        unconvertedValue = substituteNumeral(unconvertedValue, output, "IX", 9);
        unconvertedValue = substituteNumeral(unconvertedValue, output, "V", 5);
        unconvertedValue = substituteNumeral(unconvertedValue, output, "IV", 4);
        substituteNumeral(unconvertedValue, output, "I", 1);
        return output.toString();
    }

    private int substituteNumeral(int input, StringBuilder output, String stringValue, int numericValue) {
        int threshold = numericValue - 1;
        int unconvertedValue = input;
        while (unconvertedValue > threshold) {
            output.append(stringValue);
            unconvertedValue = unconvertedValue - numericValue;
            if (LOG.isTraceEnabled()) {
                LOG.trace("Converted string: " + output.toString() + " , remaining unconverted value: " + unconvertedValue);
            }
        }
        return unconvertedValue;
    }

}
