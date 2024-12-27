package data;

import java.io.Serializable;
import java.util.ArrayList;

public final class DataStorage implements Serializable {

    // Player stats.
    int level;
    int maxLife;

    int life;
    int maxMana;

    int strength;
    int dexterity;

    int exp;
    int nextLevelExp;

    int coin;
    int mana;

    // Player inventory.
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    int currentWeaponSlot;
    int currentShieldSlot;

    // Objects on map
    String[][] mapObjectNames;
    int[][] mapObjectWorldX;
    int[][] mapObjectWorldY;
    String[][] mapObjectLootNames;
    boolean[][] mapObjectOpened;

}
