package ru.itone.course_java.core.basic_collections.brew;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WineTest {


    private final static LocalDate COLLECTED_DATE = LocalDate.now().minusYears(2);
    private final static LocalDate PRODUCED_DATE = LocalDate.now().minusYears(2).plusMonths(6);

    private static Wine baseWine() {
        return new Wine("Saperavi", PRODUCED_DATE, 750, 12.5f,
                new Grape("Saperavi", 25.5f, 2.5f, COLLECTED_DATE));
    }

    @Test
    void wine_EqualsNull() {
        var wine = baseWine();

        assertThat(wine.equals(null)).isFalse();
    }

    @Test
    void wine_EqualsItself() {
        var wine = baseWine();

        assertThat(wine.equals(wine)).isTrue();
    }

    @ParameterizedTest
    @MethodSource
    void wine_NotEquals(Wine another) {
        assertThat(baseWine().equals(another)).isFalse();
    }

    static Stream<Arguments> wine_NotEquals() {
        var wineName = baseWine();
        wineName.setName("Merlo");

        var wineProduced = baseWine();
        wineProduced.setProduced(PRODUCED_DATE.plusDays(1));

        var wineAbv = baseWine();
        wineAbv.setAbv(12.4f);

        var grapeSort = baseWine();
        grapeSort.getGrapeSource().setSort("Merlo");

        var brixUp = baseWine();
        brixUp.getGrapeSource().setBrix(27.501f);
        var brixDown = baseWine();
        brixDown.getGrapeSource().setBrix(23.4f);

        var acidUp = baseWine();
        acidUp.getGrapeSource().setAcidity(2.601f);
        var acidDown = baseWine();
        acidDown.getGrapeSource().setAcidity(2.399f);

        var future = baseWine();
        future.getGrapeSource().setCollected(COLLECTED_DATE.plusYears(1).plusDays(1));
        var past = baseWine();
        past.getGrapeSource().setCollected(COLLECTED_DATE.minusYears(1).minusDays(1));

        return Stream.of(
                Arguments.of(wineName),
                Arguments.of(wineProduced),
                Arguments.of(wineAbv),
                Arguments.of(grapeSort),
                Arguments.of(brixUp),
                Arguments.of(brixDown),
                Arguments.of(acidUp),
                Arguments.of(acidDown),
                Arguments.of(future),
                Arguments.of(past)
        );
    }

    @ParameterizedTest
    @MethodSource
    void wine_Equals(Wine another) {
        assertThat(baseWine().equals(another)).isTrue();
    }

    static Stream<Arguments> wine_Equals() {
        var changeVol = baseWine();
        changeVol.setVol(1150);

        var brixUp = baseWine();
        brixUp.getGrapeSource().setBrix(27.5f);
        var brixDown = baseWine();
        brixDown.getGrapeSource().setBrix(23.5f);
        var brixSmall = baseWine();
        brixSmall.getGrapeSource().setBrix(25.0f);

        var acidUp = baseWine();
        acidUp.getGrapeSource().setAcidity(2.6f);
        var acidDown = baseWine();
        acidDown.getGrapeSource().setAcidity(2.4f);

        var future = baseWine();
        future.getGrapeSource().setCollected(COLLECTED_DATE.plusYears(1));
        var past = baseWine();
        past.getGrapeSource().setCollected(COLLECTED_DATE.minusYears(1));

        return Stream.of(
                Arguments.of(changeVol),
                Arguments.of(brixUp),
                Arguments.of(brixDown),
                Arguments.of(brixSmall),
                Arguments.of(acidUp),
                Arguments.of(acidDown),
                Arguments.of(future),
                Arguments.of(past)
        );
    }
}