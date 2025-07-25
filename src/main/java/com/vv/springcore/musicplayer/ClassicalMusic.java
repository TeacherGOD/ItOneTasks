package com.vv.springcore.musicplayer;

import org.springframework.stereotype.Component;

/**
 * @author VV Boss
 */
@Component
public class ClassicalMusic implements Music {
    @Override
    public String getSong() {
        return "Random Classic Music Song";
    }

}
