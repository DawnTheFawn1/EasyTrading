package io.github.RysingDragon.SimpleTrading.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import io.github.RysingDragon.SimpleTrading.SimpleTrading;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class Config {

	public static File file = new File("config/SimpleTrading.conf");
	public static ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder().setFile(file).build(); 
	public static CommentedConfigurationNode configNode = loader.createEmptyNode(ConfigurationOptions.defaults());
	
	private static Config config = new Config();
	
	public static Config getConfig() {
		return config;
	}
	
	public void setup() {
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			load();
			setDefault();
			save();
		} else {
			load();
		}
		
	}
	
	public void save() {
		try {
			loader.save(configNode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		try {
			configNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDefault() {
		configNode.getNode("pixelmonEnabled").setValue(false).setComment("Set whether or not to use pixelmon offers");
	}
	
	
	
}
