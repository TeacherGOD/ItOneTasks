package com.vv.springcore.musicplayer;

import org.springframework.stereotype.Component;

/**
 * @author VV Boss
 */
@Component
public class RockMusic implements Music {
    @Override
    public String getSong() {
        return "Random Rock Music Song";
    }
}
