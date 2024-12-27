package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public static final String objName = "Normal Sword";

    public OBJ_Sword_Normal(GamePanel gp) {

        super(gp);
        name = objName;

        down1 = setup("objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;

        attackArea.width = 36;
        attackArea.height = 36;

        type = type_sword;
        description = "[" + name + "]\nAn old sword.";

        price = 20;
        knockBackPower = 2;

        motion1_duration = 5;
        motion2_duration = 25;

    }
}
