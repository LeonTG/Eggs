package com.leontg77.eggs.listeners;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

/**
 * EggHit listener.
 * <p>
 * Class used to listen for an egg hitting something.
 * 
 * @author LeonTG77
 */
public class EggHitListener implements Listener {

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		Projectile proj = event.getEntity();
		
		// check if the projectile is an egg, if not return.
		if (!(proj instanceof Egg)) {
			return;
		}
		
		Random rand = new Random();
		
		// make a list of all the entitytypes
		ArrayList<EntityType> types = new ArrayList<EntityType>();
		
		// Loop over all entity types.
		for (EntityType type : EntityType.values()) {
			// if the current looped type isnt alive AND isn't spawnable, hop over this loop.
			if (!type.isAlive() || !type.isSpawnable()) {
				continue;
			}
			
			// add the loop value to the list.
			types.add(type);
		}
		
		// get a random type out of the list we just created.
		EntityType type = types.get(rand.nextInt(types.size()));
		
		// get the location and world the projectile hit in.
		Location loc = proj.getLocation();
		World world = proj.getWorld();
		
		// spawn the entity in the world and location we got.
		world.spawnEntity(loc, type);
	}
}