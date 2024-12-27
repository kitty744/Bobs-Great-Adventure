package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Teleport_Orb extends Entity {

    GamePanel gp;
    public static final String objName = "Orb";

    public OBJ_Teleport_Orb(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = objName;

        down1 = setup("objects/orb", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nTeleports you back \nhome.";

        price = 25;
        stackable = true;

    }


    public boolean use(Entity entity) {

        gp.stopMusic();
        gp.playMusic(0);

        gp.player.setDefaultPositions();

        gp.currentArea = gp.outside;
        return true;

    }
}
