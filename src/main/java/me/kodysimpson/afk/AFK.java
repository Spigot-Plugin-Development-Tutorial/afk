package me.kodysimpson.afk;

import me.kodysimpson.afk.commands.AFKCommand;
import me.kodysimpson.afk.commands.IsAFKCommand;
import me.kodysimpson.afk.listeners.AFKListener;
import me.kodysimpson.afk.tasks.MovementChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AFK extends JavaPlugin {

    //Goal: Figure out when a player has not moved in a while.
    //Game Plan:
    //1. Create a new class called AFKManager.
    //2. The class has a hashmap of players and when they last triggered the PlayerMoveEvent
    //3. Have a task asynchronously running that reports a player as AFK when they have not moved in a while.
    //4. Have a way for players to manually become AFK.

    @Override
    public void onEnable() {
        // Plugin startup logic

        //register the listener
        getServer().getPluginManager().registerEvents(new AFKListener(), this);

        getCommand("isafk").setExecutor(new IsAFKCommand());
        getCommand("afk").setExecutor(new AFKCommand());

        //Schedule the async task for movement checking
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new MovementChecker(), 0L, 30 * 20L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
