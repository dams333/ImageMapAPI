package ch.dams333.imagemap.commands;

import ch.dams333.imagemap.Plugin;
import ch.dams333.imagemap.core.TaskRenderImage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMap implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("map")){

            if(sender instanceof Player){

                if(args.length == 1){

                    if(args[0].equalsIgnoreCase("stop")){

                        final Player p = (Player) sender;
                        Plugin.mapsToGive.remove(p);
                        p.sendMessage("§aVous n'allez plus poser de maps");
                        return true;

                    }

                }

                if(args.length == 2){

                    if(args[0].equalsIgnoreCase("render")){

                        final Player p = (Player) sender;
                        final String path = args[1];

                        new TaskRenderImage(p, path).runTaskAsynchronously(Plugin.INSTANCE);

                    }

                }else {
                    sender.sendMessage(ChatColor.RED + "Nombre d'arguments de la commande incorrect");
                }

            }

            return true;
        }

        return false;
    }
}
