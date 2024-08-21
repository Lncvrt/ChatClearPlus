package xyz.lncvrt.chatclearplus;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import xyz.lncvrt.chatclearplus.event.ClientReceiveMessageHandler;

public class ChatClearPlus implements ModInitializer {
    @Override
    public void onInitialize() {
        MidnightConfig.init("chatclearplus", Config.class);
        ClientReceiveMessageHandler.register();
    }
}
