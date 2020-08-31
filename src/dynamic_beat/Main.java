package dynamic_beat;

import dynamic_beat.DynamicBeat;

public class Main {

    public static final int SCREEN_WIDTH =1280;
    public static final int SCREEN_HEIGHT =720; // 상수는 대문자
    public static final int NOTE_SPEED = 5;
    public static final int SLEEP_TIME = 10;

    public static void main(String[] args) {

        new DynamicBeat();
    }
}