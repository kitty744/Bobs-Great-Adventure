package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {

        super(gp);
        this.gp = gp;

        // INFO ON MONSTER

        type = type_monster;
        name = "Green Slime";

        defaultSpeed = 1;

        exp = 2;
        projectile = new OBJ_Rock(gp);

        // SLIME STATS

        speed = defaultSpeed;

        maxLife = 4;
        life = maxLife;

        attack = 5;
        defense = 0;

        // SLIME HITBOX

        solidArea.x = 3;
        solidArea.y = 18;

        solidArea.width = 42;
        solidArea.height = 30;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();

    }

    public void getImage() {

        up1 = setup("monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/greenslime_down_2", gp.tileSize, gp.tileSize);

        left1 = setup("monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/greenslime_down_2", gp.tileSize, gp.tileSize);


        down1 = setup("monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/greenslime_down_2", gp.tileSize, gp.tileSize);


        right1 = setup("monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/greenslime_down_2", gp.tileSize, gp.tileSize);


    }


    public void setAction() {

        if(onPath) {

            checkStopChasingOrNot(gp.player, 15, 100);
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

        }

        else {

            checkStartChasingOrNot(gp.player, 5, 100);
            getRandomDirection(120);

        }


    }

    public void damageReaction() {

        actionLockCounter = 0;
        direction = gp.player.direction;

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
