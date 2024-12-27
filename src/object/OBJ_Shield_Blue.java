package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Blue extends Entity {

    public static final String objName = "Blue Shield";

    public OBJ_Shield_Blue(GamePanel gp) {

        super(gp);
        name = objName;

        down1 = setup("objects/shield_blue", gp.tileSize, gp.tileSize);
        type = type_shield;

        defenseValue = 2;
        description = "[" + name + "]\nA shiny blue shield.";

        price = 250;

    }

}
