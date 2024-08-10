package vn.turito.minefetch;

import com.mojang.brigadier.Command;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

/**
 * Main class for Minefetch mod
 */
class Minefetch implements ModInitializer {
	Command<ServerCommandSource> fetchCommand = context -> {
		ServerCommandSource source = context.getSource();
		ServerPlayerEntity player = source.getPlayer();

		String[] lines = {
			"mmmmmmmmmm  " + player.getDisplayName() + "@" + source.getServer(),
			"mmccmmccmm  " + "Seed: " + source.getWorld().getSeed(),
			"mmmmccmmmm  " + "Time: " + source.getWorld().getTime(),
			"mmmccccmmm  " + "Position: " + getPlayerPosition(player),
			"mmmcmmcmmm  ",
			"mmmmmmmmmm  ",
		};

		for (String i : lines) {
			source.sendFeedback(new LiteralText(i), false);
		}

		return 0;
	};

	/**
	 * @param p Object representing the player
	 * @return Formatted string of the player's position
	 */
	String getPlayerPosition(ServerPlayerEntity p) {
		String position = "%d, %d, %d";
		return String.format(position, p.getBlockX(), p.getBlockY(), p.getBlockZ());
	}

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) ->
			dispatcher.register(
				CommandManager.literal("minefetch").executes(this.fetchCommand)
			)
		);
	}
}
