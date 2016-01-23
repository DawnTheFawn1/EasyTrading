package io.github.RysingDragon.SimpleTrading;

import java.nio.file.Path;

import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

import io.github.RysingDragon.SimpleTrading.config.Config;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(id = "simpletrading", name = "SimpleTrading", version = "0.1")
public class SimpleTrading {

	public static SimpleTrading instance;
	
	@Inject
	@DefaultConfig(sharedRoot=true)
	private Path defaultConfig;
	
	@Inject
	@DefaultConfig(sharedRoot=true)
	private ConfigurationLoader<CommentedConfigurationNode> loader;
	
	@Inject
	@ConfigDir(sharedRoot=true)
	private Path configDir;
	
	@Listener
	public void onPreInit(GamePreInitializationEvent event) {
		instance = this;
		Config.getConfig().setup();
	}
	
	public Path getConfigPath() {
		return defaultConfig;
	}
	
}
