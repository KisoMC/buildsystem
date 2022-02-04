/*
 * Copyright (c) 2022, Thomas Meaney
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.eintosti.buildsystem.inventory;

import com.eintosti.buildsystem.BuildSystem;
import com.eintosti.buildsystem.manager.InventoryManager;
import com.eintosti.buildsystem.object.world.WorldStatus;
import com.google.common.collect.Sets;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

/**
 * @author einTosti
 */
public class WorldsInventory extends FilteredWorldsInventory implements Listener {

    private final BuildSystem plugin;
    private final InventoryManager inventoryManager;

    public WorldsInventory(BuildSystem plugin) {
        super(plugin, "world_navigator_title", "world_navigator_no_worlds", false,
                Sets.newHashSet(WorldStatus.NOT_STARTED, WorldStatus.IN_PROGRESS, WorldStatus.ALMOST_FINISHED, WorldStatus.FINISHED));

        this.plugin = plugin;
        this.inventoryManager = plugin.getInventoryManager();

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    protected Inventory createInventory(Player player) {
        Inventory inventory = super.createInventory(player);
        if (super.canCreateWorld(player)) {
            addWorldCreateItem(inventory, player);
        }
        return inventory;
    }

    private void addWorldCreateItem(Inventory inventory, Player player) {
        if (!player.hasPermission("buildsystem.createworld")) {
            inventoryManager.addGlassPane(plugin, player, inventory, 49);
            return;
        }
        inventoryManager.addUrlSkull(inventory, 49, plugin.getString("world_navigator_create_world"), "https://textures.minecraft.net/texture/3edd20be93520949e6ce789dc4f43efaeb28c717ee6bfcbbe02780142f716");
    }
}
