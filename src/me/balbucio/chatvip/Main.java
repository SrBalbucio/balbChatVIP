package me.balbucio.chatvip;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import balbucio.checker.bungeecord.Checker;
import me.balbucio.chatvip.commands.ChatCommand;
import me.balbucio.chatvip.manager.ChatManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Main extends Plugin{
	private static Main instance;
	private File folder = new File("plugins/balbChatVIP");
	private File config = new File(folder, "config.yml");
	private File msgs = new File(folder, "messages.yml");	
	public Configuration configuration, messages;

	public ChatManager chat;
	public Checker checker = new Checker("balbChatVIP", 1.0, this);
	
	public void onEnable() {
		setInstance(this);
		loadConfig();
		BungeeCord.getInstance().pluginManager.registerCommand(this, new ChatCommand());
		chat = new ChatManager(this);
		BungeeCord.getInstance().getConsole().sendMessage(new TextComponent("[BalbucioChatVIP] §2Ativado com sucesso!"));
	}
    public static Main getInstance() {
        return instance;
    }

    private static void setInstance(final Main instance) {
        Main.instance = instance;
    }
    
    public void loadConfig() {
    	try {
			if (!folder.exists()) {
				folder.mkdir();
			}

			if (!config.exists()) {
				Files.copy(this.getResourceAsStream("config.yml"), config.toPath());
			}
			if (!msgs.exists()) {
				Files.copy(this.getResourceAsStream("messages.yml"), msgs.toPath());
			}
			configuration = YamlConfiguration.getProvider(YamlConfiguration.class).load(config);
			messages = YamlConfiguration.getProvider(YamlConfiguration.class).load(msgs);
		} catch (Exception e) {
			e.printStackTrace();
			BungeeCord.getInstance().getConsole()
					.sendMessage("§c[BalbucioChatVIP] §aNão foi possível carregar os arquivos!");
		}
    }

}
