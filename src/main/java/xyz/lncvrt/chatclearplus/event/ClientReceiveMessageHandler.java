package xyz.lncvrt.chatclearplus.event;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import xyz.lncvrt.chatclearplus.Config;

import java.util.ArrayList;
import java.util.List;

public class ClientReceiveMessageHandler {
    private static final List<String> chatHistory = new ArrayList<>();

    public static void register() {
        ClientReceiveMessageEvents.ALLOW_GAME.register((message, overlay) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null) {
                String messageText = message.getString();

                chatHistory.add(messageText);

                int blankLineCount = 0;
                for (String line : chatHistory) {
                    if (line.trim().isEmpty()) {
                        blankLineCount++;
                    }
                }

                if (blankLineCount >= Config.amountToTriggerClear) {
                    chatHistory.clear();
                    client.inGameHud.getChatHud().clear(true);
                    if (!Config.chatClearedMessage.trim().isEmpty()) {
                        client.inGameHud.getChatHud().addMessage(Text.of(parseMessage(Config.chatClearedMessage)));
                    }
                    return false;
                }

                if (chatHistory.size() >= Config.amountToTriggerClear * 2) {
                    chatHistory.subList(0, chatHistory.size() - Config.amountToTriggerClear).clear();
                }

                return Config.allowEmptyMessages || !message.getString().trim().isEmpty();
            }
            return true;
        });
    }

    private static String parseMessage(String message) {
        for (Formatting formatting : Formatting.values()) {
            if (formatting.isColor() || formatting.isModifier()) {
                message = message.replace("&" + formatting.getCode(), "§" + formatting.getCode());
            }
        }
        return message;
    }
}