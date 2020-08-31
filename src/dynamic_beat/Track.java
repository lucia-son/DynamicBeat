package dynamic_beat; //track이라는 클래스는 하나의 곡에 대한 정보

public class Track {

    private String titleImage; //곡 제목 부분 이미지
    private String startImage; // 게임 선택 창 표지 이미지
    private String gameImage;  // 해당 곡으로 게임 실행할 때 이미지
    private String startMusic; // 게임 선택 창 음악
    private String gameMusic;   // 해당 곡으로 게임 실행할 때 이미지
    private String titleName; // 곡 제목

    public String getTitleImage() {
        return titleImage;
    }
    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }
    public String getStartImage() {
        return startImage;
    }
    public void setStartImage(String startImage) {
        this.startImage = startImage;
    }
    public String getGameImage() {
        return gameImage;
    }
    public void setGameImage(String gameImage) {
        this.gameImage = gameImage;
    }
    public String getStartMusic() {
        return startMusic;
    }
    public void setStartMusic(String startMusic) {
        this.startMusic = startMusic;
    }
    public String getGameMusic() {
        return gameMusic;
    }
    public void setGameMusic(String gameMusic) {
        this.gameMusic = gameMusic;
    }
    public String getTitleName() { return titleName;}
    public void setTitleName(String titleName) { this.titleName = titleName; }

    //생성자 : 트랙이라는 클래스를 사용하여 하나의 변수를 생성할 때 한번에 그 내부  값을 초기화해주는 기능
    public Track(String titleImage, String startImage, String gameImage, String startMusic, String gameMusic, String titleName) {
        super();
        this.titleImage = titleImage;
        this.startImage = startImage;
        this.gameImage = gameImage;
        this.startMusic = startMusic;
        this.gameMusic = gameMusic;
        this.titleName = titleName;
    }
}
