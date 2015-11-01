package org.YiiCommunity.GitterBot.HelpCommand;

import com.amatkivskiy.gitter.rx.sdk.model.response.message.MessageResponse;
import org.YiiCommunity.GitterBot.GitterBot;
import org.YiiCommunity.GitterBot.api.Command;
import org.YiiCommunity.GitterBot.containers.Gitter;

import java.util.ArrayList;
import java.util.List;

public class Help extends Command {

    private List<String> commands = new ArrayList<>();

    public Help() {
        commands = getConfig().getStringList("commands");
    }

    @Override
    public void onMessage(MessageResponse message) {
        if (message.text.trim().equalsIgnoreCase("@" + GitterBot.getInstance().getConfiguration().getBotUsername())) {
            showHelp(message.fromUser.username);
            return;
        }
        for (String item : commands) {
            if (message.text.trim().equalsIgnoreCase("@" + GitterBot.getInstance().getConfiguration().getBotUsername() + " " + item)) {
                showHelp(message.fromUser.username);
                return;
            }
        }
    }

    private void showHelp(String username) {
        try {
            Gitter.sendMessage(getConfig().getString("text").replace("{username}", username));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
