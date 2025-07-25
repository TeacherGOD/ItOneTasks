package com.vv.springcore;

import com.vv.springcore.musicplayer.ClassicalMusic;
import com.vv.springcore.musicplayer.Music;
import com.vv.springcore.musicplayer.MusicPlayer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        ClassicalMusic classicalMusic = context.getBean("musicBean", ClassicalMusic.class);

        System.out.println(classicalMusic.getSong());

        context.close();
    }
}
