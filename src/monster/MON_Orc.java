package monster;

import entity.Entity;
import main.GamePanel;
import object.*;
import java.util.Random;

public class MON_Orc extends Entity {

    GamePanel gp;

    public MON_Orc(GamePanel gp) {

        super(gp);
        this.gp = gp;

        // INFO ON MONSTER

        type = type_monster;
        name = "Orc";

        defaultSpeed = 1;

        exp = 10;

        // Orc STATS

        speed = defaultSpeed;

        maxLife = 10;
        life = maxLife;

        attack = 8;
        defense = 2;

        knockBackPower = 5;


        // Orc HITBOX

        solidArea.x = 4;
        solidArea.y = 4;

        solidArea.width = 40;
        solidArea.height = 44;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 48;
        attackArea.height = 48;

        motion1_duration = 40;
        motion2_duration = 85;

        getImage();
        getAttackImage();

    }

    public void getImage() {

        up1 = setup("monster/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/orc_up_2", gp.tileSize, gp.tileSize);

        left1 = setup("monster/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/orc_left_2", gp.tileSize, gp.tileSize);


        down1 = setup("monster/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/orc_down_2", gp.tileSize, gp.tileSize);


        right1 = setup("monster/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/orc_right_2", gp.tileSize, gp.tileSize);


    }

    public void getAttackImage() {

        attackUp1 = setup("monster/orc_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("monster/orc_attack_up_2", gp.tileSize, gp.tileSize * 2);

        attackLeft1 = setup("monster/orc_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("monster/orc_attack_left_2", gp.tileSize * 2, gp.tileSize);

        attackDown1 = setup("monster/orc_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("monster/orc_attack_down_2", gp.tileSize, gp.tileSize * 2);

        attackRight1 = setup("monster/orc_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("monster/orc_attack_right_2", gp.tileSize * 2, gp.tileSize);

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

        if(!attacking) {
            checkAttackOrNot(30, gp.tileSize * 4, gp.tileSize);
        }


    }

    public void damageReaction() {

        actionLockCounter = 0;
        onPath = true;

    }

    public void checkDrop() {

        int i = new Random().nextInt(200) + 1;

        if(i <  50) {
            dropItem(new OBJ_Coin_Bronze(gp));
        }

        if(i >=  50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        }

        if(i >=  75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }

        if(i >= 100 && i < 150) {
            dropItem(new OBJ_Potion_Red(gp));
        }

    }

}
