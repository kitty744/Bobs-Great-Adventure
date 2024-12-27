package main;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];

    FloatControl fc;

    int volumeScale = 3;
    float volume;

    public Sound() {

        soundURL[0] = getClass().getClassLoader().getResource("sound/BlueBoyAdventure.wav");

        soundURL[1] = getClass().getClassLoader().getResource("sound/coin.wav");
        soundURL[2] = getClass().getClassLoader().getResource("sound/powerup.wav");

        soundURL[3] = getClass().getClassLoader().getResource("sound/unlock.wav");
        soundURL[4] = getClass().getClassLoader().getResource("sound/fanfare.wav");

        soundURL[5] = getClass().getClassLoader().getResource("sound/hitmonster.wav");
        soundURL[6] = getClass().getClassLoader().getResource("sound/receivedamage.wav");

        soundURL[7] = getClass().getClassLoader().getResource("sound/swingweapon.wav");
        soundURL[8] = getClass().getClassLoader().getResource("sound/levelup.wav");

        soundURL[9] = getClass().getClassLoader().getResource("sound/cursor.wav");
        soundURL[10] = getClass().getClassLoader().getResource("sound/burning.wav");

        soundURL[11] = getClass().getClassLoader().getResource("sound/cuttree.wav");
        soundURL[12] = getClass().getClassLoader().getResource("sound/gameover.wav");

        soundURL[13] = getClass().getClassLoader().getResource("sound/stairs.wav");
        soundURL[14] = getClass().getClassLoader().getResource("sound/sleep.wav");

        soundURL[15] = getClass().getClassLoader().getResource("sound/blocked.wav");
        soundURL[16] = getClass().getClassLoader().getResource("sound/parry.wav");

        soundURL[17] = getClass().getClassLoader().getResource("sound/speak.wav");

        soundURL[18] = getClass().getClassLoader().getResource("sound/Merchant.wav");
        soundURL[19] = getClass().getClassLoader().getResource("sound/Dungeon.wav");

        soundURL[20] = getClass().getClassLoader().getResource("sound/chipwall.wav");
        soundURL[21] = getClass().getClassLoader().getResource("sound/dooropen.wav");

        soundURL[22] = getClass().getClassLoader().getResource("sound/FinalBattle.wav");


    }

    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();

            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            checkVolume();

        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void checkVolume() {

        switch(volumeScale) {

            case 0: volume = -80f; break;
            case 1: volume = -20f; break;

            case 2: volume = -12f; break;
            case 3: volume = -5f; break;

            case 4: volume = 1f; break;
            case 5: volume = 6f; break;


        }
        fc.setValue(volume);
    }
}
