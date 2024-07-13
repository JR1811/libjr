package net.shirojr.libjr;

import net.fabricmc.api.ModInitializer;

import net.shirojr.libjr.logger.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LibJR implements ModInitializer {
	public static final String MODID = "libjr";

	@Override
	public void onInitialize() {
		LoggerUtil.devLogger("Loaded LibJR in a development environment");
	}
}