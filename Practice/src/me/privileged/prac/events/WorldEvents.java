package me.privileged.prac.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import me.privileged.prac.main.Main;

public class WorldEvents implements Listener{

	static Main plugin;
	public WorldEvents(Main main) {
		plugin = main;
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		event.setCancelled(true);
	}
	
	
}
