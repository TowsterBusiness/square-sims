package me.towster;

import me.towster.utils.Window;

public class Main {
    public static void main(String[] args) throws Exception {
        Window.setup(new ThreeDTest());
        Window window = Window.get();
        window.run();
    }
}