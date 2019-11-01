package xyz.acrylicstyle.connection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.*;
import java.util.Objects;
import java.util.UUID;

public class PluginChannelListener implements PluginMessageListener {

    @Override
    public synchronized void onPluginMessageReceived(String tag, Player player, byte[] message) {
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
            String subchannel = in.readUTF();
            String input = in.readUTF(); // message
            if (tag.equalsIgnoreCase("helper:message")) {
                Objects.requireNonNull(Bukkit.getPlayer(UUID.fromString(subchannel))).sendMessage(input);
            } else if (tag.equalsIgnoreCase("helper:kick")) {
                Objects.requireNonNull(Bukkit.getPlayer(UUID.fromString(subchannel))).kickPlayer(input);
            }
        } catch (IOException ignored) {}
    }
}
