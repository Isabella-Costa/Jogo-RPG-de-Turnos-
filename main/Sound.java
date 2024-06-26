package main;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound( ) {
        
        soundURL[0] = getClass().getResource("/resources/music/musicaJogoRPG.mp3");
        soundURL[1] = getClass().getResource("/resources/music/musicaChaves.mp3");
        soundURL[2] = getClass().getResource("/resources/music/musicaRaios.mp3");
        soundURL[3] = getClass().getResource("/resources/music/musicaPorta.mp3");
    }

    public void setFile(int i){

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
           
        }

    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop(){
        clip.stop();
    }
    
}
