package com.leontg77.eggs.listeners;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

/**
 * ChickenDeath listener
 * <p> 
 * Class used to listen for the entity death event and make the chicken drop an egg with a 5% chance.
 * 
 * @author LeonTG77
 */
public class ChickenDeathListener implements Listener {

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		Entity entity = event.getEntity();
		
		// check if the entity was a chicken, if not return.
		if (!(entity instanceof Chicken)) {
			return;
		}
		
		Random rand = new Random();
		double chance = 0.05;
		
		// check if the random value is more than the chance, if so return.
		if (rand.nextDouble() > chance) {
			return;
		}
		
		// add an egg to the drops of the chicken.
		event.getDrops().add(new ItemStack(Material.EGG));
	}
}