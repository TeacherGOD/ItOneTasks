package ru.itone.course_java.core.base_syntax;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class BaseSyntaxTest {

    BaseSyntax baseSyntax;


    @BeforeEach
    void setUp() {
        baseSyntax = new BaseSyntax();
    }

    @ParameterizedTest
    @MethodSource
    void and(boolean x, boolean y, boolean result) {
        assertThat(baseSyntax.and(x, y)).isEqualTo(result);
    }

    static Stream<Arguments> and() {
        return Stream.of(
                Arguments.of(true, true, true),
                Arguments.of(true, false, false),
                Arguments.of(false, true, false),
                Arguments.of(false, false, false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void or(boolean x, boolean y, boolean result) {
        assertThat(baseSyntax.or(x, y)).isEqualTo(result);
    }

    static Stream<Arguments> or() {
        return Stream.of(
                Arguments.of(true, true, true),
                Arguments.of(true, false, true),
                Arguments.of(false, true, true),
                Arguments.of(false, false, false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void xor(boolean x, boolean y, boolean result) {
        assertThat(baseSyntax.xor(x, y)).isEqualTo(result);
    }

    static Stream<Arguments> xor() {
        return Stream.of(
                Arguments.of(true, true, false),
                Arguments.of(true, false, true),
                Arguments.of(false, true, true),
                Arguments.of(false, false, false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void sum_short_success(short x, short y, int result) {
        assertThat(baseSyntax.sum(x, y)).isEqualTo(result);
    }

    static Stream<Arguments> sum_short_success() {
        return Stream.of(
                Arguments.of((short) 0, (short) 0, 0),
                Arguments.of((short) 1, (short) 2, 3),
                Arguments.of((short) -10, (short) 10, 0),
                Arguments.of(Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE * 2),
                Arguments.of(Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE * 2),
                Arguments.of(Short.MIN_VALUE, Short.MAX_VALUE, -1)
        );
    }

    @ParameterizedTest
    @MethodSource
    void sum_byte_success(byte x, byte y, byte result) {
        assertThat(baseSyntax.sum(x, y)).isEqualTo(result);
    }

    static Stream<Arguments> sum_byte_success() {
        return Stream.of(
                Arguments.of((byte) 0, (byte) 0, (byte) 0),
                Arguments.of((byte) 1, (byte) 2, (byte) 3),
                Arguments.of((byte) -10, (byte) 10, (byte) 0),
                Arguments.of(Byte.MAX_VALUE, (byte) 0, Byte.MAX_VALUE),
                Arguments.of(Byte.MIN_VALUE, (byte) 0, Byte.MIN_VALUE),
                Arguments.of(Byte.MIN_VALUE, Byte.MAX_VALUE, (byte) -1),
                Arguments.of(((byte) (Byte.MAX_VALUE / 2 + 1)), ((byte) (Byte.MAX_VALUE / 2)), Byte.MAX_VALUE),
                Arguments.of(((byte) (Byte.MIN_VALUE / 2)), ((byte) (Byte.MIN_VALUE / 2)), Byte.MIN_VALUE)
        );
    }

    @ParameterizedTest
    @MethodSource
    void sum_byte_error(byte x, byte y) {
        assertThatExceptionOfType(Throwable.class)
                .isThrownBy(() -> baseSyntax.sum(x, y));
    }

    static Stream<Arguments> sum_byte_error() {
        return Stream.of(
                Arguments.of(Byte.MAX_VALUE, Byte.MAX_VALUE),
                Arguments.of(Byte.MAX_VALUE, (byte) 1),
                Arguments.of(Byte.MIN_VALUE, (byte) -1),
                Arguments.of(((byte) (Byte.MAX_VALUE / 2 + 1)), ((byte) (Byte.MAX_VALUE / 2 + 1))),
                Arguments.of(((byte) (Byte.MIN_VALUE / 2 - 1)), ((byte) (Byte.MIN_VALUE / 2)), Byte.MIN_VALUE)
        );
    }

    @ParameterizedTest
    @MethodSource
    void mult_short_success(short x, short y, int result) {
        assertThat(baseSyntax.mult(x, y)).isEqualTo(result);
    }

    static Stream<Arguments> mult_short_success() {
        return Stream.of(
                Arguments.of(Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE * Short.MAX_VALUE),
                Arguments.of(Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE * Short.MIN_VALUE),
                Arguments.of(Short.MIN_VALUE, (short) 0, 0),
                Arguments.of(Short.MAX_VALUE, (short) 0, 0),
                Arguments.of((short) 5, (short) -5, -25),
                Arguments.of((short) -5, (short) -5, 25)
        );
    }

    @ParameterizedTest
    @MethodSource
    void mult_int_success(int x, int y, long result) {
        assertThat(baseSyntax.mult(x, y)).isEqualTo(result);
    }

    static Stream<Arguments> mult_int_success() {
        return Stream.of(
                Arguments.of(10, 0, 0),
                Arguments.of(10, 1, 10),
                Arguments.of(-1, 1, -1),
                Arguments.of(-1, -1, 1),
                Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE, (long) Integer.MAX_VALUE * Integer.MAX_VALUE),
                Arguments.of(Integer.MIN_VALUE, Integer.MIN_VALUE, (long) Integer.MIN_VALUE * Integer.MIN_VALUE)
        );
    }

    @ParameterizedTest
    @MethodSource
    void div_int_success(int x, int y, int result) {
        assertThat(baseSyntax.div(x, y)).isEqualTo(result);
    }

    static Stream<Arguments> div_int_success() {
        return Stream.of(
                Arguments.of(0, 10, 0),
                Arguments.of(10, 1, 10),
                Arguments.of(-1, 1, -1),
                Arguments.of(-1, -1, 1),
                Arguments.of(10, 2, 5),
                Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE, 1),
                Arguments.of(Integer.MIN_VALUE, Integer.MIN_VALUE, 1)
        );
    }

    @Test
    void div_int_error() {
        assertThatExceptionOfType(Throwable.class)
                .isThrownBy(() -> baseSyntax.div(1, 0));
    }

    @ParameterizedTest
    @MethodSource
    void sameByMod(float x, float y, boolean result) {
        assertThat(baseSyntax.sameByMod(x, y)).isEqualTo(result);
    }

    static Stream<Arguments> sameByMod() {
        return Stream.of(
                Arguments.of(0.000_01f, 0.000_1f, true),
                Arguments.of(100f, 100f, true),
                Arguments.of(0f, 0f, true),
                Arguments.of(-1f, -1f, true),
                Arguments.of(-1f, 1f, true),
                Arguments.of(-0.000_1f, -0.000_099_999f, true),
                Arguments.of(Float.MAX_VALUE, Float.MAX_VALUE, true),
                Arguments.of(Float.MIN_VALUE, Float.MIN_VALUE, true),
                Arguments.of(Float.MAX_VALUE, -Float.MAX_VALUE, true),
                Arguments.of(-0.000_1f, -0.001f, false),
                Arguments.of(0.000_1f, 0.001f, false),
                Arguments.of(-0.001f, 0.000_1f, false),
                Arguments.of(1f, -2f, false),
                Arguments.of(Float.MAX_VALUE, Float.MIN_VALUE, false)
        );
    }

    @Test
    void sumCharValues() {
        assertThat(baseSyntax.sumCharValues("I study Java".toCharArray())).isEqualTo("I study Java".chars().sum());
    }

    @ParameterizedTest
    @MethodSource
    void factorial_success(int x, double result) {
        assertThat(baseSyntax.factorial(x)).isEqualTo(result);
    }

    static Stream<Arguments> factorial_success() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(1, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 6),
                Arguments.of(10, 3_628_800)
        );
    }

    @Test
    void factorial_error() {
        assertThatExceptionOfType(Throwable.class)
                .isThrownBy(() -> baseSyntax.factorial(-1));
    }
}
