package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Fireball extends Projectile {

    GamePanel gp;
    public static final String objName = "FireBall";

    public OBJ_Fireball(GamePanel gp) {

        super(gp);
        this.gp = gp;

        name = objName;
        speed = 5;

        maxLife = 80;
        life = maxLife;

        knockBackPower = 0;

        attack = 1;
        useCost = 1;

        alive = false;
        getImage();

    }

    public void getImage() {

        up1 = setup("projectile/fireball_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("projectile/fireball_up_2", gp.tileSize, gp.tileSize);

        left1 = setup("projectile/fireball_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("projectile/fireball_left_2", gp.tileSize, gp.tileSize);

        down1 = setup("projectile/fireball_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("projectile/fireball_down_2", gp.tileSize, gp.tileSize);

        right1 = setup("projectile/fireball_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("projectile/fireball_right_2", gp.tileSize, gp.tileSize);

    }

    public boolean haveResource(Entity user) {return user.mana >= useCost;}
    public void subtractResource(Entity user) {user.mana -= useCost;}

    public Color getParticleColor() {
        return new Color(240, 50, 0);
    }
    public int getParticleSize() {
        return 10;
    }
    public int getParticleSpeed() {
        return 1;
    }
    public int getParticleMaxLife() {
        return 20;
    }

}
