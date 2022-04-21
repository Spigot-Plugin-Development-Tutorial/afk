package me.kodysimpson.afk.tasks;

import me.kodysimpson.afk.AFKManager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class MovementChecker implements Runnable {

    @Override
    public void run() {

        System.out.println("CHECKING ALL PLAYER STATUSESWES");

        AFKManager.checkAllStatuses();

    }

}
