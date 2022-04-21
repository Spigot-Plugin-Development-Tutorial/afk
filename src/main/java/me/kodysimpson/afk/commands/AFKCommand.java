package me.kodysimpson.afk.commands;

import me.kodysimpson.afk.AFKManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AFKCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Allow a player to go AFK manually.
        if(sender instanceof Player){
            Player player = (Player) sender;

            if (AFKManager.toggleAFK(player)){
                player.sendMessage("You are now AFK.");
                AFKManager.announceAFK(player, true);
            }else{
                player.sendMessage("You are no longer AFK.");
                AFKManager.announceAFK(player, false);
            }

        }

        return true;
    }
}
