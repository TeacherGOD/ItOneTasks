package ru.itone.algorithm.search;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TextSearch {

    /**
     * Текст, в котором нужной искать последовательность символов
     */
    private final String text;

    /**
     * Реализовать поиск указанной последовательности searchingText символов в строке text.
     * В качестве ответа вернуть количество найденных последовательностей символов.
     * Если последовательности не найдены, вернуть 0.
     * Если хотя бы один из входных параметров text и searchingText пустой или null, то вернуть -1.
     *
     * @param searchingText Последовательность символов для поиска
     * @return Количество найденных последовательностей символов
     */
    public int findText(String searchingText) {
        throw new UnsupportedOperationException();
    }
}
