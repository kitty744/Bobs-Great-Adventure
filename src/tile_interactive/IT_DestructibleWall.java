package tile_interactive;

import entity.Entity;
import main.GamePanel;
import java.awt.Color;

public class IT_DestructibleWall extends InteractiveTile{

    GamePanel gp;

    public IT_DestructibleWall(GamePanel gp, int col, int row) {

        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("tiles_interactive/destructiblewall", gp.tileSize, gp.tileSize);
        destructible = true;

        life = 3;

    }

    @Override
    public boolean isCorrectItem(Entity entity) {
        return entity.currentWeapon.type == type_pickaxe;
    }

    @Override
    public void playSE() {
        gp.playSE(20);
    }

    @Override
    public InteractiveTile getDestroyedForm() {
        return super.getDestroyedForm();
    }

    @Override
    public Color getParticleColor() {
        return new Color(65, 65, 65);
    }

    @Override
    public int getParticleSize() {
        return 6;
    }

    @Override
    public int getParticleSpeed() {
        return 1;
    }

    @Override
    public int getParticleMaxLife() {
        return 20;
    }
}
