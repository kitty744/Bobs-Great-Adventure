package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import java.util.Random;

public class MON_Bat extends Entity {


    GamePanel gp;

    public MON_Bat(GamePanel gp) {

        super(gp);
        this.gp = gp;

        // INFO ON MONSTER

        type = type_monster;
        name = "Bat";

        defaultSpeed = 4;
        exp = 7;

        // Bat STATS

        speed = defaultSpeed;

        maxLife = 7;
        life = maxLife;

        attack = 7;
        defense = 0;


        // Bat HITBOX

        solidArea.x = 3;
        solidArea.y = 15;

        solidArea.width = 42;
        solidArea.height = 21;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();

    }

    public void getImage() {

        up1 = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/bat_down_2", gp.tileSize, gp.tileSize);

        left1 = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/bat_down_2", gp.tileSize, gp.tileSize);


        down1 = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/bat_down_2", gp.tileSize, gp.tileSize);


        right1 = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/bat_down_2", gp.tileSize, gp.tileSize);

    }


    public void setAction() {
        getRandomDirection(10);
    }

    public void damageReaction() {
        actionLockCounter = 0;
    }

    public void checkDrop() {

        int i = new Random().nextInt(100) + 1;

        if(i <  50) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }

        if(i >=  50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        }

        if(i >=  75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }

    }

}
