package monster;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.*;

public class MON_SkeletonLord extends Entity {

    GamePanel gp;
    public static final String monName = "Skeleton Lord";

    public MON_SkeletonLord(GamePanel gp) {

        super(gp);
        this.gp = gp;

        // INFO ON BOSS

        type = type_monster;
        name = monName;

        boss = true;
        sleep = true;

        defaultSpeed = 1;
        exp = 50;

        // Boss STATS

        speed = defaultSpeed;

        maxLife = 500;
        life = maxLife;

        attack = 10;
        defense = 2;

        knockBackPower = 5;

        // Boss HITBOX

        int size = gp.tileSize * 5;

        solidArea.x = 48;
        solidArea.y = 48;

        solidArea.width = size - 48 * 2;
        solidArea.height = size - 48;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 170;
        attackArea.height = 170;

        motion1_duration = 25;
        motion2_duration = 50;

        getImage();
        getAttackImage();

        setDialogue();

    }

    public void getImage() {

        int i = 5;

        if(!inRage) {

            up1 = setup("monster/skeletonlord_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("monster/skeletonlord_up_2", gp.tileSize * i, gp.tileSize * i);

            left1 = setup("monster/skeletonlord_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("monster/skeletonlord_left_2", gp.tileSize * i, gp.tileSize * i);


            down1 = setup("monster/skeletonlord_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("monster/skeletonlord_down_2", gp.tileSize * i, gp.tileSize * i);


            right1 = setup("monster/skeletonlord_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("monster/skeletonLord_right_2", gp.tileSize * i, gp.tileSize * i);


        }

        if(inRage) {

            up1 = setup("monster/skeletonlord_phase2_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("monster/skeletonlord_phase2_up_2", gp.tileSize * i, gp.tileSize * i);

            left1 = setup("monster/skeletonlord_phase2_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("monster/skeletonlord_phase2_left_2", gp.tileSize * i, gp.tileSize * i);


            down1 = setup("monster/skeletonlord_phase2_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("monster/skeletonlord_phase2_down_2", gp.tileSize * i, gp.tileSize * i);


            right1 = setup("monster/skeletonlord_phase2_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("monster/skeletonLord_phase2_right_2", gp.tileSize * i, gp.tileSize * i);

        }

    }

    public void getAttackImage() {

        int i = 5;


        if(!inRage) {

            attackUp1 = setup("monster/skeletonlord_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackUp2 = setup("monster/skeletonlord_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);

            attackLeft1 = setup("monster/skeletonlord_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackLeft2 = setup("monster/skeletonlord_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);

            attackDown1 = setup("monster/skeletonlord_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown2 = setup("monster/skeletonlord_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);

            attackRight1 = setup("monster/skeletonlord_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight2 = setup("monster/skeletonlord_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);

        }

        if(inRage) {

            attackUp1 = setup("monster/skeletonlord_phase2_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackUp2 = setup("monster/skeletonlord_phase2_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);

            attackLeft1 = setup("monster/skeletonlord_phase2_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackLeft2 = setup("monster/skeletonlord_phase2_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);

            attackDown1 = setup("monster/skeletonlord_phase2_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown2 = setup("monster/skeletonlord_phase2_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);

            attackRight1 = setup("monster/skeletonlord_phase2_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight2 = setup("monster/skeletonlord_phase2_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);


        }

    }


    public void setAction() {

        if(!inRage && life < maxLife / 2) {

            inRage = true;

            getImage();
            getAttackImage();

            defaultSpeed++;
            speed = defaultSpeed;

            attack *= 2;

        }

        if(getTileDistance(gp.player) < 10) {
            moveTowardPlayer(60);
        }

        else {
            getRandomDirection(120);
        }

        if(!attacking) {
            checkAttackOrNot(60, gp.tileSize * 7, gp.tileSize * 5);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
    }

    public void checkDrop() {

        dropItem(new OBJ_Yellow_Sword(gp));

        gp.bossBattleOn = false;
        Progress.skeletonLordDefeated = true;

        gp.stopMusic();
        gp.playMusic(19);

        for(int i = 0; i < gp.obj[1].length; i++) {
            if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)) {
                gp.playSE(21);
                gp.obj[gp.currentMap][i] = null;
            }
        }
    }

    public void setDialogue() {

        dialogues[0][0] = "No one can steal my treasure!";
        dialogues[0][1] = "You will die here!";
        dialogues[0][2] = "WELCOME TO YOUR DOOM!";

    }

}
