package me.balbucio.chatvip.manager;

import java.util.ArrayList;
import java.util.List;

import me.balbucio.chatvip.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

public class ChatManager {

	private List<ProxiedPlayer> mutedPlayers = new ArrayList<>();
	private List<ProxiedPlayer> naoIncomodar = new ArrayList<>();
	private Main m;
	private Configuration msg, cnf;
	private String prefix;
	private String chatprefix;

	private boolean mute;
	private static ChatManager chat;

	public ChatManager(Main m) {
		setChat(this);
		this.m = m;
		msg = m.messages;
		cnf = m.configuration;
		prefix = msg.getString("prefix").replace("&", "§");
		chatprefix = msg.getString("chatprefix").replace("&", "§");
	}
	
	

	public static ChatManager get() {
		return chat;
	}



	public static void setChat(ChatManager chat) {
		ChatManager.chat = chat;
	}



	@SuppressWarnings("deprecation")
	public void sendMessage(ProxiedPlayer player, String message) {
		if (mutedPlayers.contains(player)) {
			player.sendMessage(msg.getString("mute.voce").replace("&", "§").replace("{prefix}", prefix)
					.replace("{chatprefix}", chatprefix));
			return;
		}
		if (hasBlockedWord(message) > 0) {
			player.sendMessage(msg.getString("blockmsg").replace("&", "§").replace("{prefix}", prefix)
					.replace("{chatprefix}", chatprefix)
					.replace("{motivo}", msg.getString("motivos.badword").replace("&", "§")));
			return;
		}
		if (mute) {
			player.sendMessage(msg.getString("mute.all").replace("&", "§").replace("{prefix}", prefix)
					.replace("{chatprefix}", chatprefix));
		}
		String name = m.configuration.getBoolean("customname") ? player.getDisplayName() : player.getName();
		for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
			if (!naoIncomodar.contains(all) && all.hasPermission("chatvip.view")) {
				all.sendMessage(msg.getString("message").replace("&", "§").replace("{prefix}", prefix)
						.replace("{chatprefix}", chatprefix).replace("{message}", message).replace("{user}", name));
			}
		}
	}

	private int hasBlockedWord(String msg) {
		int quantidade = 0;
		for (String blocked : cnf.getStringList("blockedwords")) {
			if (msg.contains(blocked)) {
				quantidade++;
			}
		}
		return quantidade;
	}
}
