package com.vv.springcore.musicplayer;

/**
 * @author VV Boss
 */
public class ClassicalMusic implements Music {
    @Override
    public String getSong() {
        return "Random Classic Music Song";
    }

    private ClassicalMusic() {}

    public static ClassicalMusic getClassicalMusic() {
        return new ClassicalMusic();
    }

    public void doMyInit() {
        System.out.println("Doing my initialization");
    }

    public void doMyDestroy() {
        System.out.println("Doing my destruction");
    }

}
