package me.bscal.core.proxy;

import me.bscal.common.capability.CapabilityHandler;
import me.bscal.common.capability.healing.Healing;
import me.bscal.common.capability.healing.HealingStorage;
import me.bscal.common.capability.healing.IHealing;
import me.bscal.common.events.PlayerTickHandler;
import me.bscal.core.Healthy;
import me.bscal.core.register.ItemRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Optional;

public class SideProxy implements IProxy
{
	private MinecraftServer m_server = null;

	SideProxy()
	{
		// Register Registries
		ItemRegistry.Register();

		// Mod Events
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::OnCommonSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::EnqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::ProcessIMC);

		// Forge Events
		MinecraftForge.EVENT_BUS.addListener(this::OnServerAboutToStart);
		MinecraftForge.EVENT_BUS.addListener(this::OnServerStarting);

		MinecraftForge.EVENT_BUS.register(PlayerTickHandler.class);
		MinecraftForge.EVENT_BUS.register(CapabilityHandler.class);
	}

	private void OnCommonSetup(final FMLCommonSetupEvent event)
	{
		CapabilityManager.INSTANCE.register(IHealing.class, new HealingStorage(), Healing::new);
	}

	private void EnqueueIMC(final InterModEnqueueEvent event)
	{
	}

	private void ProcessIMC(final InterModProcessEvent event)
	{
	}

	public void OnServerStarting(final FMLServerStartingEvent event)
	{
		event.getServer()
				.getGameRules()
				.get(GameRules.NATURAL_REGENERATION)
				.set(false, event.getServer());
	}

	private void OnServerAboutToStart(final FMLServerAboutToStartEvent event)
	{
		m_server = event.getServer();
	}

	@Override
	public Optional<PlayerEntity> Player()
	{
		return Optional.empty();
	}

	@Override
	public MinecraftServer GetServer()
	{
		return m_server;
	}

	public static class Client extends SideProxy
	{
		public Client()
		{
			FMLJavaModLoadingContext.get().getModEventBus().addListener(this::OnClientSetup);
		}

		private void OnClientSetup(final FMLClientSetupEvent event)
		{
		}

		@Override
		public Optional<PlayerEntity> Player()
		{
			return Optional.ofNullable(Minecraft.getInstance().player);
		}
	}

	public static class Server extends SideProxy
	{
		public Server()
		{
		}
	}

}
