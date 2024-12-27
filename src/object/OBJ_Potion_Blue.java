package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Blue extends Entity {

    GamePanel gp;
    public static final String objName = "Blue Potion";

    public OBJ_Potion_Blue(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;

        down1 = setup("objects/potion_blue", gp.tileSize, gp.tileSize);

        value = 2;
        description = "[Blue Potion]\nRestores your mana \nby " + value + ".";

        price = 50;
        stackable = true;

        setDialogue();

    }

    public void setDialogue() {
        dialogues[0][0] = "You drink the " + name + "!\n" + "Your mana has been restored by " + value + ".";
    }

    public boolean use(Entity entity) {

        startDialogue(this, 0);

        entity.mana += value;
        gp.playSE(2);

        return true;

    }
}
