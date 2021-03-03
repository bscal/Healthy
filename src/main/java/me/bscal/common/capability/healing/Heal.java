package me.bscal.common.capability.healing;

import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.FloatNBT;

public class Heal
{

	public boolean instant;
	public boolean finished;

	public float totalHealing = 0;
	public float durationInTicks = 20.0f;
	public float ticksPerHeal = 20.0f;

	public float totalHealingDone;

	public Heal() {}

	public Heal(float totalHealing, float durationInTicks, float ticksPerHeal)
	{
		this.totalHealing = totalHealing;
		this.durationInTicks = durationInTicks;
		this.ticksPerHeal = ticksPerHeal;
	}

	public float ConsumeHealing()
	{
		float healing = 0f;

		if (instant)
		{
			healing = totalHealing;
			finished = true;
			durationInTicks = -1;
		}
		else
		{
			healing = totalHealing / ticksPerHeal;
			Progress();
		}

		totalHealingDone += totalHealing;

		if (durationInTicks < 0 || totalHealingDone > totalHealing)
			finished = true;

		return healing;
	}

	public float HealingPerSec()
	{
		return totalHealing / (durationInTicks * 20.0f);
	}

	public float HealingPerTick()
	{
		return totalHealing / durationInTicks;
	}

	private void Progress()
	{
		durationInTicks -= ticksPerHeal;
	}

	public static Heal Builder(float totalHealing, float durationInTicks, float ticksPerHeal)
	{
		return new Heal(totalHealing, durationInTicks, ticksPerHeal);
	}

	public Heal Instant(boolean isInstant)
	{
		instant = isInstant;
		return this;
	}

	public CompoundNBT Write()
	{
		if (finished)
			return null;

		CompoundNBT data = new CompoundNBT();
		data.put("instant", ByteNBT.valueOf(instant));
		data.put("totalHealing", FloatNBT.valueOf(totalHealing));
		data.put("ticksPerHeal", FloatNBT.valueOf(ticksPerHeal));
		data.put("durationInTicks", FloatNBT.valueOf(durationInTicks));
		data.put("totalHealingDone", FloatNBT.valueOf(totalHealingDone));
		return data;
	}


	public void Read(CompoundNBT data)
	{
		instant = data.getBoolean("instant");
		totalHealing = data.getFloat("totalHealing");
		ticksPerHeal = data.getFloat("ticksPerHeal");
		durationInTicks = data.getFloat("durationInTicks");
		totalHealingDone = data.getFloat("totalHealingDone");
	}

}
