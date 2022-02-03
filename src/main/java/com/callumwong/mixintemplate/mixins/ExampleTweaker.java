package com.callumwong.mixintemplate.mixins;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExampleTweaker implements ITweaker {
    private List<String> launchArgs = new ArrayList<>();
    
    @Override
    public final void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.launchArgs.addAll(args);
        
        final String VERSION = "--version";
        final String ASSET_DIR = "--assetDir";
        final String GAME_DIR = "--gameDir";
        
        if (!args.contains(VERSION) && profile != null) {
            launchArgs.add(VERSION);
            launchArgs.add(profile);
        }
        
        if (!args.contains(ASSET_DIR) && profile != null) {
            launchArgs.add(ASSET_DIR);
            launchArgs.add(profile);
        }
        
        if (!args.contains(GAME_DIR) && profile != null) {
            launchArgs.add(GAME_DIR);
            launchArgs.add(profile);
        }
    }
    
    @Override
    public final void injectIntoClassLoader(LaunchClassLoader classLoader) {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.mixintemplate.json");
        
        MixinEnvironment environment = MixinEnvironment.getDefaultEnvironment();
        
        if (environment.getObfuscationContext() == null) {
            environment.setObfuscationContext("notch");
        }
        
        environment.setSide(MixinEnvironment.Side.CLIENT);
    }
    
    @Override
    public String getLaunchTarget() {
        return MixinBootstrap.getPlatform().getLaunchTarget();
    }
    
    @Override
    public String[] getLaunchArguments() {
        return launchArgs.toArray(new String[0]);
    }
}
