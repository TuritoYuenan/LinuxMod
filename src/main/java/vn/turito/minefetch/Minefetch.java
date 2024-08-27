package vn.turito.minefetch;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

public class Minefetch implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("minefetch");

	Command<ServerCommandSource> fetchCommand = context -> {
		ServerCommandSource source = context.getSource();
		ServerPlayerEntity player = source.getPlayer();
		ServerWorld world = source.getWorld();

		String[] lines = {
				"mmmmmmmmmm  " + player.getDisplayName() + "@" + source.getServer(),
				"mmccmmccmm  " + "Seed: " + world.getSeed(),
				"mmmmccmmmm  " + "Time: " + world.getTime(),
				"mmmccccmmm  " + "Position: " + getPlayerPosition(player),
				"mmmcmmcmmm  ",
				"mmmmmmmmmm  ",
		};

		for (String line : lines) {
			source.sendFeedback(() -> Text.literal(line), false);
		}

		return 0;
	};

	/**
	 * @param p Object representing the player
	 * @return Formatted string of the player's position
	 */
	private static String getPlayerPosition(ServerPlayerEntity p) {
		String position = "%d, %d, %d";
		return String.format(position, p.getBlockX(), p.getBlockY(), p.getBlockZ());
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Minefetch mod activated!");

		LiteralArgumentBuilder<ServerCommandSource> command = CommandManager
				.literal("minefetch")
				.executes(this.fetchCommand);

		CommandRegistrationCallback.EVENT
				.register((dispatcher, registryAccess, environment) -> {
					dispatcher.register(command);
				});
	}
}
