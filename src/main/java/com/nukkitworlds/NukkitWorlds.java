package com.nukkitworlds;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import com.nukkitworlds.commands.NWorldsCommand;
import com.nukkitworlds.listeners.EventListener;
import com.nukkitworlds.managers.WorldConfigManager;
import com.nukkitworlds.utils.CoreDetector;
import com.nukkitworlds.utils.MessageUtils;

public class NukkitWorlds extends PluginBase {

    private Config messagesConfig;
    private MessageUtils msg;
    private WorldConfigManager worldConfigManager;
    private CoreDetector coreDetector;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveResource("messages.yml");
        messagesConfig = new Config(getDataFolder() + "/messages.yml", Config.YAML);

        coreDetector = new CoreDetector();
        msg = new MessageUtils(this);
        worldConfigManager = new WorldConfigManager(this);

        getServer().getPluginManager().registerEvents(new EventListener(this, worldConfigManager), this);

        var command = new NWorldsCommand(this);
        getServer().getCommandMap().register("nworlds", command);

        getLogger().info("NukkitWorlds v" + getDescription().getVersion() + " enabled.");
        getLogger().info("Detected core: " + coreDetector.getCoreName() + " " + coreDetector.getCoreVersion());
        getLogger().info("Author: Vaktari-Dev");
    }

    @Override
    public void onDisable() {
        getLogger().info("NukkitWorlds disabled.");
    }

    public Config getMessagesConfig() {
        return messagesConfig;
    }

    public MessageUtils getMsg() {
        return msg;
    }

    public WorldConfigManager getWorldConfigManager() {
        return worldConfigManager;
    }

    public CoreDetector getCoreDetector() {
        return coreDetector;
    }
}