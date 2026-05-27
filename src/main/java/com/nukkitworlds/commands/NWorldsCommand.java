package com.nukkitworlds.commands;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.nukkitworlds.NukkitWorlds;

import java.util.HashMap;
import java.util.Map;

public class NWorldsCommand extends Command {

    private final NukkitWorlds plugin;

    public NWorldsCommand(NukkitWorlds plugin) {
        super("nworlds", "Manage world configurations", "/nworlds <reload|info>");
        this.plugin = plugin;
        this.setPermission("nukkitworlds.admin");
        this.commandParameters.clear();
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(plugin.getMsg().format("usage"));
            return false;
        }

        return switch (args[0].toLowerCase()) {
            case "reload" -> handleReload(sender);
            case "info" -> handleInfo(sender);
            default -> {
                sender.sendMessage(plugin.getMsg().format("usage"));
                yield false;
            }
        };
    }

    private boolean handleReload(CommandSender sender) {
        if (!sender.hasPermission("nukkitworlds.reload") && !sender.hasPermission("nukkitworlds.admin")) {
            sender.sendMessage(plugin.getMsg().format("no-permission"));
            return false;
        }

        try {
            plugin.getWorldConfigManager().reload();
            plugin.getMsg().reload();
            sender.sendMessage(plugin.getMsg().format("reload-success"));
            plugin.getLogger().info("Configuration reloaded by " + sender.getName());
            return true;
        } catch (Exception e) {
            sender.sendMessage(plugin.getMsg().format("reload-fail"));
            plugin.getLogger().error("Failed to reload config: " + e.getMessage());
            return false;
        }
    }

    private boolean handleInfo(CommandSender sender) {
        if (!sender.hasPermission("nukkitworlds.info") && !sender.hasPermission("nukkitworlds.admin")) {
            sender.sendMessage(plugin.getMsg().format("no-permission"));
            return false;
        }

        var config = plugin.getWorldConfigManager();
        var worlds = config.getConfiguredWorlds();

        if (worlds.isEmpty()) {
            sender.sendMessage(plugin.getMsg().format("info-no-worlds"));
            return true;
        }

        sender.sendMessage(plugin.getMsg().format("info-header"));

        for (String world : worlds) {
            Map<String, String> placeholders = new HashMap<>();
            placeholders.put("world", world);
            placeholders.put("spawn", config.isMobSpawnDisabled(world) ? "&c✗" : "&a✓");
            placeholders.put("decay", config.isLeafDecayDisabled(world) ? "&c✗" : "&a✓");
            placeholders.put("fire", config.isFireDisabled(world) ? "&c✗" : "&a✓");
            placeholders.put("lightning", config.isLightningFireDisabled(world) ? "&c✗" : "&a✓");
            sender.sendMessage(plugin.getMsg().format("info-entry", placeholders));
        }

        Map<String, String> footerPlaceholders = new HashMap<>();
        footerPlaceholders.put("count", String.valueOf(worlds.size()));
        sender.sendMessage(plugin.getMsg().format("info-footer", footerPlaceholders));
        return true;
    }
}