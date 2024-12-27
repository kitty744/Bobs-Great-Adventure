package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity {

    public static final String objName = "Pickaxe";

    public OBJ_Pickaxe(GamePanel gp) {

        super(gp);
        name = objName;

        down1 = setup("objects/pickaxe", gp.tileSize, gp.tileSize);
        type = type_pickaxe;

        attackValue = 2;

        attackArea.width = 30;
        attackArea.height = 30;

        description = "[Pickaxe]\nYou will dig it!.";
        price = 75;

        knockBackPower = 10;

        motion1_duration = 10;
        motion2_duration = 20;

    }
}
