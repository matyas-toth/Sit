package me.reigniteh.sit.entities;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ChairEntity {

    private ArmorStand chair;

    public void spawn(World world, Location location) {

        this.chair = (ArmorStand) world.spawnEntity(location.add(0, -1.6, 0), EntityType.ARMOR_STAND);

        this.chair.setGravity(false);
        this.chair.setVisible(false);
        this.chair.setInvulnerable(false);

    }

    public void deSpawn() {

        this.chair.remove();

    }

    public ArmorStand getChair() {

        return this.chair;

    }

    public void attachPlayer(Player player) {

        if(this.chair != null) {

            this.chair.addPassenger(player);

        }

    }

}
