package xyz.acrylicstyle;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;
import util.ICollectionList;
import xyz.acrylicstyle.connection.PluginChannelListener;

@SuppressWarnings("unused") // its completely using!!!!!
public class DoubleTimeCommandsHelper extends JavaPlugin {
    public static final Sound BLOCK_NOTE_PLING;
    public static final Sound ENTITY_EXPERIENCE_ORB_PICKUP;
    public static DoubleTimeCommandsHelper instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    static {
        if (ICollectionList.asList(Sound.values()).map(Enum::name).contains("BLOCK_NOTE_PLING")) {
            BLOCK_NOTE_PLING = Sound.valueOf("BLOCK_NOTE_PLING");
        } else {
            BLOCK_NOTE_PLING = Sound.valueOf("NOTE_PLING");
        }
        if (ICollectionList.asList(Sound.values()).map(Enum::name).contains("ENTITY_EXPERIENCE_ORB_PICKUP")) {
            ENTITY_EXPERIENCE_ORB_PICKUP = Sound.valueOf("ENTITY_EXPERIENCE_ORB_PICKUP");
        } else {
            ENTITY_EXPERIENCE_ORB_PICKUP = Sound.valueOf("ORB_PICKUP");
        }
    }

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
