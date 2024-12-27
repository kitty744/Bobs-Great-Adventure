package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Yellow_Sword extends Entity {

    public static final String objName = "Yellow Sword";

    public OBJ_Yellow_Sword(GamePanel gp) {

        super(gp);

        // INFO
        name = objName;
        type = type_Yellow_Sword;
        description = "[" + name + "]\nThe final sword.";
        down1 = setup("objects/YellowSword", gp.tileSize, gp.tileSize);
        price = 500;

        // WEAPONS ATTACK
        attackValue = 50;
        attackArea.width = 36;
        attackArea.height = 36;
        knockBackPower = 2;
        motion1_duration = 5;
        motion2_duration = 10;

    }
}
