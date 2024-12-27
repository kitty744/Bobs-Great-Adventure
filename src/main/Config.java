package main;

import java.io.*;

public class Config {

    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {

        try {

            // Find the file to write data on.
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            // Writes on if fullscreen is turned on.
            if(gp.fullScreenOn) {
                bw.write("On");
            }

            // Writes off if fullscreen is turned off.
            if(!gp.fullScreenOn) {
                bw.write("Off");
            }

            // Create a new line.
            bw.newLine();

            // Write the musics volume level.
            bw.write(String.valueOf(gp.music.volumeScale));

            // Create a new line.
            bw.newLine();

            // Write the sound effects volume level.
            bw.write(String.valueOf(gp.se.volumeScale));

            // Write a new line just incase user adds another condition.
            bw.newLine();

            // Close the fileWriter after it stores the data.
            bw.close();

        } catch (IOException e) {
            System.out.println("File could not be found");
        }

    }

    public void loadConfig() {

        try {

            // Read the file data stored in the saveConfig method.
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            // Create a string that contains the value of anything written in the config.txt.
            String s = br.readLine();

            // Check if fullscreen is on.
            if(s.equals("On")) {
                gp.fullScreenOn = true;
            }

            // Check if fullscreen is off.
            if(s.equals("Off")) {
                gp.fullScreenOn = false;
            }

            // Set the volume to the data written in the file.
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);
            s = br.readLine();
            gp.se.volumeScale = Integer.parseInt(s);

            // Close The fileReader after all the data is collected.
            br.close();

        }

        catch (IOException i) {
            System.out.println("File could not be read");
        }
    }
}
