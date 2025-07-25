package com.vv.springcore.musicplayer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author VV Boss
 */
@Component
public class ClassicalMusic implements Music {
    private List<String> songs = new ArrayList<>();

    {
        songs.add("Random Classical Music No1");
        songs.add("Random Classical Music No2");
        songs.add("Random Classical Music No3");
    }

    @Override
    public List<String> getSongs() {
        return songs;
    }
}
