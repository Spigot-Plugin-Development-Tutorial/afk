package me.kodysimpson.afk;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AFKManager {

    private static final long TIME_TO_AFK = 60000L;

    //The value represents the last time the player moved. If -1, the player is manual AFK.
    private static final HashMap<Player, Long> lastMovement = new HashMap<>();

    //Used to keep track of the last AFK status of the player before lastMovement was updated
    private static final HashMap<Player, Boolean> previousData = new HashMap<>();

    public static void playerMoved(Player player) {
        lastMovement.put(player, System.currentTimeMillis());

        checkPlayerAFKStatus(player);
    }

    public static void playerJoined(Player player) {
        lastMovement.put(player, System.currentTimeMillis());
    }

    public static void playerLeft(Player player) {
        lastMovement.remove(player);
    }

    //If the player has not moved in 5 at least minutes, they are AFK. Or if the value is -1L, they are manual AFK.
    public static boolean isAFK(Player player) {

        if(lastMovement.get(player) != null){
            long timeElapsed = System.currentTimeMillis() - lastMovement.get(player);

            return timeElapsed >= TIME_TO_AFK || lastMovement.get(player) == -1L;
        }

        lastMovement.put(player, System.currentTimeMillis());

        return false;
    }

    //Allow a player to manually set their AFK status. Return true if the player is now AFK.
    public static boolean toggleAFK(Player player) {

        if(isAFK(player)) {
            previousData.put(player, false);
            lastMovement.put(player, System.currentTimeMillis());
            return false;
        }

        previousData.put(player, true);
        lastMovement.put(player, -1L);
        return true;
    }

    public static void checkAllStatuses(){

        for(Map.Entry<Player, Long> entry : lastMovement.entrySet()){
            checkPlayerAFKStatus(entry.getKey());
        }

    }

    //Check the players current AFK status to either mark them as AFK or not based on data
    public static void checkPlayerAFKStatus(Player player){

        if(lastMovement.containsKey(player)){
            long whenLastMoved = lastMovement.get(player);
            long timeElapsed = System.currentTimeMillis() - whenLastMoved;
            boolean isAFK = whenLastMoved == -1L || timeElapsed >= AFKManager.TIME_TO_AFK;

            //See if the player was already marked in the previous data. If they were,
            // we looked at them already and their status is important to check for toggling.
            if (previousData.containsKey(player)) {

                boolean wasAFK = previousData.get(player);

                //Check to see if the AFK status was toggled or not based on previous data.
                if (wasAFK && !isAFK) {
                    previousData.put(player, false);
                    AFKManager.announceAFK(player, false);
                    player.sendMessage("You are no longer AFK.");
                } else if (!wasAFK && isAFK) {
                    previousData.put(player, true);
                    AFKManager.announceAFK(player, true);
                    player.sendMessage("You are now AFK.");
                }

            }else{
                //Update the previous data so we know if they were AFK last time we checked or not
                previousData.put(player, isAFK);
            }
        }

    }

    public static void announceAFK(Player player, boolean isAFK){

        if(isAFK){
            Bukkit.getServer().broadcastMessage(player.getDisplayName() + " is now AFK.");
        }else{
            Bukkit.getServer().broadcastMessage(player.getDisplayName() + " is no longer AFK.");
        }

    }

}
