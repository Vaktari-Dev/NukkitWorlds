package com.nukkitworlds.listeners;

import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockIgniteEvent;
import cn.nukkit.event.block.LeavesDecayEvent;
import cn.nukkit.event.entity.EntityExplodeEvent;
import cn.nukkit.event.entity.EntitySpawnEvent;
import com.nukkitworlds.NukkitWorlds;
import com.nukkitworlds.managers.WorldConfigManager;

public class EventListener implements Listener {

    private final NukkitWorlds plugin;
    private final WorldConfigManager configManager;

    public EventListener(NukkitWorlds plugin, WorldConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntitySpawn(EntitySpawnEvent event) {
        var entity = event.getEntity();
        var level = entity.getLevel();
        if (level == null) return;

        if (!configManager.isMobSpawnDisabled(level)) return;

        if (!isHostileMob(entity.getClass())) return;

        try {
            entity.close();
            if (configManager.isDebug()) {
                plugin.getLogger().debug("[MobSpawn] Removed " + entity.getName()
                        + " in " + level.getFolderName());
            }
        } catch (Exception e) {
            plugin.getLogger().warning("Failed to remove entity: " + e.getMessage());
        }
    }

    private boolean isHostileMob(Class<?> clazz) {
        String name = clazz.getSimpleName().toLowerCase();
        return name.contains("creeper") || name.contains("zombie")
                || name.contains("skeleton") || name.contains("spider")
                || name.contains("enderman") || name.contains("witch")
                || name.contains("slime") || name.contains("magmacube")
                || name.contains("blaze") || name.contains("ghast")
                || name.contains("piglin") || name.contains("hoglinn")
                || name.contains("witherskeleton") || name.contains("drowned")
                || name.contains("phantom") || name.contains("vex")
                || name.contains("guardian") || name.contains("shulker")
                || name.contains("evoker") || name.contains("ravager")
                || name.contains("pillager") || name.contains("vindicator");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeavesDecay(LeavesDecayEvent event) {
        var level = event.getBlock().getLevel();
        if (level == null) return;

        if (configManager.isLeafDecayDisabled(level)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockIgnite(BlockIgniteEvent event) {
        var level = event.getBlock().getLevel();
        if (level == null) return;

        if (configManager.isFireDisabled(level)) {
            event.setCancelled(true);
            return;
        }

        if (event.getCause() == BlockIgniteEvent.BlockIgniteCause.LIGHTNING
                && configManager.isLightningFireDisabled(level)) {
            event.setCancelled(true);
        }
    }
}