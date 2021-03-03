package me.bscal.common.capability.healing;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class HealingProvider implements ICapabilitySerializable<INBT>
{

	@CapabilityInject(IHealing.class)
	public static final Capability<IHealing> HEALING_CAP = null;

	private IHealing m_instance = HEALING_CAP.getDefaultInstance();

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		return HEALING_CAP.orEmpty(cap, LazyOptional.of(() -> m_instance));
	}

	@Override
	public INBT serializeNBT()
	{
		return HEALING_CAP.getStorage().writeNBT(HEALING_CAP, m_instance, null);
	}

	@Override
	public void deserializeNBT(INBT nbt)
	{
		HEALING_CAP.getStorage().readNBT(HEALING_CAP, m_instance, null, nbt);
	}
}
