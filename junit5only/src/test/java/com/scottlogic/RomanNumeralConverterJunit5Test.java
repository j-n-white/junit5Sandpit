package com.scottlogic;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RomanNumeralConverterJunit5Test {

    private static RomanNumeralConverter testee;

    @BeforeAll
    static void setUp() {
        testee = new RomanNumeralConverter();
    }

    @Test
    void oneReturnsI() {
        assertEquals("I", testee.convertToRomanNumerals(1));
    }

    @Test
    void twoReturnsII() {
        assertEquals("II", testee.convertToRomanNumerals(2));
    }

    @Test
    void threeReturnsIII() {
        assertEquals("III", testee.convertToRomanNumerals(3));
    }

    @Test
    void fourReturnsIV() {
        assertEquals("IV", testee.convertToRomanNumerals(4));
    }

    @Test
    void fiveReturnsV() {
        assertEquals("V", testee.convertToRomanNumerals(5));
    }

    @Test
    void sixReturnsVI() {
        assertEquals("VI", testee.convertToRomanNumerals(6));
    }

    @Test
    void sevenReturnsVII() {
        assertEquals("VII", testee.convertToRomanNumerals(7));
    }

    @Test
    void eightReturnsVII() {
        assertEquals("VIII", testee.convertToRomanNumerals(8));
    }

    @Test
    void nineReturnsIX() {
        assertEquals("IX", testee.convertToRomanNumerals(9));
    }

    @Test
    void tenReturnsX() {
        assertEquals("X", testee.convertToRomanNumerals(10));
    }

    @Test
    void fourtyReturnsXL() {
        assertEquals("XL", testee.convertToRomanNumerals(40));
    }

    @Test
    void fiftyReturnsL() {
        assertEquals("L", testee.convertToRomanNumerals(50));
    }

    @Test
    void ninetyReturnsXC() {
        assertEquals("XC", testee.convertToRomanNumerals(90));
    }

    @Test
    void oneHundredReturnsC() {
        assertEquals("C", testee.convertToRomanNumerals(100));
    }

    @Test
    void fourHundredReturnsCD() {
        assertEquals("CD", testee.convertToRomanNumerals(400));
    }

    @Test
    void fiveHundredReturnsD() {
        assertEquals("D", testee.convertToRomanNumerals(500));
    }

    @Test
    void nineHundredReturnsCM() {
        assertEquals("CM", testee.convertToRomanNumerals(900));
    }

    @Test
    void oneThousandReturnsM() {
        assertEquals("M", testee.convertToRomanNumerals(1000));
    }

    @Test
    void oneThousandNineHundredAndFifyFourReturnsMCMLIV() {
        assertEquals("MCMLIV", testee.convertToRomanNumerals(1954));
    }

    @Test
    void zeroReturnsNulla() {
        assertEquals("nulla", testee.convertToRomanNumerals(0));
    }

    @Test
    void negativeThrowsException() {
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, () -> testee.convertToRomanNumerals(-1));
        assertEquals("Negative numbers are not supported", actual.getMessage());
    }
}