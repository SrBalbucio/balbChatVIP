package me.balbucio.chatvip.commands;

import java.util.ArrayList;

import me.balbucio.chatvip.Main;
import me.balbucio.chatvip.manager.ChatManager;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class ChatCommand extends Command {
	
	public ChatCommand() {
		super("cv", null, "chatvip", "vipchat");
	}

	private Main m = Main.getInstance();
	private Configuration msg = m.messages;

	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {
		String prefix = msg.getString("prefix").replace("&", "§");
		String chatprefix = msg.getString("chatprefix").replace("&", "§");
		if (!sender.hasPermission("chatvip.use")) {
			sender.sendMessage(msg.getString("nopermission").replace("&", "§").replace("{prefix}", prefix));
			return;
		}

		if (args.length == 0) {
			sender.sendMessage(msg.getString("nullmessage").replace("&", "§").replace("{prefix}", prefix));
			return;
		}

		String message = "";
		for (int i = 1; i < args.length; i++) {
			if (message.equalsIgnoreCase("")) {
				message = args[i];
			} else {
				message = message + " " + args[i];
			}
		}
		if(sender.hasPermission("chatvip.translatecolor")){
			message = message.replace("&", "§");
		}
		ChatManager.get().sendMessage((ProxiedPlayer) sender, message);
	}

}
