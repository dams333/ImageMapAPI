package ch.dams333.imagemap;

import ch.dams333.imagemap.helpers.PosterHelper;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.UUID;

public class ListenerClass implements Listener {
    Plugin main;
    public ListenerClass(Plugin plugin) {
        this.main = plugin;
    }

    @EventHandler
    public void rightClickOnItemFrame(PlayerInteractEntityEvent e){

        if(e.getRightClicked() instanceof ItemFrame){
            Player p = e.getPlayer();
            ItemFrame itemFrame = (ItemFrame) e.getRightClicked();

            if(!Plugin.mapsToGive.containsKey(p) && PosterHelper.readUUIDOfItemFrame(itemFrame) != null){
                e.setCancelled(true);
            }

            if(!Plugin.mapsToGive.containsKey(p) && PosterHelper.readUUIDOfItemFrame(itemFrame) != null && !Plugin.msgToSave.containsKey(p) && PosterHelper.getMsgFromUUID(PosterHelper.readUUIDOfItemFrame(itemFrame)) != null){

                String message = PosterHelper.getMsgFromUUID(PosterHelper.readUUIDOfItemFrame(itemFrame));
                String message2 = null;
                if(message.startsWith("http://") || message.startsWith("https://") || message.startsWith("www.")){
                    message2 = message;
                }else{
                    message2 = message.replaceAll("&", "§");
                }

                p.sendMessage(message2);
                e.setCancelled(true);

            }

            if(!Plugin.mapsToGive.containsKey(p) && PosterHelper.readUUIDOfItemFrame(itemFrame) != null && Plugin.msgToSave.containsKey(p)){

                PosterHelper.saveMessageForUUID(PosterHelper.readUUIDOfItemFrame(itemFrame), Plugin.msgToSave.get(p));
                Plugin.msgToSave.remove(p);
                p.sendMessage(ChatColor.GREEN + "Message défini");
                e.setCancelled(true);

            }

            if(Plugin.mapsToGive.containsKey(p) && Plugin.rows.containsKey(p) && Plugin.cols.containsKey(p)){

                int row = Plugin.rows.get(p);
                int cols = Plugin.cols.get(p);

                int cols2 = cols;

                int index = 0;

                UUID uuid = UUID.randomUUID();

                if(itemFrame.getFacing() == BlockFace.NORTH){

                    Location loc = itemFrame.getLocation();

                        itemFrame.setItem(new ItemStack(Material.MAP, 1, Plugin.mapsToGive.get(p).get(index)));

                        PosterHelper.writeItemFrameInConfig(itemFrame, uuid);

                        cols2 = cols2 - 1;
                        Double saveX = loc.getX();
                        loc.setX(loc.getX() - 1);
                        while (cols2 > 0){

                            Collection<Entity> entitiesList = loc.getWorld().getNearbyEntities(loc, 0, 0, 0);

                            if(entitiesList.size() >= 1) {

                                for (Entity entity : loc.getWorld().getNearbyEntities(loc, 0, 0, 0)) {
                                    if (entity instanceof ItemFrame) {
                                        index = index + 1;
                                        ItemFrame itF = (ItemFrame) entity;
                                        itF.setItem(new ItemStack(Material.MAP, 1, Plugin.mapsToGive.get(p).get(index)));

                                        PosterHelper.writeItemFrameInConfig(itF, uuid);

                                        loc.setX(loc.getX() - 1);
                                        cols2 = cols2 - 1;
                                        if (cols2 == 0) {
                                            if (row > 1) {
                                                loc.setY(loc.getY() - 1);
                                                loc.setX(saveX);
                                                cols2 = cols;
                                                row = row - 1;
                                            } else {
                                                e.setCancelled(true);
                                                return;
                                            }
                                        }
                                    } else {
                                        p.sendMessage("§cPas assez d'item frames");
                                        e.setCancelled(true);
                                        return;
                                    }

                                }
                            }else {
                                p.sendMessage("§cPas assez d'item frames");
                                e.setCancelled(true);
                                return;
                            }
                        }

                }
                if(itemFrame.getFacing() == BlockFace.EAST){

                    Location loc = itemFrame.getLocation();

                    itemFrame.setItem(new ItemStack(Material.MAP, 1, Plugin.mapsToGive.get(p).get(index)));

                    PosterHelper.writeItemFrameInConfig(itemFrame, uuid);

                    cols2 = cols2 - 1;
                    Double saveZ = loc.getZ();
                    loc.setZ(loc.getZ() - 1);
                    while (cols2 > 0){

                        Collection<Entity> entitiesList = loc.getWorld().getNearbyEntities(loc, 0, 0, 0);

                        if(entitiesList.size() >= 1){

                        for(Entity entity : loc.getWorld().getNearbyEntities(loc, 0, 0, 0)){
                            if(entity instanceof ItemFrame){
                                index = index + 1;
                                ItemFrame itF = (ItemFrame) entity;
                                itF.setItem(new ItemStack(Material.MAP, 1, Plugin.mapsToGive.get(p).get(index)));

                                PosterHelper.writeItemFrameInConfig(itF, uuid);

                                loc.setZ(loc.getZ() - 1);
                                cols2 = cols2 - 1;
                                if(cols2 == 0){
                                    if(row > 1) {
                                        loc.setY(loc.getY() - 1);
                                        loc.setZ(saveZ);
                                        cols2 = cols;
                                        row = row - 1;
                                    }else {
                                        e.setCancelled(true);
                                        return;
                                    }
                                }
                            }else {
                                p.sendMessage("§cPas assez d'item frames");
                                e.setCancelled(true);
                                return;
                            }

                        }

                        }else {
                            p.sendMessage("§cPas assez d'item frames");
                            e.setCancelled(true);
                            return;
                        }

                    }

                }
                if(itemFrame.getFacing() == BlockFace.WEST){


                    Location loc = itemFrame.getLocation();

                    itemFrame.setItem(new ItemStack(Material.MAP, 1, Plugin.mapsToGive.get(p).get(index)));

                    PosterHelper.writeItemFrameInConfig(itemFrame, uuid);

                    cols2 = cols2 - 1;
                    Double saveZ = loc.getZ();
                    loc.setZ(loc.getZ() + 1);
                    while (cols2 > 0){

                        Collection<Entity> entitiesList = loc.getWorld().getNearbyEntities(loc, 0, 0, 0);

                        if(entitiesList.size() >= 1){

                        for(Entity entity : loc.getWorld().getNearbyEntities(loc, 0, 0, 0)){
                            if(entity instanceof ItemFrame){
                                index = index + 1;
                                ItemFrame itF = (ItemFrame) entity;
                                itF.setItem(new ItemStack(Material.MAP, 1, Plugin.mapsToGive.get(p).get(index)));

                                PosterHelper.writeItemFrameInConfig(itF, uuid);

                                loc.setZ(loc.getZ() + 1);
                                cols2 = cols2 - 1;
                                if(cols2 == 0){
                                    if(row > 1) {
                                        loc.setY(loc.getY() - 1);
                                        loc.setZ(saveZ);
                                        cols2 = cols;
                                        row = row - 1;
                                    }else {
                                        e.setCancelled(true);
                                        return;
                                    }
                                }
                            }else {
                                p.sendMessage("§cPas assez d'itemframes");
                                e.setCancelled(true);
                                return;
                            }

                        }

                        }else {
                            p.sendMessage("§cPas assez d'item frames");
                            e.setCancelled(true);
                            return;
                        }

                    }

                }
                if(itemFrame.getFacing() == BlockFace.SOUTH){

                    Location loc = itemFrame.getLocation();

                    itemFrame.setItem(new ItemStack(Material.MAP, 1, Plugin.mapsToGive.get(p).get(index)));

                    PosterHelper.writeItemFrameInConfig(itemFrame, uuid);

                    cols2 = cols2 - 1;
                    Double saveX = loc.getX();
                    loc.setX(loc.getX() + 1);
                    while (cols2 > 0){

                        Collection<Entity> entitiesList = loc.getWorld().getNearbyEntities(loc, 0, 0, 0);

                        if(entitiesList.size() >= 1){

                        for(Entity entity : loc.getWorld().getNearbyEntities(loc, 0, 0, 0)){
                            if(entity instanceof ItemFrame){
                                index = index + 1;
                                ItemFrame itF = (ItemFrame) entity;
                                itF.setItem(new ItemStack(Material.MAP, 1, Plugin.mapsToGive.get(p).get(index)));

                                PosterHelper.writeItemFrameInConfig(itF, uuid);

                                loc.setX(loc.getX() + 1);
                                cols2 = cols2 - 1;
                                if(cols2 == 0){
                                    if(row > 1) {
                                        loc.setY(loc.getY() - 1);
                                        loc.setX(saveX);
                                        cols2 = cols;
                                        row = row - 1;
                                    }else {
                                        e.setCancelled(true);
                                        return;
                                    }
                                }
                            }else {
                                p.sendMessage("§cPas assez d'item frames");
                                e.setCancelled(true);
                                return;
                            }

                        }

                        }else {
                            p.sendMessage("§cPas assez d'item frames");
                            e.setCancelled(true);
                            return;
                        }

                    }

                }

           }

        }

    }

}
