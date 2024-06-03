package main;

import java.io.*;

public class Config {
    GamePanel gamepanel;
    public Config(GamePanel gamePanel)
    {
        this.gamepanel = gamePanel;
    }

    public void saveConfig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config"));

            // Music volume
            bw.write(String.valueOf(gamepanel.sound.musicVolume));
            bw.newLine();

            // SE Volume
            bw.write(String.valueOf(gamepanel.sound.soundEffect));
            bw.newLine();

            bw.close();
        } catch(IOException e) {
            System.out.println("Error saving config");
        }
    }
    public void loadConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config"));

            String s = br.readLine();

            // Music volume
            if(s != null) {
                gamepanel.sound.volume = Integer.parseInt(s);
            } else gamepanel.sound.volume = 10;

            // SE Volume
            s = br.readLine();
            if(s != null){
                gamepanel.sound.soundEffect = Integer.parseInt(s);
            } else gamepanel.sound.volume = 10;


            br.close();

        } catch (IOException e) {
            System.out.println("Error loading config");
        }
    }
}
