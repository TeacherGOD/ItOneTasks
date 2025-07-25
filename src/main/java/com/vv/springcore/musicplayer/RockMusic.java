package com.vv.springcore.musicplayer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author VV Boss
 */
@Component
public class RockMusic implements Music {
    private List<String> songs = new ArrayList<>();

    {
        songs.add("Random Rock Music No 1");
        songs.add("Random Rock Music No 2");
        songs.add("Random Rock Music No 3");
    }

    @Override
    public List<String> getSongs() {
        return songs;
    }
}
