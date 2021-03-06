package com.eintosti.buildsystem.listener;

import com.eintosti.buildsystem.BuildSystem;
import com.eintosti.buildsystem.manager.WorldManager;
import com.eintosti.buildsystem.object.world.BuildWorld;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author einTosti
 */
public class BuildWorldResetUnloadListener implements Listener {

    private final WorldManager worldManager;

    public BuildWorldResetUnloadListener(BuildSystem plugin) {
        this.worldManager = plugin.getWorldManager();

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        World from = event.getFrom();
        BuildWorld buildWorld = worldManager.getBuildWorld(from.getName());
        if (buildWorld != null) {
            buildWorld.resetUnloadTask();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        World from = event.getPlayer().getWorld();
        BuildWorld buildWorld = worldManager.getBuildWorld(from.getName());
        if (buildWorld != null) {
            buildWorld.resetUnloadTask();
        }
    }
}