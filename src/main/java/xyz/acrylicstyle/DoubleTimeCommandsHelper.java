package xyz.acrylicstyle;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.acrylicstyle.connection.PluginChannelListener;

@SuppressWarnings("unused") // its completely using!!!!!
public class DoubleTimeCommandsHelper extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginChannelListener pcl = new PluginChannelListener();
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "helper:message", pcl);
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "helper:kick", pcl);
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "helper:connect", pcl);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "helper:connect");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "helper:sound", pcl);
    }
}
