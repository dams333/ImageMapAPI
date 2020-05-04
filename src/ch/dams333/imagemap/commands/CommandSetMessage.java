package ch.dams333.imagemap.commands;

import ch.dams333.imagemap.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetMessage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            if(args.length >= 1){

                StringBuilder sb = new StringBuilder();
                for(String msg : args){
                    sb.append(msg + " ");
                }
                String message = sb.toString();
                if(Plugin.msgToSave.containsKey(sender)){
                    Plugin.msgToSave.remove(sender);
                }
                Plugin.msgToSave.put((Player) sender, message);
                sender.sendMessage(ChatColor.GREEN + "Message enregistré. Clique droit sur une des maps");

            }

        }

        return false;
    }
}
