package com.vv.springcore;

import com.vv.springcore.musicplayer.ClassicalMusic;
import com.vv.springcore.musicplayer.Computer;
import com.vv.springcore.musicplayer.Music;
import com.vv.springcore.musicplayer.MusicPlayer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        Computer computer = context.getBean("computer", Computer.class);
        System.out.println(computer);

        context.close();
    }
}
