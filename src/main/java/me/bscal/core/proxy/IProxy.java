package me.bscal.core.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

import java.util.Optional;

public interface IProxy
{

	Optional<PlayerEntity> Player();

	MinecraftServer GetServer();

}
