package ru.itone.course_java.core.base_stream_api;

import lombok.Getter;
import ru.itone.course_java.core.base_stream_api.model.*;

import java.lang.reflect.Field;
import java.util.*;

public class ChestDataSource {

    private static final List<String> ARMOR_NAMES = List.of("Duskveil Plate", "Stormforged Carapace", "Flask's Wraithbone Flask Husk", "Emberclaw Flask's Mantle", "Verdant Sentinel's Shell", "Obsidian Judge's Cuirass", "Frostfang Flask's Harness", "Celestial Arbiter's Aegis", "Bloodoath Brigandine", "Hollow King's Shroud", "Shadowmoon Brigandine", "Dragonfang Scale Mail", "Runebound Aegis", "Phoenix Ash Carapace", "Titanforged Bulwark", "Abyssal Tide Plate", "Spectral Warden's Guard", "Volcanic Demonskin", "Starborn Cuirass", "Doom Herald's Visage", "Ironthorn Hauberk", "Nightmare Chitin", "Sunflare Battlegear", "Voidwalker's Shroud", "Thunderlord's Embrace", "Golemheart Plate", "Witchwood Weave", "Frostforged Redoubt", "Lichbone Carapace", "Serpent Scale Aegis", "Oathkeeper's Panoply", "Molten Core Harness", "Stormcaller's Raiment", "Moonsteel Brigandine", "Doombringer's Cage", "Elderwood Barkmail", "Soulforged Bastion", "Tidehunter's Shell", "Inferno Clasp", "Celestial Chain", "Plague Doctor's Guard", "Wyrmhide Tassets", "Astral Warden's Plate", "Bloodiron Carapace", "Ghostforge Armor", "Rimebound Cuirass", "Sunspire Aegis", "Dreadlord's Visage", "Viper's Kiss Mail", "Oblivion Cuirass", "Stormbinder's Cage", "Feywild Weave", "Dwarven Anvilplate", "Necrotic Carapace", "Solaris Mantle", "Abyssal Scale", "Thunderpeak Bulwark", "Wraithweave Shroud", "Dragonbone Aegis", "Frostfire Plate", "Voidtouched Mail", "Titan's Grasp", "Moonwhisper Chain", "Hellforged Harness", "Stoneskin Carapace", "Tempest Raiment", "Bloodthorn Plate", "Starfall Brigandine", "Doomgale Visage", "Eldritch Husk", "Phoenix Down Guard", "Glacierborn Cuirass", "Shadowstalker's Shroud", "Runebeast Plate", "Sunforged Aegis", "Dreadmaw Carapace", "Stormborn Hauberk", "Witchbane Mail", "Obsidian Maw", "Celestial Ward", "Plaguebearer's Hide", "Frostwarden's Shell", "Emberhide Tassets", "Voidscale Armor", "Thunderhoof Plate", "Moonhowl Brigandine", "Doomspike Flask Carapace", "Soulflame Aegis", "Tidecaller's Guard", "Infernal Spikeshell", "Starborne Cuirass", "Bloodmoon Shroud", "Grimwarden's Plate", "Flask Wyvernscale Mail", "Flask's Rimefang Harness", "Sunspark Bulwark", "Dreadforge Armor", "Flask's Viperfang Carapace", "Oblivion's Grasp", "Stormcrest Raiment", "Feybark Weave", "Flask's Anvilmar Plate", "Necroshroud", "Solar Flare Aegis", "Abyssguard");
    private static final List<String> WEAPON_NAMES = List.of("Dawnbreaker Sword", "Doombringer Mace", "Frostbite Dagger", "Soulreaper Spear", "StormcallerFlask Bow", "Moonfang Flask Dagger", "Embercleave Sword", "Voidedge Spear", "Thunderstrike Mace", "Bloodthorn Dagger", "Shadowfang Dagger", "Sunflare Bow", "Dragonfang Sword", "Oathkeeper Sword", "Widowmaker Bow", "Stoneheart Mace", "Nightshade Dagger", "Lightbringer Sword", "Darkthorn Spear", "Skysunder Sword", "Hellfire Mace", "Ironshard Sword", "Vipersting Dagger", "Starfall Bow", "Gutripper Dagger", "Widow's Wail Sword", "Bonecruncher Mace", "Spellweaver Spear", "Frostmourne Sword", "Blackfang Dagger", "Stormbreaker Sword", "Warbringer Sword", "Silvershot Bow", "Deathwhisper Dagger", "Runeblade Sword", "Phoenixfire Bow", "Tidecaller Spear", "Gorehowl Mace", "Windslicer Spear", "Earthshaker Mace", "Soulrender Sword", "Duskfang Dagger", "Sunstriker Sword", "Moonveil Dagger", "Bloodletter Dagger", "Shadowstrike Bow", "Flamewrath Sword", "Voidspike Spear", "Thunderfang Mace", "Icebrand Sword", "Doomspike Spear", "Starfire Bow", "Dragonclaw Dagger", "Oathbinder Sword", "Widowkiss Bow", "Stonecleaver Mace", "Nightbane Dagger", "Lightfang Sword", "Darkblade Dagger", "Skyrend Sword", "Hellspike Spear", "Ironspike Mace", "Viperfang Dagger", "Starborn Bow", "Gutspiller Dagger", "Widow's Bite Dagger", "Boneshatter Mace", "Spellbinder Spear", "Frostfang Dagger", "Blackrazor Sword", "Stormfang Sword", "Warglaive Sword", "Silverslash Dagger", "Deathgrip Dagger", "Runebite Sword", "Phoenixclaw Dagger", "Tideshaper Spear", "Gorespike Spear", "Windslash Bow", "Earthrend Mace", "Soulslash Sword", "Duskthorn Dagger", "Sunfang Sword", "Moonwhisper Bow", "Bloodspike Spear", "Shadowspike Dagger", "Flamefang Sword", "Voidslash Sword", "Thunderspike Mace", "Icefang Dagger", "Doombite Sword", "Starshard Bow", "Dragonspike Spear", "Oathspike Sword", "Widowthorn Dagger", "Stonespike Mace", "Nightspike Dagger", "Lightspike Sword", "Darkspike Dagger", "Skyspike Spear", "Hellfang Dagger", "Ironfang Sword", "Viperslash Sword", "Starspike Spear", "Gutfang Dagger", "Sharp Flask Dagger");
    private static final List<String> POTION_NAMES = List.of("Sunfire Flask", "Venomtongue Poison", "Moonblessing Flask", "Widow's Kiss Poison", "Giantsblood Flask", "Nightshade Poison", "Starlight Flask", "Scorpion's Sting Poison", "Phoenix Tears Flask", "Blackrot Poison", "Dragonsoul Flask", "Viper's Fang Poison", "Eternal Vigor Flask", "Withering Doom Poison", "Celestial Grace Flask", "Plaguebearer Poison", "Titan's Might Flask", "Soulburn Poison", "Verdant Renewal Flask", "Shadowfang Poison");
    private static final List<String> FOOD_NAMES = List.of("Goblin Jerky", "Cave Mushroom Stew", "Iron Ration Biscuit", "Roasted Giant Beetle", "Mage’s Mutton Pie", "Dwarven Hardtack", "Elven Waybread", "Troll Sausage", "Cursed Candy", "Dungeon Licorice Root");

    private static Random random = new Random();
    private static LootType[] lootTypes = LootType.values();

    private Loot generateItem() {
        Loot loot = new Loot();
        loot.setType(lootTypes[random.nextInt(lootTypes.length)]);
        switch (loot.getType()) {
            case ARMOR -> {
                loot.setName(ARMOR_NAMES.get(random.nextInt(ARMOR_NAMES.size())));
                loot.setWeight(random.nextInt(1_000, 15_001));
            }
            case WEAPON -> {
                loot.setName(WEAPON_NAMES.get(random.nextInt(WEAPON_NAMES.size())));
                loot.setWeight(random.nextInt(500, 5_001));
            }
            case POTION -> {
                loot.setName(POTION_NAMES.get(random.nextInt(POTION_NAMES.size())));
                loot.setWeight(random.nextInt(100, 501));
            }
            case FOOD -> {
                loot.setName(FOOD_NAMES.get(random.nextInt(FOOD_NAMES.size())));
                loot.setWeight(random.nextInt(200, 1_001));
            }
            default -> throw new IllegalStateException("Unexpected value: " + loot.getType());
        }
        return loot;
    }

    private Map<ChestKey, Chest> generateChests() {
        var chests = new HashMap<ChestKey, Chest>();

        ChestKey chestKey;
        var i = 0;
        while (i < minLootAmount) {
            var bucket = random.nextInt(100_00);
            int amountOfLoot = 0;
            if (bucket < 50) {
                amountOfLoot = random.nextInt(100, 10_000);
            }
            if (bucket >= 50 && bucket < 10_00) {
                amountOfLoot = random.nextInt(50, 100);
            }
            if (bucket >= 10_00 && bucket < 35_00) {
                amountOfLoot = random.nextInt(10, 50);
            }
            if (bucket >= 35_00) {
                amountOfLoot = random.nextInt(1, 10);
            }
            chestKey = new ChestKey();
            var chestLock = new ChestLock(chestKey);
            var chestContent = new ArrayList<Loot>();
            for (int j = 0; j < amountOfLoot; j++) {
                chestContent.add(generateItem());
            }
            chests.put(chestKey, new Chest(chestContent, chestLock));
            i += amountOfLoot;
        }
        return chests;
    }

    @Getter
    private List<Chest> chests;
    @Getter
    private List<ChestKey> keys;
    @Getter
    private int minLootAmount = 1_000_000;

    public ChestDataSource() {
        var keyToChest = generateChests();
        keys = new ArrayList<>(keyToChest.keySet());
        chests = new ArrayList<>(keyToChest.values());
        Collections.shuffle(keys);
        Collections.shuffle(chests);
    }

    public void removeKeys(int count) {
        keys = new ArrayList<>(keys.stream()
                .limit(count)
                .toList());
        Collections.shuffle(keys);
    }

    public Loot getRandomItem() {
        Chest chest = chests.get(random.nextInt(chests.size()));
        try {
            Field field = chest.getClass().getDeclaredField("content");
            field.setAccessible(true);
            Object content = field.get(chest);
            var loot = (List<Loot>) content;
            return loot.get(random.nextInt(loot.size()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
