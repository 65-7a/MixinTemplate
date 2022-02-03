package com.callumwong.mixintemplate;

public class MixinTemplate {
    private static MixinTemplate INSTANCE;
    
    public static MixinTemplate getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MixinTemplate();
        }
        
        return INSTANCE;
    }
    
    public void start() {
        System.out.println("hello");
    }
}
