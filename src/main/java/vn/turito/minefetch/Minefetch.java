package vn.turito.minefetch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import vn.turito.commands.FetchCommand;

public class Minefetch implements ModInitializer {
	/*
	 * This logger is used to write text to the console and the log file. It is
	 * considered best practice to use your mod id as the logger's name. That way,
	 * it's clear which mod wrote info, warnings, and errors.
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger("minefetch");

	/*
	 * This code runs as soon as Minecraft is in a mod-load-ready state.
	 * However, some things (like resources) may still be uninitialized.
	 * Proceed with mild caution.
	 */
	@Override
	public void onInitialize() {
		LOGGER.info("Initialising Minefetch");

		CommandRegistrationCallback.EVENT
				.register((dispatcher, registryAccess, environment) -> {
					dispatcher.register(CommandManager
							.literal("minefetch").executes(new FetchCommand()));
				});

		LOGGER.info("Initialised Minefetch");
	}
}
