package ru.itone.course_java.core.base_stream_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.itone.course_java.core.base_stream_api.model.Chest;
import ru.itone.course_java.core.base_stream_api.model.Loot;
import ru.itone.course_java.core.base_stream_api.model.LootType;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

class BaseStreamApiTest {

    BaseStreamApi baseStreamApi = new BaseStreamApi();
    ChestDataSource source;

    @BeforeEach
    void setUp() {
        source = new ChestDataSource();
    }

    @Test
    void openAllChests() {
        var result = baseStreamApi.openAllChests(source);

        assertThat(result).allMatch(c -> !c.getChestLock().isLocked());
    }

    @Test
    void openAllChestsNotEnoughKey() {
        source.removeKeys(source.getMinLootAmount() / 2);

        var result = baseStreamApi.openAllChestsNotEnoughKey(source);

        assertThat(result).hasSize(source.getKeys().size());
        assertThat(result).allMatch(c -> !c.getChestLock().isLocked());
    }

    @Test
    void getEachChestContent() {
        var result = baseStreamApi.getEachChestContent(source);

        assertThat(result.size()).isGreaterThanOrEqualTo(source.getMinLootAmount());
        var loot = new ArrayList<Loot>();
        source.getChests().forEach(c -> {
            loot.addAll(c.getContent());
        });
        assertThat(result).isEqualTo(loot);
    }

    @Test
    void sortAllLoot() {
        var result = baseStreamApi.sortAllLoot(source);

        assertThat(result.keySet()).containsExactlyInAnyOrder(LootType.values());
        for (LootType value : LootType.values()) {
            assertThat(result.get(value)).allMatch(l -> l.getType() == value);
        }
    }

    @Test
    void entireLootWeight() {
        var result = baseStreamApi.entireLootWeight(source);

        AtomicInteger exp = new AtomicInteger();
        source.getChests().forEach(c -> {
            c.getContent().forEach(l -> exp.addAndGet(l.getWeight()));
        });
        assertThat(result).isEqualTo(exp.get());
    }

    @Test
    @Timeout(120)
    void lightestSword() {
        Optional<Loot> result = baseStreamApi.lightestSword(source);
        while (result.isEmpty()) {
            source = new ChestDataSource();
            result = baseStreamApi.lightestSword(source);
        }

        AtomicReference<Loot> exp = new AtomicReference<>(new Loot("test", LootType.WEAPON, Integer.MAX_VALUE));
        source.getChests().forEach(c -> {
            c.getContent().forEach(l -> {
                if (l.getName().contains("Sword") && l.getWeight() < exp.get().getWeight()) {
                    exp.set(l);
                }
            });
        });
        assertThat(result.get()).isEqualTo(exp.get());
    }

    @Test
    void allUniqueNames() {
        var result = baseStreamApi.allUniqueNames(source);

        Set<String> names = new HashSet<>();
        source.getChests().forEach(c -> {
            c.getContent().forEach(l -> {
                names.add(l.getName());
            });
        });
        System.out.println(result);
        assertThat(result).isEqualTo(String.join(", ", names.stream().sorted(String::compareTo).toList()));
    }

    @Test
    void goodPotionsAndFoodStatistics() {
        var result = baseStreamApi.goodPotionsAndFoodStatistics(source);

        Map<String, Integer> exp = new HashMap<>();
        source.getChests().forEach(c -> {
            c.getContent().forEach(l -> {
                if (l.getType() == LootType.POTION && l.getName().contains("Flask") || l.getType() == LootType.FOOD) {
                    var i = exp.get(l.getName());
                    if (i == null) {
                        exp.put(l.getName(), 1);
                    } else {
                        exp.put(l.getName(), ++i);
                    }
                }
            });
        });
        assertThat(result).containsOnlyKeys(exp.keySet().toArray(new String[0]));
        exp.forEach((name, count) -> {
            assertThat(result.get(name)).isEqualTo(count);
        });
    }

    @Test
    void lootSpecificChests() {
        var result = baseStreamApi.lootSpecificChests(source);

        List<Loot> exp = new ArrayList<>();
        AtomicBoolean can = new AtomicBoolean(false);
        AtomicInteger i = new AtomicInteger(0);
        source.getChests().forEach(c -> {
            if (!can.get() && c.getContent().size() >= 80) {
                can.set(true);
            }
            if (can.get() && i.getAndIncrement() < 10) {
                exp.addAll(c.getContent());
            }
        });
        assertThat(result).isEqualTo(exp);
    }

    @Test
    void searchForItem_ItemFromChests() {
        var item = source.getRandomItem();

        var result = baseStreamApi.searchForItem(source, item);

        assertThat(result).isTrue();
        Chest previous = null;
        for (Chest chest : source.getChests()) {
            if (chest.getChestLock().isLocked()) {
                break;
            } else {
                previous = chest;
            }
        }
        assertThat(previous.getContent()).contains(item);
    }

    @Test
    void searchForItem_ItemCopy() {
        var original = source.getRandomItem();
        var item = new Loot(original.getName(), original.getType(), original.getWeight());

        var result = baseStreamApi.searchForItem(source, item);

        assertThat(result).isTrue();
        Chest previous = null;
        for (Chest chest : source.getChests()) {
            if (chest.getChestLock().isLocked()) {
                break;
            } else {
                previous = chest;
            }
        }
        assertThat(previous.getContent()).contains(item);
    }

    @Test
    void searchForItem_NotFoundItem() {
        var item = new Loot("Random", LootType.WEAPON, 100);

        var result = baseStreamApi.searchForItem(source, item);

        assertThat(result).isFalse();
        assertThat(source.getChests()).allMatch(chest -> !chest.getChestLock().isLocked());
    }
}