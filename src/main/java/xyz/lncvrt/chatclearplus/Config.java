package xyz.lncvrt.chatclearplus;


import eu.midnightdust.lib.config.MidnightConfig;

public class Config extends MidnightConfig {
    @Entry(isSlider = true, min = 25, max = 1000)
    public static int amountToTriggerClear = 100;
    @Entry
    public static boolean allowEmptyMessages = false;
    @Entry
    public static String chatClearedMessage = "&cChat has been cleared";
    @Comment
    public static Comment chatClearedMessageInfo;
}