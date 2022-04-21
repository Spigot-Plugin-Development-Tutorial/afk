package me.kodysimpson.afk.listeners;

import me.kodysimpson.afk.AFKManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AFKListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        AFKManager.playerJoined(e.getPlayer());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        AFKManager.playerMoved(e.getPlayer()); //update their last movement time
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        AFKManager.playerLeft(e.getPlayer());
    }

}
