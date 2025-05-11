package ru.itone.course_java.core.base_stream_api;

import ru.itone.course_java.core.base_stream_api.model.Chest;
import ru.itone.course_java.core.base_stream_api.model.Loot;
import ru.itone.course_java.core.base_stream_api.model.LootType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BaseStreamApi {

    /**
     * Откройте все сундуки, подобрав ключи к их замкам
     */
    public List<Chest> openAllChests(ChestDataSource source) {
        throw new UnsupportedOperationException();
    }

    /**
     * Откройте все сундуки, но ключей в этот раз не хватает, верните только те сундуки, что получилось открыть
     */
    public List<Chest> openAllChestsNotEnoughKey(ChestDataSource source) {
        throw new UnsupportedOperationException();
    }

    /**
     * Откройте все сундуки и верните все предметы, что в них лежат
     */
    public List<Loot> getEachChestContent(ChestDataSource source) {
        throw new UnsupportedOperationException();
    }

    /**
     * Откройте все сундуки, вытащите все предметы и отсортируйте их по типам {@link LootType}
     */
    public Map<LootType, List<Loot>> sortAllLoot(ChestDataSource source) {
        throw new UnsupportedOperationException();
    }

    /**
     * Откройте все сундуки и верните общей вес {@link Loot#getWeight()} всех полученных предметов
     */
    public int entireLootWeight(ChestDataSource source) {
        throw new UnsupportedOperationException();
    }

    /**
     * Откройте все сундуки и верните меч {@link Loot#getName()}.contains("Sword") с минимальным весом {@link Loot#getWeight()} из всех полученных предметов
     */
    public Optional<Loot> lightestSword(ChestDataSource source) {
        throw new UnsupportedOperationException();
    }

    /**
     * Откройте все сундуки и найдите все уникальные наименования предметов, которые в нём лежат,
     * отсортируйте наименования в порядке возрастания, используя {@link String#compareTo(String)}
     * верните их в виде единой строки с разделителем в виде запятой и пробела.
     * Пример результата: "All-on-one Sword 1, Bad Potion 2, Citrus Stew"
     */
    public String allUniqueNames(ChestDataSource source) {
        throw new UnsupportedOperationException();
    }

    /**
     * Откройте все сундуки, найдите всю еду и зелья, отфильтруйте яды ("Poison") от хороших зелий ("Flask"),
     * верните {@link Map}, где ключ - это название предмета, а значение - его количество
     */
    public Map<String, Integer> goodPotionsAndFoodStatistics(ChestDataSource source) {
        throw new UnsupportedOperationException();
    }

    /**
     * Откройте все сундуки, отбрасывайте сундуки до тех пор, пока количество предметов в сундуке не будет 80 или больше,
     * после чего верните все предметы в этом и последующих 10 сундуках
     */
    public List<Loot> lootSpecificChests(ChestDataSource source) {
        throw new UnsupportedOperationException();
    }

    /**
     * Открывайте сундуки, пока не найдёте указанный предмет item.
     * Верните true в случае успеха,
     * false в случае если такого предмета нет
     */
    public boolean searchForItem(ChestDataSource source, Loot item) {
        throw new UnsupportedOperationException();
    }
}
