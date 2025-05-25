package ru.itone.algorithm.search;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

class TextSearchTest {

    Random random = new Random();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    String generate(int length) {
        var chars = new char[length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
        }
        return String.valueOf(chars);
    }

    @Test
    void emptyText() {
        assertThat(new TextSearch("").findText("a")).isEqualTo(-1);
    }

    @Test
    void nullText() {
        assertThat(new TextSearch(null).findText("a")).isEqualTo(-1);
    }

    @Test
    void nullSearch() {
        assertThat(new TextSearch("asd").findText(null)).isEqualTo(-1);
    }

    @Test
    void emptySearch() {
        assertThat(new TextSearch("asd").findText("")).isEqualTo(-1);
    }

    @Test
    void findText_Found() {
        var result = new TextSearch("asd asd asd asddd asd dsa dsa das dddddd").findText("ddd");
        assertThat(result).isEqualTo(3);
    }

    @Test
    void findText_NotFound() {
        var result = new TextSearch("asd asd asd asddd asd dsa dsa das dddddd").findText("x");
        assertThat(result).isEqualTo(0);
    }

    @Test
    void longText_Found() {
        var text = generate(100_000);
        var search = text.substring(random.nextInt(0, 50_000), random.nextInt(50_000, 100_000));
        assertThat(new TextSearch(text).findText(search))
                .isEqualTo((int) Pattern.compile(search).matcher(text).results().count());
    }
}