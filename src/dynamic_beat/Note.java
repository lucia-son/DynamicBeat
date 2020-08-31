package dynamic_beat;

import javax.swing.*;
import java.awt.*;

public class Note extends Thread {

    private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/note_basic.png")).getImage();
    private int x, y = 600 - 1000 / Main.SLEEP_TIME * Main.NOTE_SPEED;
    private String noteType;

    public Note(String noteType) {
        if(noteType.equals("S")) {
            x = 228;
        }
        else if(noteType.equals("D")) {
            x = 332;
        }
        else if(noteType.equals("F")) {
            x = 436;
        }
        else if(noteType.equals("Space")) {
            x = 540;
        }
        else if(noteType.equals("J")) {
            x = 744;
        }
        else if(noteType.equals("K")) {
            x = 848;
        }
        else if(noteType.equals("L")) {
            x = 952;
        }
        this.noteType=noteType;
    }

    public void screenDraw(Graphics2D g) {
        if(!noteType.equals("Space"))
        {
            g.drawImage(noteBasicImage, x, y, null);
        }
        else
        {
            g.drawImage(noteBasicImage, x, y, null);
            g.drawImage(noteBasicImage, x+100, y, null);
        }
    }

    public void drop(){
        y += Main.NOTE_SPEED;
    }

    @Override
    public void run() {  // Start Thread
        try {
            while (true) {
                drop(); //1초에 700픽셀만큼 아래로 떨어진다
                Thread.sleep(Main.SLEEP_TIME); //0.001초를 기준으로하는 sleep 즉,sleep(10) 이라는 것은 1초에 100번 실행
            }
        } catch(Exception e) {
                System.err.println(e.getMessage());
            }

        }
}
