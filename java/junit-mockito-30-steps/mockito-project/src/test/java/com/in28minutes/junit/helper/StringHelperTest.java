package com.in28minutes.junit.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class StringHelperTest {

    private StringHelper stringHelper;

    @BeforeEach
    void initialize() {
        stringHelper = new StringHelper();
    }

    // AACD => CD | ACD => CD | CDEF => CDEF | CDAA => CDAA

    @ParameterizedTest
    //@MethodSource("testTruncateAInFirst2PositionsSource")
    @CsvSource({
            "AACD, CD",
            "ACD, CD",
            "CDEF, CDEF",
            "CDAA, CDAA"}
            )
    //void testTruncateAInFirst2Positions(String[] testAndExpected) {
    void testTruncateAInFirst2Positions(String test, String expected) {
        Assertions.assertEquals(expected, stringHelper.truncateAInFirst2Positions(test));
    }
/*
    static Stream<Arguments> testTruncateAInFirst2PositionsSource() {
        return Stream.of(
                Arguments.of((Object) new String[]{"AACD", "CD"}),
                Arguments.of((Object) new String[]{"ACD", "CD"}),
                Arguments.of((Object) new String[]{"CDEF", "CDEF"}),
                Arguments.of((Object) new String[]{"CDAA", "CDAA"})
        );
    }*/


}
