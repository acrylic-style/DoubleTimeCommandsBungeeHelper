package xyz.acrylicstyle;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.acrylicstyle.connection.PluginChannelListener;
import xyz.acrylicstyle.tomeito_core.utils.Log;

@SuppressWarnings("unused") // its completely using!!!!!
public class DoubleTimeCommandsHelper extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginChannelListener pcl = new PluginChannelListener();
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "helper:message", pcl);
        Log.info("DoubleTimeCommandsHelper is ready!");
    }
}
