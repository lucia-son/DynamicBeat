package dynamic_beat;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame { //jframe은 이미 존재하는 라이브러리로, GUI가 가능하게 해준다

    private Image screenImage; // 전체 화면에 대한 이미지를 담는 인스턴스; 더블버퍼링
    private Graphics screenGraphic; // 전체 화면에 대한 이미지를 담는 인스턴스; 더블버퍼링

    private ImageIcon exitButtonEnteredImage= new ImageIcon(Main.class.getResource("../images/exit_hover.png"));
    private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exit_basic.png"));

    private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/start_basic.png"));
    private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/start_enter.png"));
    private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quit_basic.png"));
    private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quit_enter.png"));

    private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftbutton.png"));
    private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftbutton_enter.png"));
    private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightbutton.png"));
    private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightbutton_enter.png"));

    private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easybutton.png"));
    private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easybutton_enter.png"));
    private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardbutton.png"));
    private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardbutton_enter.png"));

    private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backbutton.png"));
    private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backbutton_enter.png"));

    private Image background = new ImageIcon(Main.class.getResource("../images/intro_back.png")).getImage();
    private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/MENUBAR.png"))); // menuBar라는 객체안에 해당 이미지가 자리잡게 된다

    private Music introMusic = new Music("intro_music.mp3", true);


    private JButton exitButton = new JButton(exitButtonBasicImage); //버튼생성 후 초기 이미지값은 basic
    private JButton startButton =  new JButton(startButtonBasicImage);
    private JButton quitButton = new JButton(quitButtonBasicImage);
    private JButton leftButton =  new JButton(leftButtonBasicImage);
    private JButton rightButton = new JButton(rightButtonBasicImage);
    private JButton easyButton =  new JButton(easyButtonBasicImage);
    private JButton hardButton = new JButton(hardButtonBasicImage);
    private JButton backButton =  new JButton(backButtonBasicImage);

    private int mouseX, mouseY; //현재 프로그램에서 마우스의 위치를 나타냄

    private boolean isMainScreen = false ; // 처음에는 메인화면이 아닌 시작화면이기때문, 메인화면으로 바뀌면 이 값이 true
    private boolean isGameScreen = false ; //게임화면으로 넘어왔는지 파악하는 변수

    ArrayList<Track> tracklist = new ArrayList<Track>(); //트랙을 담을 수 있는 리스트 생성

    private Image titleImage;
    private Image selectedImage;
    private Music selectedMusic;
    private int nowSelected = 0; // 첫번째 곡을 의미 , 현재 선택된 트랙리스트의  번호

    public static Game game;  //프로젝트 전체에서 사용되는 변수로 만들어 한 번에 하나의 게임만 가능하게 하기,변수로 선언

    public DynamicBeat() { // 생성자
        //트랙리스트 각 인덱스에 변수 담기
        tracklist.add(new Track("nhc_crazy_title.png", "nhc_crazy_start.png", "nhc_crazy_game.png", "nhc_crazy_cut.mp3", "nhc_crazy.mp3", "NHC-Crazy"));
        tracklist.add(new Track("lizzo_juice_title.png", "lizzo_juice_start.png", "lizzo_juice_game.png", "lizzo_juice_cut.mp3", "lizzo_juice.mp3", "Lizzo-Juice"));
        tracklist.add(new Track("svt_change_title.png", "svt_change_start.png", "svt_change_game.png", "svt_change_cut.mp3", "svt_change.mp3", "SVT-Change Up"));

        setUndecorated(true); // 실행할 때 기본적으로 존재하는 메뉴바가 존재하지 않게 됨
        setTitle("Dynamic Beat");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null); // 게임창이 정중앙에 뜨도록
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임창 종료시 프로그램 전체 종료
        setVisible(true); // 게임창이 정상적으로 출력될 수 있게 디폴트가 false이기 때문에 설정 필요
        setBackground(new Color(0, 0, 0, 0)); // 페인트컴포넌트를 했을 때 배경이 하얀색이 된다
        setLayout(null); // 버튼이나 jlabel을 넣을 때 그 위치 그대로 꽂히게
        introMusic.start(); // 시작하면서 음악이 동시에 무한정 재생

        addKeyListener(new KeyListener()); //keylistener class가 적용된다

        exitButton.setBounds(1230, 10, 30, 30);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setIcon(exitButtonEnteredImage); //마우스가 올라가면 다른 이미지로 바꾼다
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonentered.mp3", false); //isloop부분의 파라미터값이  false: 반복재생 안되게
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setIcon(exitButtonBasicImage); //마우스를 떼면 다른 이미지로 바꾼다
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonEnteredMusic = new Music("buttonpressed.mp3", false);
                buttonEnteredMusic.start();
                try {
                    Thread.sleep(1000); // 1초의 대기시간 후에 종료되도록
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);; //마우스를 누르면 프로그램 종료
            }
        });

        add(exitButton);

        startButton.setBounds(40, 250, 400, 100);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setIcon(startButtonEnteredImage); //마우스가 올라가면 다른 이미지로 바꾼다
                startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonentered.mp3", false); //isloop부분의 파라미터값이  false: 반복재생 안되게
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setIcon(startButtonBasicImage); //마우스를 떼면 다른 이미지로 바꾼다
                startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) { // 처음 main 화면으로 넘어가는 부분
                Music buttonEnteredMusic = new Music("buttonpressed.mp3", false);
                buttonEnteredMusic.start();
                introMusic.close(); //인트로 뮤직 끄고
                enterMain();
            }
        });

        add(startButton);

        quitButton.setBounds(40, 380, 400, 100);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                quitButton.setIcon(quitButtonEnteredImage); //마우스가 올라가면 다른 이미지로 바꾼다
                quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonentered.mp3", false); //isloop부분의 파라미터값이  false: 반복재생 안되게
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                quitButton.setIcon(quitButtonBasicImage); //마우스를 떼면 다른 이미지로 바꾼다
                quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonEnteredMusic = new Music("buttonpressed.mp3", false);
                buttonEnteredMusic.start();
            }
        });

        add(quitButton);

        leftButton.setVisible(false);
        leftButton.setBounds(140, 310, 60, 60);
        leftButton.setBorderPainted(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setFocusPainted(false);
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                leftButton.setIcon(leftButtonEnteredImage);
                leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonentered.mp3", false);
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                leftButton.setIcon(leftButtonBasicImage);
                leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonEnteredMusic = new Music("buttonpressed.mp3", false);
                buttonEnteredMusic.start();
                // 왼쪽 버튼 이벤트
                selectLeft();
            }
        });

        add(leftButton);

        rightButton.setVisible(false); // 맨처음에는 보이지 않게
        rightButton.setBounds(1080, 310, 60, 60);
        rightButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setFocusPainted(false);
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rightButton.setIcon(rightButtonEnteredImage);
                rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonentered.mp3", false);
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                rightButton.setIcon(rightButtonBasicImage); //마우스를 떼면 다른 이미지로 바꾼다
                rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonEnteredMusic = new Music("buttonpressed.mp3", false);
                buttonEnteredMusic.start();
                // 오른쪽 버튼 이벤트
                selectRight();
            }
        });

        add(rightButton);

        easyButton.setVisible(false); // 맨처음에는 보이지 않게
        easyButton.setBounds(375, 580, 250, 70);
        easyButton.setBorderPainted(false);
        easyButton.setContentAreaFilled(false);
        easyButton.setFocusPainted(false);
        easyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                easyButton.setIcon(easyButtonEnteredImage);
                easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonentered.mp3", false);
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                easyButton.setIcon(easyButtonBasicImage); //마우스를 떼면 다른 이미지로 바꾼다
                easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                isGameScreen = true;
                Music buttonEnteredMusic = new Music("buttonpressed.mp3", false);
                buttonEnteredMusic.start();
                gameStart(nowSelected, "Easy");
            }
        });

        add(easyButton);

        hardButton.setVisible(false); // 맨처음에는 보이지 않게
        hardButton.setBounds(655, 580, 250, 70);
        hardButton.setBorderPainted(false);
        hardButton.setContentAreaFilled(false);
        hardButton.setFocusPainted(false);
        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hardButton.setIcon(hardButtonEnteredImage);
                hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonentered.mp3", false);
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                hardButton.setIcon(hardButtonBasicImage); //마우스를 떼면 다른 이미지로 바꾼다
                hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                isGameScreen = true;
                Music buttonEnteredMusic = new Music("buttonpressed.mp3", false);
                buttonEnteredMusic.start();
                gameStart(nowSelected, "Hard");
            }
        });

        add(hardButton);

        backButton.setVisible(false); // 맨처음에는 보이지 않게
        backButton.setBounds(20, 50, 60, 60);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setIcon(backButtonEnteredImage);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music buttonEnteredMusic = new Music("buttonentered.mp3", false);
                buttonEnteredMusic.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setIcon(backButtonBasicImage); //마우스를 떼면 다른 이미지로 바꾼다
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                Music buttonEnteredMusic = new Music("buttonpressed.mp3", false);
                buttonEnteredMusic.start();
                //back 버튼 모드 이벤트: 메인 화면으로 돌아가기
                backMain();
                isGameScreen = false;
            }
        });

        add(backButton);

        menuBar.setBounds(0, 0, 1280, 30); // 사각형 위치 및 크기 선언

        menuBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { //마우스로 해당 버튼을 누를 때에 대한 이벤트 처리
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - mouseX, y - mouseY); //메뉴바를 잡고
            }
        });

        add(menuBar); // JFrame에 메뉴바가 추가됨

        background = new ImageIcon(Main.class.getResource("../images/intro_back.png")).getImage();
        // 이미지 아이콘이라는 클래스를 import해야 하며, main 클래스 위치를 기반으로 하여 images폴더 안의이미지 파일을 얻어와서
        // geImage() background라는 변수의 값으로 초기화

    }

    // 메소드
    public void paint(Graphics g) { // paint는  GUI게임에서 가장 첫번째 화면을 그려주는 메소드
        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 프로그램 화면 크기만큼 이미지 생성
        screenGraphic = screenImage.getGraphics(); //
        screenDraw((Graphics2D) screenGraphic); // 개발자가 원하는대로 SCREENDraw 라는 함수를 통해 이미지를 그려주는데, 해당 함수는 다음에 정의 // (Graphics2D)로 해당 매개변수로 형변환
        g.drawImage(screenImage, 0, 0, null);
    }

    public void screenDraw(Graphics2D g) {
        g.drawImage(background, 0, 0, null); // 이미지를 그려주는 방식이 정의되어있다. 0, 0 좌표에 그려준다
        if(isMainScreen)
        {
            g.drawImage(selectedImage, 340, 200, null); //drawImage는 들어온 이미지 또는 역동적인 이미지를 그려주는 역할
            g.drawImage(titleImage, 340, 70, null);
        }
        if(isGameScreen)
        {
            game.screenDraw(g);
        }

        paintComponents(g); // 메뉴바는 고정되는 것, paintComponents로 JLabel을 그려준다.즉, add되는 것들을 그려준다.
        this.repaint(); // paint 함수 재호출
    }

    public void selectTrack(int nowSelected) {
        if(selectedMusic != null)
            selectedMusic.close(); //어떤 곡이 실행중이라면 음악 재생 중지
        titleImage = new ImageIcon(Main.class.getResource("../images/" + tracklist.get(nowSelected).getTitleImage())).getImage();
        selectedImage = new ImageIcon(Main.class.getResource("../images/" + tracklist.get(nowSelected).getStartImage())).getImage();
        selectedMusic = new Music(tracklist.get(nowSelected).getStartMusic(), true);
        selectedMusic.start();
    }

    public void selectLeft()  { //왼쪽으로 화면을 넘길 때 적용되는 함수
        if(nowSelected == 0 )  //이미 첫번째 화면에서 왼쪽으로 넘어간다면
            nowSelected = tracklist.size() - 1; // 트랙리스트의 가장 마지막 곡이 나와야 한다
        else
            nowSelected--;
        selectTrack(nowSelected); // 선택된 곡 정보에 대해 selectedTrack 함수 적용
    }

    public void selectRight()  { //왼쪽으로 화면을 넘길 때 적용되는 함수
        if(nowSelected ==  tracklist.size() - 1 )  //이미 첫번째 화면에서 왼쪽으로 넘어간다면
            nowSelected = 0; // 트랙리스트의 가장 마지막 곡이 나와야 한다
        else
            nowSelected++;
        selectTrack(nowSelected); // 선택된 곡 정보에 대해 selectedTrack 함수 적용
    }

    public void gameStart(int nowSelected, String difficulty)  { // 모드 선택시 발동되는 함수
        if(selectedMusic != null)  // 어떤 음악이 실행중이라면
            selectedMusic.close();   //현재 재생중인 음악을 닫기
        isMainScreen = false ;    //메인화면이 아님을 전달
        leftButton.setVisible(false);
        rightButton.setVisible(false);
        easyButton.setVisible(false);
        hardButton.setVisible(false);
        background = new ImageIcon(Main.class.getResource("../images/" + tracklist.get(nowSelected).getGameImage())).getImage();
        backButton.setVisible(true);
        game = new Game(tracklist.get(nowSelected).getTitleName(),difficulty, tracklist.get(nowSelected).getGameMusic());
        game.start();
        setFocusable(true); //키보드 포커스를 맞추기]
    }

    public void backMain()  { // 메인으로 돌아가는 함수
        isMainScreen = true ;
        leftButton.setVisible(true);
        rightButton.setVisible(true);
        easyButton.setVisible(true);
        hardButton.setVisible(true);
        background = new ImageIcon(Main.class.getResource("../images/main_back.jpg")).getImage();
        backButton.setVisible(false);
        selectTrack(nowSelected);
        isGameScreen = false;
        game.close();
    }

    public void enterMain()  {
        startButton.setVisible(false); //마우스 누르면 버튼 사라진다
        quitButton.setVisible(false);
        background = new ImageIcon(Main.class.getResource("../images/main_back.jpg")).getImage();
        isMainScreen = true;
        leftButton.setVisible(true); //마우스누르면 버튼 생겨난다
        rightButton.setVisible(true);
        easyButton.setVisible(true);
        hardButton.setVisible(true);
        introMusic.close()
        ;		selectTrack(0); // 메인화면 넘어갈 때 가장 처음 뜨는 곡 설정
    }

}
