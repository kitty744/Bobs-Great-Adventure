package main;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import tile.Map;
import tile.TileManager;
import tile_interactive.InteractiveTile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    // Basic Variables
    final int scale = 3;

    final int originalTileSize = 16;
    public final int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;

    public int screenWidth = tileSize * maxScreenCol; // 960
    public int screenHeight = tileSize * maxScreenRow; // 576

    public int maxWorldCol;
    public int maxWorldRow;

    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;

    public boolean fullScreenOn = false;

    public final int maxMap = 10;
    public int currentMap = 0;

    public boolean bossBattleOn = false;

    // GAME SCREEN

    BufferedImage tempScreen;
    Graphics2D g2;

    // GAME THREAD

    int FPS = 60;
    Thread gameThread;

    // ADD CLASSES

    public KeyHandler keyH = new KeyHandler(this);
    SaveLoad saveLoad = new SaveLoad(this);

    public Player player = new Player(this, keyH);
    public TileManager tileM = new TileManager(this);

    Sound music = new Sound();
    Sound se = new Sound();

    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);

    public AssetSetter aSetter = new AssetSetter(this);
    public CollisionChecker cChecker = new CollisionChecker(this);

    Config config = new Config(this);
    public PathFinder pFinder = new PathFinder(this);

    public EnvironmentManager eManager = new EnvironmentManager(this);
    Map map = new Map(this);

    public EntityGenerator eGenerator = new EntityGenerator(this);
    public CutsceneManager csManager = new CutsceneManager(this);

    // ARRAYLISTS AND Arrays

    public Entity[][] obj = new Entity[maxMap][20];
    public Entity[][] npc = new Entity[maxMap][10];
    public Entity[][] monster = new Entity[maxMap][20];
    public InteractiveTile[][] iTile = new InteractiveTile[maxMap][50];

    public Entity[][] projectile = new Entity[maxMap][20];
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATES

    public int gameState;
    public final int titleState = 0;

    public final int playState = 1;
    public final int pauseState = 2;

    public final int dialogueState = 3;
    public final int characterState = 4;

    public final int optionsState = 5;
    public final int gameOverState = 6;

    public final int transitionState = 7;
    public final int tradeState = 8;

    public final int sleepState = 9;
    public final int mapState = 10;

    public int currentArea;
    public final int outside = 50;

    public final int indoor = 51;
    public final int dungeon = 52;

    public int nextArea;
    public final int cutsceneState = 11;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame() {

        aSetter.setObject();
        aSetter.setNPC();

        aSetter.setMonster();
        aSetter.setInteractiveTile();

        eManager.setup();

        gameState = titleState;
        currentArea = outside;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        if(fullScreenOn) {
            setFullScreen();
        }

    }

    public void resetGame(boolean restart) {

        currentArea = outside;

        removeTempEntity();

        bossBattleOn = false;
        player.setDefaultPositions();

        player.restoreStatus();
        player.resetCounter();

        aSetter.setNPC();
        aSetter.setMonster();

        if(restart) {

            player.setDefaultValues();
            aSetter.setObject();

            aSetter.setInteractiveTile();
            eManager.lighting.resetDay();

        }

    }

    public void setFullScreen() {

        // GET COMPUTERS MAX SCREEN SIZE

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        Main.window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        screenWidth2 = (int) width;
        screenHeight2 = (int) height;

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;

        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {

                update();

                drawToTempScreen();
                drawToScreen();

                delta--;
                drawCount++;

            }

            if(timer >= 1000000000) {

                System.out.println("FPS:" + drawCount);

                drawCount = 0;
                timer = 0;

            }

        }

    }

    public void update() {


        if(gameState == playState) {

            player.update();

            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }

            for (int i = 0; i < monster[1].length; i++) {

                if (monster[currentMap][i] != null) {
                    if(monster[currentMap][i].alive && !monster[currentMap][i].dying) {
                        monster[currentMap][i].update();
                    }

                    if (!monster[currentMap][i].alive) {

                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;

                    }
                }
            }

            for (int i = 0; i < projectile[1].length; i++) {

                if (projectile[currentMap][i] != null) {
                    if(projectile[currentMap][i].alive) {
                        projectile[currentMap][i].update();
                    }

                    if (!projectile[currentMap][i].alive) {
                        projectile[currentMap][i] = null;
                    }

                }
            }

            for(int i = 0; i < particleList.size(); i++) {

                if(particleList.get(i) != null) {

                    if(particleList.get(i).alive) {
                        particleList.get(i).update();
                    }

                    if(!particleList.get(i).alive) {
                        particleList.clear();
                    }

                }

            }

        }

        for(int i = 0; i < iTile[1].length; i++) {
            if(iTile[currentMap][i] != null) {
                iTile[currentMap][i].update();
            }
        }
        eManager.update();
    }

    public void drawToTempScreen() {

        g2.clearRect(0, 0, screenWidth, screenHeight);
        long drawStart = 0;

        if(keyH.showDebugText) {
            drawStart = System.nanoTime();
        }

        if(gameState == titleState) {
            ui.draw(g2);
        }

        else if (gameState == mapState) {
            map.drawFullMapScreen(g2);
        }

        else {

            tileM.draw(g2);

            for(int i = 0; i < iTile[1].length; i++){
                if(iTile[currentMap][i] != null) {
                    iTile[currentMap][i].draw(g2);
                }
            }

            entityList.add(player);

            for (int i = 0; i < npc[1].length; i++) {
                if(npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            for (int i = 0; i < obj[1].length; i++) {
                if(obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }

            for (int i = 0; i < monster[1].length; i++) {
                if(monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }


            for (int i = 0; i < projectile[1].length; i++) {
                 if(projectile[currentMap][i] != null) {
                    entityList.add(projectile[currentMap][i]);
                }
            }

            for (int i = 0; i < particleList.size(); i++) {
                if(particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
                }
            }

            entityList.sort((e1, e2) -> {
                int result = Integer.compare(e1.worldY, e2.worldY);
                return result;
            });

            for(int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            entityList.clear();

            eManager.draw(g2);
            map.drawMiniMap(g2);

            csManager.draw(g2);
            ui.draw(g2);

        }


        if(keyH.showDebugText) {

            long drawEnd = System.nanoTime();

            long passed = drawEnd - drawStart;
            g2.setFont(new Font("Arial", Font.PLAIN, 20));

            int x = 10;
            int y = 400;

            int lineHeight = 20;

            g2.drawString("WorldX" + player.worldX, x, y); y += lineHeight;
            g2.drawString("WorldY" + player.worldY, x, y); y += lineHeight;
            g2.drawString("Col" + (player.worldX + player.solidArea.x) / tileSize, x, y); y += lineHeight;
            g2.drawString("Row" + (player.worldY + player.solidArea.y) / tileSize, x, y); y += lineHeight;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: " + passed, x, y);
        }
    }

    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {music.stop();}

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }

    public void changeArea() {

        if(nextArea != currentArea) {

            stopMusic();

            if(nextArea == outside) {
                playMusic(0);
            }

            else if(nextArea == indoor) {
                playMusic(18);
            }

            else if(nextArea == dungeon) {
                playMusic(19);
            }

            aSetter.setNPC();

        }

        currentArea = nextArea;
        aSetter.setMonster();

    }

    public void removeTempEntity() {

        for(int mapNum = 0; mapNum < maxMap; mapNum++) {
            for(int i = 0; i < obj[1].length; i++) {
                if(obj[mapNum][i] != null && obj[mapNum][i].temp) {
                    obj[mapNum][i] = null;
                }
            }
        }
    }
}
