package me.bscal.common.capability.healing;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class HealingStorage implements Capability.IStorage<IHealing>
{
	@Override
	public INBT writeNBT(Capability<IHealing> capability, IHealing instance, Direction side)
	{
		return instance.Write();
	}

	@Override
	public void readNBT(Capability<IHealing> capability, IHealing instance, Direction side,
			INBT nbt)
	{
		instance.Read(nbt);
	}
}
