package com.nukkitworlds.managers;

import cn.nukkit.Server;
import cn.nukkit.level.Level;
import com.nukkitworlds.NukkitWorlds;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class WorldConfigManager {

    private final NukkitWorlds plugin;

    private final Set<String> noMobSpawn;
    private final Set<String> noLeafDecay;
    private final Set<String> noFire;
    private final Set<String> noLightningFire;

    private boolean debug;

    public WorldConfigManager(NukkitWorlds plugin) {
        this.plugin = plugin;
        this.noMobSpawn = new HashSet<>();
        this.noLeafDecay = new HashSet<>();
        this.noFire = new HashSet<>();
        this.noLightningFire = new HashSet<>();
        reload();
    }

    public void reload() {
        plugin.reloadConfig();
        var config = plugin.getConfig();

        this.debug = config.getBoolean("debug", false);

        noMobSpawn.clear();
        noMobSpawn.addAll(config.getStringList("disabled-mob-spawn"));

        noLeafDecay.clear();
        noLeafDecay.addAll(config.getStringList("disabled-leaf-decay"));

        noFire.clear();
        noFire.addAll(config.getStringList("disabled-fire"));

        noLightningFire.clear();
        noLightningFire.addAll(config.getStringList("disabled-lightning-fire"));

        if (debug) {
            plugin.getLogger().info("Loaded " + noMobSpawn.size() + " worlds with disabled mob spawn");
            plugin.getLogger().info("Loaded " + noLeafDecay.size() + " worlds with disabled leaf decay");
            plugin.getLogger().info("Loaded " + noFire.size() + " worlds with disabled fire");
            plugin.getLogger().info("Loaded " + noLightningFire.size() + " worlds with disabled lightning fire");
        }
    }

    public boolean isMobSpawnDisabled(String worldName) {
        return noMobSpawn.contains(worldName);
    }

    public boolean isMobSpawnDisabled(Level level) {
        return level != null && noMobSpawn.contains(level.getFolderName());
    }

    public boolean isLeafDecayDisabled(String worldName) {
        return noLeafDecay.contains(worldName);
    }

    public boolean isLeafDecayDisabled(Level level) {
        return level != null && noLeafDecay.contains(level.getFolderName());
    }

    public boolean isFireDisabled(String worldName) {
        return noFire.contains(worldName);
    }

    public boolean isFireDisabled(Level level) {
        return level != null && noFire.contains(level.getFolderName());
    }

    public boolean isLightningFireDisabled(String worldName) {
        return noLightningFire.contains(worldName);
    }

    public boolean isLightningFireDisabled(Level level) {
        return level != null && noLightningFire.contains(level.getFolderName());
    }

    public boolean isDebug() {
        return debug;
    }

    public Set<String> getConfiguredWorlds() {
        Set<String> all = new HashSet<>();
        all.addAll(noMobSpawn);
        all.addAll(noLeafDecay);
        all.addAll(noFire);
        all.addAll(noLightningFire);
        return all;
    }

    public Set<String> getNoMobSpawnWorlds() {
        return Collections.unmodifiableSet(noMobSpawn);
    }

    public Set<String> getNoLeafDecayWorlds() {
        return Collections.unmodifiableSet(noLeafDecay);
    }

    public Set<String> getNoFireWorlds() {
        return Collections.unmodifiableSet(noFire);
    }

    public Set<String> getNoLightningFireWorlds() {
        return Collections.unmodifiableSet(noLightningFire);
    }
}