package xyz.acrylicstyle.connection;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.*;

public class PluginChannelListener implements PluginMessageListener {

    @Override
    public synchronized void onPluginMessageReceived(String tag, Player player, byte[] message) {
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
            in.readUTF();
            String input = in.readUTF(); // message
            player.sendMessage(input);
        } catch (IOException ignored) {}
    }
}
