package com.scottlogic;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This test class shows an example of a parametrised test.
 */
class RomanNumeralConverterParamererisedTest {

    private static RomanNumeralConverter testee;

    @BeforeAll
    static void setUp() {
        testee = new RomanNumeralConverter();
    }

    @ParameterizedTest
    @MethodSource("romanNumeralsTestProvider")
    void returnsCorrectRomanNumeralValue(String expected, int input) {
        assertEquals(expected, testee.convertToRomanNumerals(input));
    }

    private static Stream<Arguments> romanNumeralsTestProvider() {
        return Stream.of(
                Arguments.of("I",      1),
                Arguments.of("II",     2),
                Arguments.of("III",    3),
                Arguments.of("IV",     4),
                Arguments.of("V",      5),
                Arguments.of("VI",     6),
                Arguments.of("VII",    7),
                Arguments.of("VIII",   8),
                Arguments.of("IX",     9),
                Arguments.of("X",      10),
                Arguments.of("XL",     40),
                Arguments.of("L",      50),
                Arguments.of("XC",     90),
                Arguments.of("C",      100),
                Arguments.of("CD",     400),
                Arguments.of("D",      500),
                Arguments.of("CM",     900),
                Arguments.of("M",      1000),
                Arguments.of("MCMLIV", 1954),
                Arguments.of("nulla",  0)
                );
    }

    @Test
    void negativeThrowsException() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> testee.convertToRomanNumerals(-1));
        assertEquals("Negative numbers are not supported", actual.getMessage());
    }
}