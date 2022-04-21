package me.kodysimpson.afk.commands;

import me.kodysimpson.afk.AFKManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IsAFKCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Allow a player to check to see if they are AFK or someone else
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(args.length == 0){
                //Check to see if the player is AFK
                if(AFKManager.isAFK(player)){
                    player.sendMessage("You are AFK");
                }else{
                    player.sendMessage("You are not AFK");
                }
            }else{
                //Check to see if the player is AFK
                if(AFKManager.isAFK(Bukkit.getPlayerExact(args[0]))){
                    player.sendMessage(args[0] + " is AFK");
                }else{
                    player.sendMessage(args[0] + " is not AFK");
                }
            }

        }

        return true;
    }
}
