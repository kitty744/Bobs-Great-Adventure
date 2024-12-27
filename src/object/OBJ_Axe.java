package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

    public static final String objName = "Woodcutter's Axe";

    public OBJ_Axe(GamePanel gp) {

        super(gp);
        name = objName;

        down1 = setup("objects/axe", gp.tileSize, gp.tileSize);
        type = type_axe;

        attackValue = 2;

        attackArea.width = 30;
        attackArea.height = 30;

        description = "[Woodcutter's Axe]\nA bit rusty but still \ncan cut some trees.";
        price = 75;

        knockBackPower = 10;

        motion1_duration = 5;
        motion2_duration = 25;

    }
}
