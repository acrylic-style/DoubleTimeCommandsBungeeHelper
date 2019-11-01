package xyz.acrylicstyle.connection;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class PluginChannelListener implements PluginMessageListener {

    @Override
    public synchronized void onPluginMessageReceived(String tag, Player player, byte[] message) {
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
            String subchannel = in.readUTF();
            String input = in.readUTF(); // message
            String[] p = subchannel.split(",");
            if (tag.equalsIgnoreCase("helper:message")) {
                if (p.length >= 2 && p[1].startsWith("/p")) {
                    TextComponent dialog = new TextComponent("" + ChatColor.GREEN + ChatColor.BOLD + "[ACCEPT]");
                    dialog.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GREEN + "Accept the party invite and join to their party.").create()));
                    dialog.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p accept " + p[2]));
                    TextComponent deny = new TextComponent("" + ChatColor.RED + ChatColor.BOLD + "[DENY]");
                    deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.RED + "Decline the party invite.").create()));
                    deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p deny " + p[2]));
                    dialog.addExtra("" + ChatColor.RESET + ChatColor.GRAY + " - ");
                    dialog.addExtra(deny);
                    Objects.requireNonNull(Bukkit.getPlayer(UUID.fromString(p[0]))).spigot().sendMessage(dialog);
                } else if (p.length >= 2 && p[1].startsWith("/f")) {
                    TextComponent dialog = new TextComponent("" + ChatColor.GREEN + ChatColor.BOLD + "[ACCEPT]");
                    dialog.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GREEN + "Accept the friend request and add to your/their friend list.").create()));
                    dialog.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f accept " + p[2]));
                    TextComponent deny = new TextComponent("" + ChatColor.RED + ChatColor.BOLD + "[DENY]");
                    deny.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.RED + "Decline the friend request.").create()));
                    deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/f deny " + p[2]));
                    dialog.addExtra("" + ChatColor.RESET + ChatColor.GRAY + " - ");
                    dialog.addExtra(deny);
                    Objects.requireNonNull(Bukkit.getPlayer(UUID.fromString(p[0]))).spigot().sendMessage(dialog);
                } else Objects.requireNonNull(Bukkit.getPlayer(UUID.fromString(p[0]))).sendMessage(input);
            } else if (tag.equalsIgnoreCase("helper:kick")) {
                Objects.requireNonNull(Bukkit.getPlayer(UUID.fromString(p[0]))).kickPlayer(input);
            }
        } catch (IOException ignored) {}
    }
}
