package me.bscal.core;

import me.bscal.core.proxy.IProxy;
import me.bscal.core.proxy.SideProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Healthy.MOD_ID)
public class Healthy
{
	public static final String MOD_ID = "healthy";
	public static final String MOD_NAME = "Healthy";
	public static final Logger LOGGER = LogManager.getLogger();

	public static Healthy INSTANCE;
	public static IProxy PROXY;

	public Healthy()
	{
		INSTANCE = this;
		PROXY = DistExecutor.safeRunForDist(() -> SideProxy.Client::new,
				() -> SideProxy.Server::new);
	}

}
