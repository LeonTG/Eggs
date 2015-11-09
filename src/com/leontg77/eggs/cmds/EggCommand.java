package com.leontg77.eggs.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

import com.leontg77.eggs.Main;
import com.leontg77.eggs.listeners.ChickenDeathListener;
import com.leontg77.eggs.listeners.EggHitListener;

/**
 * Egg command.
 * <p> 
 * Command used to enable or disable the scenario.
 * 
 * @author LeonTG77
 */
public class EggCommand implements CommandExecutor {
	private static final String PERMISSION = "egg.manage";
	public static final String PREFIX = "§eEggs §8» §f";
	
	public static boolean enabled = false;
	
	private ChickenDeathListener chicken = new ChickenDeathListener();
	private EggHitListener egg = new EggHitListener();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// check if the have permission, if not send them a message and return.
		if (!sender.hasPermission(PERMISSION)) {
			sender.sendMessage(PREFIX + ChatColor.RED + "You don't have access to this.");
			return true;
		}
		
		// check if they typed anything else than the command itself, if not send usage and return.
		if (args.length == 0) {
			sender.sendMessage(PREFIX + "Usage: /egg <enable|disable>");
			return true;
		}
		
		// check if they typed /egg enable, if so do the command.
		if (args[0].equalsIgnoreCase("enable")) {
			// check if the scenario is enabled, if not tell them so and return.
			if (enabled) {
				sender.sendMessage(PREFIX + "Eggs is already enabled.");
				return true;
			}
			
			// send them a message and set enabled to be true
			sender.sendMessage(PREFIX + "Eggs has been enabled.");
			enabled = true;
			
			// register the eventhandles for the scenario.
			PluginManager manager = Bukkit.getPluginManager();
			manager.registerEvents(chicken, Main.plugin);
			manager.registerEvents(egg, Main.plugin);
			return true;
		}

		// check if they typed /egg enable, if so do the command.
		if (args[0].equalsIgnoreCase("disable")) {
			// check if the scenario wasn't enabled, if not tell them so and return.
			if (!enabled) {
				sender.sendMessage(PREFIX + "Eggs is not enabled.");
				return true;
			}

			// send them a message and set enabled to be false
			sender.sendMessage(PREFIX + "Eggs has been disabled.");
			enabled = false;
			
			// unregister the eventhandles for the scenario.
			HandlerList.unregisterAll(chicken);
			HandlerList.unregisterAll(egg);
			return true;
		}
		
		// they didn't type enable or disable, send usage.
		sender.sendMessage(PREFIX + "Usage: /egg <enable|disable>");
		return true;
	}
}