package me.bscal.core;

import me.bscal.core.register.HItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("healthy")
public class Healthy
{
	public static final String MOD_ID = "healthy";
	public static final Logger LOGGER = LogManager.getLogger();

	public Healthy()
	{
		// Register Registries
		HItemRegistry.Register();

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::Setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::EnqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::ProcessIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::DoClientStuff);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void Setup(final FMLCommonSetupEvent event)
	{
	}

	private void EnqueueIMC(final InterModEnqueueEvent event)
	{
	}

	private void ProcessIMC(final InterModProcessEvent event)
	{
	}

	private void DoClientStuff(final FMLClientSetupEvent event)
	{
	}

	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void OnServerStarting(FMLServerStartingEvent event)
	{
	}

}
