package me.bscal.core;

import me.bscal.common.capability.CapabilityHandler;
import me.bscal.common.capability.healing.Healing;
import me.bscal.common.capability.healing.HealingStorage;
import me.bscal.common.capability.healing.IHealing;
import me.bscal.common.events.PlayerTickHandler;
import me.bscal.core.register.ItemRegistry;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
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
@Mod(Healthy.MOD_ID)
public class Healthy
{
	public static final String MOD_ID = "healthy";
	public static final String MOD_NAME = "Healthy";
	public static final Logger LOGGER = LogManager.getLogger();

	public Healthy()
	{
		// Register Registries
		ItemRegistry.Register();

		CapabilityManager.INSTANCE.register(IHealing.class, new HealingStorage(), Healing::new);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::OnCommonSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::EnqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::ProcessIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::OnClientSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::OnServerStarting);

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new PlayerTickHandler());
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
	}

	private void OnCommonSetup(final FMLCommonSetupEvent event)
	{
	}

	private void EnqueueIMC(final InterModEnqueueEvent event)
	{
	}

	private void ProcessIMC(final InterModProcessEvent event)
	{
	}

	private void OnClientSetup(final FMLClientSetupEvent event)
	{
	}

	public void OnServerStarting(final FMLServerStartingEvent event)
	{
		event.getServer().getGameRules().get(GameRules.NATURAL_REGENERATION).set(false, event.getServer());

	}

}
