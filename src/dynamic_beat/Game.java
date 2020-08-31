package dynamic_beat;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Game extends Thread{ //전반적인 게임 틀 안에서 하나의 게임이 하나의 단위로 동작하기 때문에 thread 이용
    private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/note_route_line.png")).getImage();
    private Image judgeLineImage = new ImageIcon(Main.class.getResource("../images/note_bar1.png")).getImage();
    private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/game_Info.png")).getImage();
    private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
    private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
    private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
    private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
    private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
    private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
    private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
    private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();

    private String titleName;
    private String difficulty;
    private String musicTitle;
    private Music gameMusic;

    private boolean gameMaker = true;

    ArrayList<Note> noteList = new ArrayList<Note>(); //배열에 노트를 담아 사용가능하게 생성

    private int index;

    public Game(String titleName, String difficulty,String musicTitle) { //생성자
        this.titleName = titleName;
        this.difficulty = difficulty;
        this.musicTitle = musicTitle;
        gameMusic = new Music(this.musicTitle, false);
    }

    public void screenDraw(Graphics2D g) {
        g.drawImage(noteRouteSImage, 228, 30, null);
        g.drawImage(noteRouteDImage, 332, 30, null);
        g.drawImage(noteRouteFImage, 436, 30, null);
        g.drawImage(noteRouteSpace1Image, 540, 30, null);
        g.drawImage(noteRouteSpace2Image, 640, 30, null);
        g.drawImage(noteRouteJImage, 744, 30, null);
        g.drawImage(noteRouteKImage, 848, 30, null);
        g.drawImage(noteRouteLImage, 952, 30, null);
        g.drawImage(noteRouteLineImage, 224, 30, null);
        g.drawImage(noteRouteLineImage, 328, 30, null);
        g.drawImage(noteRouteLineImage, 432, 30, null);
        g.drawImage(noteRouteLineImage, 536, 30, null);
        g.drawImage(noteRouteLineImage, 740, 30, null);
        g.drawImage(noteRouteLineImage, 844, 30, null);
        g.drawImage(noteRouteLineImage, 948 , 30, null);
        g.drawImage(noteRouteLineImage, 1052, 30, null);

        for(int i = 0 ; i < noteList.size(); i++)
        {
            Note note = noteList.get(i);
            note.screenDraw(g); //리스트에 있는 노트를 하나씩 그려줌
        }

        g.drawImage(judgeLineImage, 0, 600, null);
        g.drawImage(gameInfoImage, 0, 660, null);

        g.setColor(Color.lightGray);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(titleName, 20, 702);
        g.drawString(difficulty, 1190, 702);

        g.setColor(Color.gray);
        g.setFont(new Font("Courier", Font.BOLD, 25));
        g.drawString("S",270,630);
        g.drawString("D",374,630);
        g.drawString("F",478,630);
        g.drawString("SPACE",576,630);
        g.drawString("J",784,630);
        g.drawString("K",888,630);
        g.drawString("L",992,630);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Elephant", Font.BOLD, 30));
        g.drawString("000000", 565, 702); // 점수 출력
    }

    public void pressS() {
        new Music("note1.mp3",false).start();
        noteRouteSImage = new ImageIcon(Main.class.getResource("../images/note_route_press.png")).getImage();
        if (gameMaker==true) {
            System.out.println(gameMusic.getTime() + "S");
        }
    }
    public void releaseS() {
        noteRouteSImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
    }

    public void pressD() {
        noteRouteDImage = new ImageIcon(Main.class.getResource("../images/note_route_press.png")).getImage();
        new Music("note1.mp3",false).start();
        if (gameMaker==true) {
            System.out.println(gameMusic.getTime() + "D");
        }
    }
    public void releaseD() {
        noteRouteDImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
    }

    public void pressF() {
        noteRouteFImage = new ImageIcon(Main.class.getResource("../images/note_route_press.png")).getImage();
        new Music("note1.mp3",false).start();
        if (gameMaker==true) {
            System.out.println(gameMusic.getTime() + "F");
        }
    }
    public void releaseF() {
        noteRouteFImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
    }

    public void pressSpace() {
        noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/note_route_press.png")).getImage();
        noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/note_route_press.png")).getImage();
        new Music("note7.mp3",false).start();
        if (gameMaker==true) {
            System.out.println(gameMusic.getTime() + "Space");
        }
    }
    public void releaseSpace() {
        noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
        noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
    }

    public void pressJ() {
        noteRouteJImage = new ImageIcon(Main.class.getResource("../images/note_route_press.png")).getImage();
        new Music("note1.mp3",false).start();
        if (gameMaker==true) {
            System.out.println(gameMusic.getTime() + "J");
        }
    }
    public void releaseJ() {
        noteRouteJImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
    }

    public void pressK() {
        noteRouteKImage = new ImageIcon(Main.class.getResource("../images/note_route_press.png")).getImage();
        new Music("note1.mp3",false).start();
        if (gameMaker==true) {
            System.out.println(gameMusic.getTime() + "K");
        }
    }
    public void releaseK() {
        noteRouteKImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
    }

    public void pressL() {
        noteRouteLImage = new ImageIcon(Main.class.getResource("../images/note_route_press.png")).getImage();
        new Music("note1.mp3",false).start();
        if (gameMaker==true) {
            System.out.println(gameMusic.getTime() + "L");
        }
    }
    public void releaseL() {
        noteRouteLImage = new ImageIcon(Main.class.getResource("../images/note_route.png")).getImage();
    }

    public void index(String titleName, String difficulty) throws Exception {
        int index = 0;
        BufferedReader br =new BufferedReader(new FileReader("../../text/" + titleName + difficulty + ".txt"));
        String str = "";
        while ((str = br.readLine()) != null) {
            index++;
        }
        this.index = index;
        br.close();
    }

    @Override
    public void run() {
       dropNotes();
    }

    public void close() { // Terminate thread
        gameMusic.close();
        this.interrupt();
    }
    
    public void dropNotes() throws Exception {
        String[] noteType = null;
        try {
            Beat[] beats = null;
            if (titleName.equals("Lizzo-Juice")) {
                if (difficulty.equals("Easy")) {
                    index(titleName, difficulty);
                    int[] time = new int[this.index];
                    int i = 0;
                    BufferedReader br = new BufferedReader(new FileReader("../../text/" + titleName + difficulty + ".txt"));
                    String str = "";
                    while ((str = br.readLine()) != null) {
                        String[] tmp = str.split(" ");
                        String st = tmp[0];
                        int k = Integer.parseInt(st);
                        time[i] = k;
                        noteType[i] = tmp[1];
                        i++;
                    }
                    br.close();
                    int gap = (660 / Main.NOTE_SPEED) * Main.SLEEP_TIME;

                    beats = new Beat[index];
                    for (int k = 0; k < index; k++) {
                        beats[k] = new Beat(time[k] - gap, noteType[k]);
                        gameMusic.start();
                    }
                } else if (difficulty.equals("Hard")) {
                    index(titleName, difficulty);
                    int[] time = new int[this.index];
                    int i = 0;
                    BufferedReader br = new BufferedReader(new FileReader("../../text/" + titleName + difficulty + ".txt"));
                    String str = "";
                    while ((str = br.readLine()) != null) {
                        String[] tmp = str.split(" ");
                        String st = tmp[0];
                        int k = Integer.parseInt(st);
                        time[i] = k;
                        noteType[i] = tmp[1];
                        i++;
                    }
                    br.close();
                    int gap = (660 / Main.NOTE_SPEED) * Main.SLEEP_TIME;

                    beats = new Beat[index];
                    for (int k = 0; k < index; k++) {
                        beats[k] = new Beat(time[k] - gap, noteType[k]);
                        gameMusic.start();
                    }
                }
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
                };
        };
        /*int i = 0;
        while (true) {
            if(beats[i].getTime() <= gameMusic.getTime()) {
                Note note = new Note(beats[i].getNoteName());
                note.start();
                noteList.add(note);
                i++;
            }*/
        }
