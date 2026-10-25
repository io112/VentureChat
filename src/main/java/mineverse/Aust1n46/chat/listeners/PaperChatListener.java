package mineverse.Aust1n46.chat.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import mineverse.Aust1n46.chat.MineverseChat;
import mineverse.Aust1n46.chat.utilities.Format;

/**
 * Stops Paper from sending its own copy of a chat message that VentureChat already handled.
 *
 * VentureChat cancels {@link org.bukkit.event.player.AsyncPlayerChatEvent} and sends the formatted message itself.
 * Paper carries that cancellation into {@link AsyncChatEvent}, but a plugin listening after VentureChat can undo it,
 * and players then see the vanilla "&lt;name&gt; message" line as well. Emptying the viewers keeps the vanilla message
 * from reaching anyone while leaving the cancelled state alone for listeners such as chat bubbles.
 */
public class PaperChatListener implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onAsyncChat(AsyncChatEvent event) {
		if (!event.isCancelled() && MineverseChat.getInstance().getConfig().getString("loglevel", "info").equals("debug")) {
			Bukkit.getConsoleSender().sendMessage(Format.FormatStringAll(
					"&8[&eVentureChat&8]&e - Chat event from " + event.getPlayer().getName() + " was uncancelled by another plugin, hiding the vanilla message"));
		}
		event.viewers().clear();
	}
}
