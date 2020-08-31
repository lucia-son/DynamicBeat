package dynamic_beat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player; /*따로 외부에서 다운받아서 build path로 추가한 라이브러리
해당 라이브러리 내의 player 클래스로 손쉽게 음악을 재생할 수 있는데, 사전에 음악파일을 분석할 수 있게 해야 함
*/

public class Music extends Thread { //스레드를 사용하면 음악 재생 중간에 음악을 중단하거나 다른 일을 할 수 있게 한다. 스레드는 작은 하나의 프로그램

    private Player player;
    private boolean isLoop; //곡이 무한 루프인지, 한번 재생 후 종료되는지에 대한 변수
    private File file;
    private FileInputStream fis;
    private BufferedInputStream bis;

    // 생성자
    public Music(String name, boolean isLoop) {
        try {
            this.isLoop = isLoop;
            file = new File(Main.class.getResource("../musics/" + name).toURI());
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getTime() {
        if (player == null)
            return 0;
        return player.getPosition();
    }

    public void close () {
        isLoop = false ;
        player.close();
        this.interrupt(); //곡 종료
    }

    @Override
    public void run() {
        try {
            do {
                player.play();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
            } while (isLoop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
