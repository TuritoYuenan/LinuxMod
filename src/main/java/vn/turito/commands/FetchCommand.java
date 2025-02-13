package vn.turito.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import vn.turito.providers.InfoProvider;

public class FetchCommand implements Command<ServerCommandSource> {
	@Override
	public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
		ServerCommandSource source = context.getSource();
		InfoProvider infoProvider = new InfoProvider(source);

		String[] asciiArt = infoProvider.getAsciiArt("m", "c");

		String[] lines = {
				infoProvider.getName() + "@" + source.getServer(),
				"Seed: " + infoProvider.getSeed(),
				"Time: " + infoProvider.getTime(),
				"Position: " + infoProvider.getPosition(),
				"",
				"",
		};

		for (int i = 0; i < asciiArt.length; i++) {
			final int idx = i;
			source.sendFeedback(() -> Text.literal(asciiArt[idx] + " " + lines[idx]), false);
		}

		return 0;
	}
}
