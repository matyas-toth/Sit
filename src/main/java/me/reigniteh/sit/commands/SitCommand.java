package me.reigniteh.sit.commands;

import me.reigniteh.sit.Sit;
import me.reigniteh.sit.entities.ChairEntity;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class SitCommand implements CommandExecutor, Listener {

    private static final ArrayList<Player> sittingPlayers = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) { return true; }

        Player p = (Player) sender;

        if(!p.hasPermission("sit.command")) { return true; }
        if(!p.isOnGround()) { return true; }
        if(sittingPlayers.contains(p)) { return true; }

        sittingPlayers.add(p);

        Location loc = p.getLocation();
        World world = loc.getWorld();

        ChairEntity chair = new ChairEntity();

        chair.spawn(world, loc);
        chair.attachPlayer(p);

        return true;

    }

    @EventHandler
    public void onDismount(EntityDismountEvent e) {

        if(!(e.getEntity() instanceof Player)) { return; }

        Player p = (Player) e.getEntity();

        if(!(e.getDismounted() instanceof ArmorStand)) { return; }

        if(sittingPlayers.contains(p)) {

            e.getDismounted().remove();

            sittingPlayers.remove(p);

            Location loc = p.getLocation();
            loc.add(0,1,0);

            p.teleport(loc);


        }

    }



}
