import java.io.*;
import javax.sound.sampled.*;

/**
 * Audio - class for playing audio files
 *
 * @author Caleb Richardson
 */
public class Audio {
    //sets the parameters for the audio clip
    private Clip audioClip;
    private AudioInputStream audioIn;

    /**
     * Audio - constructor for an audio file
     *
     * @param filename - the name of the .wav file to be played
     */
    public Audio(String filename){
        try{
            //gets the audio input
            audioIn = AudioSystem.getAudioInputStream(new File(filename).getAbsoluteFile());
            //gets the sound clip resource
            audioClip = AudioSystem.getClip();
            //opens the clip
            audioClip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * play - plays the current audio clip
     */
    public void play() {
        audioClip.start();
    }

    /**
     * stop - stops the current audio clip
     */
    public void stop(){
        if (audioClip.isRunning()) {
            audioClip.stop();
        }
    }

    /**
     * isPlaying - checks to see if the current audio clip is playing
     *
     * @return - if it is return true, if not return false
     */
    public boolean isPlaying(){
        if(audioClip.isRunning()){
            return true;
        }
        return false;
    }

    /**
     * setVolume - sets the volume for the current audio clip between 0.0 and 1.0
     *
     * @param volume - the volume to play the clip at between 0.0 and 1.0
     */
    public void setVolume(double volume){
        FloatControl volumeControl = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
        //does the math to convert the decimal volume level to the actual float control value
        float volumeLevel = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
        volumeControl.setValue(volumeLevel);
    }
}