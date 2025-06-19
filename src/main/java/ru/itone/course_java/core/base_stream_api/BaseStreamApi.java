package ru.itone.course_java.core.base_stream_api;

import ru.itone.course_java.core.base_stream_api.model.Chest;
import ru.itone.course_java.core.base_stream_api.model.ChestKey;
import ru.itone.course_java.core.base_stream_api.model.Loot;
import ru.itone.course_java.core.base_stream_api.model.LootType;

import java.util.*;
import java.util.stream.Collectors;

public class BaseStreamApi {

    /**
     * Откройте все сундуки, подобрав ключи к их замкам
     */
    public List<Chest> openAllChests(ChestDataSource source) {
        List<ChestKey> keys = new ArrayList<>(source.getKeys());
        source.getChests().forEach(chest ->
                keys.stream()
                        .filter(key -> chest.getChestLock().checkKey(key))
                        .findFirst()
                        .ifPresent(key -> chest.getChestLock().unlock(key))
        );
        return source.getChests();
    }

    /**
     * Откройте все сундуки, но ключей в этот раз не хватает, верните только те сундуки, что получилось открыть
     */
    public List<Chest> openAllChestsNotEnoughKey(ChestDataSource source) {
        List<ChestKey> keys = new ArrayList<>(source.getKeys());
        source.getChests().forEach(chest ->
                keys.stream()
                        .filter(key -> chest.getChestLock().checkKey(key))
                        .findFirst()
                        .ifPresent(key -> chest.getChestLock().unlock(key))
        );
        return source.getChests().stream().filter(chest -> !chest.getChestLock().isLocked()).toList();
    }

    /**
     * Откройте все сундуки и верните все предметы, что в них лежат
     */
    public List<Loot> getEachChestContent(ChestDataSource source) {
        openAllChests(source);
        return source.getChests().stream().flatMap(
                chest -> chest.getContent().stream()
        ).toList();
    }

    /**
     * Откройте все сундуки, вытащите все предметы и отсортируйте их по типам {@link LootType}
     */
    public Map<LootType, List<Loot>> sortAllLoot(ChestDataSource source) {
        return getEachChestContent(source).stream()
                .collect(Collectors.groupingBy(Loot::getType));
    }

    /**
     * Откройте все сундуки и верните общей вес {@link Loot#getWeight()} всех полученных предметов
     */
    public int entireLootWeight(ChestDataSource source) {
        return getEachChestContent(source).stream()
                .mapToInt(Loot::getWeight).sum();
    }

    /**
     * Откройте все сундуки и верните меч {@link Loot#getName()}.contains("Sword") с минимальным весом {@link Loot#getWeight()} из всех полученных предметов
     */
    public Optional<Loot> lightestSword(ChestDataSource source) {
        return getEachChestContent(source).stream()
                .filter(item-> item.getName().contains("Sword"))
                .min(Comparator.comparingInt(Loot::getWeight));
    }

    /**
     * Откройте все сундуки и найдите все уникальные наименования предметов, которые в нём лежат,
     * отсортируйте наименования в порядке возрастания, используя {@link String#compareTo(String)}
     * верните их в виде единой строки с разделителем в виде запятой и пробела.
     * Пример результата: "All-on-one Sword 1, Bad Potion 2, Citrus Stew"
     */
    public String allUniqueNames(ChestDataSource source) {
        return getEachChestContent(source).stream()
                .map(Loot::getName)
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));
    }

    /**
     * Откройте все сундуки, найдите всю еду и зелья, отфильтруйте яды ("Poison") от хороших зелий ("Flask"),
     * верните {@link Map}, где ключ - это название предмета, а значение - его количество
     */
    public Map<String, Integer> goodPotionsAndFoodStatistics(ChestDataSource source) {
        return getEachChestContent(source).stream()
                .filter(item->
                        item.getType()==LootType.FOOD||
                        item.getType()==LootType.POTION &&item.getName().contains("Flask")
                )
                .collect(Collectors.toMap(
                        Loot::getName,
                        item->1,
                        Integer::sum
                ));
    }

    /**
     * Откройте все сундуки, отбрасывайте сундуки до тех пор, пока количество предметов в сундуке не будет 80 или больше,
     * после чего верните все предметы в этом и последующих 10 сундуках
     */
    public List<Loot> lootSpecificChests(ChestDataSource source) {
        openAllChests(source);
        return source.getChests().stream()
                .dropWhile(chest -> chest.getContent().size()<80)
                .limit(10).flatMap(chest -> chest.getContent().stream())
                .toList();
    }

    /**
     * Открывайте сундуки, пока не найдёте указанный предмет item.
     * Верните true в случае успеха,
     * false в случае если такого предмета нет
     */
    public boolean searchForItem(ChestDataSource source, Loot item) {
        return source.getChests().stream()
                .anyMatch(chest -> {
                    if (chest.getChestLock().isLocked()) {
                        source.getKeys().stream()
                                .filter(key -> chest.getChestLock().checkKey(key))
                                .findFirst()
                                .ifPresent(key -> chest.getChestLock().unlock(key));
                    }
                    return !chest.getChestLock().isLocked() && chest.getContent().contains(item);
                });
    }
}
