package com.eintosti.buildsystem.event.world;

import com.eintosti.buildsystem.object.world.BuildWorld;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * @author einTosti
 */
public class BuildWorldEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final BuildWorld buildWorld;

    public BuildWorldEvent(BuildWorld buildWorld) {
        this.buildWorld = buildWorld;
    }

    public BuildWorld getBuildWorld() {
        return buildWorld;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}