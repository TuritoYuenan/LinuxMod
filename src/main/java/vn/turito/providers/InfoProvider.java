package vn.turito.providers;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class InfoProvider {
	private ServerPlayerEntity player;
	private ServerWorld world;

	private String[] asciiArt = {
			"||||||||||",
			"||..||..||",
			"||||..||||",
			"|||....|||",
			"|||.||.|||",
			"||||||||||",
	};

	public InfoProvider(ServerCommandSource source) {
		this.player = source.getPlayer();
		this.world = source.getWorld();
	}

	/**
	 * Get the name of the player
	 *
	 * @return Name of the player
	 */
	public String getName() {
		return player.getDisplayName().toString();
	}

	/**
	 * Get the position of the player
	 *
	 * @return Position of the player
	 */
	public String getPosition() {
		int x = player.getBlockX();
		int y = player.getBlockY();
		int z = player.getBlockZ();
		return String.format("%d, %d, %d", x, y, z);
	}

	/**
	 * Get the seed of the world
	 *
	 * @return Seed of the world
	 */
	public long getSeed() {
		return world.getSeed();
	}

	/**
	 * Get the current time of the world
	 *
	 * @return Current time of the world
	 */
	public long getTime() {
		return world.getTime();
	}

	/**
	 * Get the ASCII art with the default characters
	 *
	 * @return ASCII art with '|' as background and '.' as foreground
	 */
	public String[] getAsciiArt() {
		return getAsciiArt("|", ".");
	}

	/**
	 * Get the ASCII art with the specified characters
	 *
	 * @param bg Background character
	 * @param fg Foreground character
	 * @return ASCII art with the specified characters
	 */
	public String[] getAsciiArt(String bg, String fg) {
		String[] art = new String[asciiArt.length];

		for (int i = 0; i < asciiArt.length; i++) {
			art[i] = asciiArt[i].replace("|", bg).replace(".", fg);
		}

		return art;
	}
}
