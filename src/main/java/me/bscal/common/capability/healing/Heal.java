package me.bscal.common.capability.healing;

import me.bscal.core.Healthy;
import net.minecraft.nbt.CompoundNBT;

public class Heal
{

	public static final float TICKS_PER_SEC = 20.0f;

	public boolean instant;
	public boolean finished;

	public float totalHealing;
	public int durationInTicks;
	public int ticksPerHeal;
	public float healingPerTick;

	public float totalHealingDone;

	public Heal()
	{
	}

	public Heal(float totalHealing, int durationInTicks, int ticksPerHeal)
	{
		this.totalHealing = totalHealing;
		this.durationInTicks = durationInTicks;
		this.ticksPerHeal = ticksPerHeal;
		this.healingPerTick = totalHealing / (durationInTicks / ticksPerHeal);
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
			healing = healingPerTick;
			Progress();
		}

		totalHealingDone += healing;

		if (durationInTicks <= 0f)
			finished = true;

		if (Healthy.DEBUG)
		{
			Healthy.LOGGER.debug("======= Heal Trigger =======");
			Healthy.LOGGER.debug("  Healed: " + healing);
			Healthy.LOGGER.debug("  Duration: " + durationInTicks + " ticks");
			Healthy.LOGGER.debug("  Healing Done: " + totalHealingDone);
			Healthy.LOGGER.debug("  Finished: " + finished);
		}

		return healing;
	}

	private void Progress()
	{
		durationInTicks -= ticksPerHeal;
	}

	public static Heal Builder(float totalHealing, int durationInTicks, int ticksPerHeal)
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
		data.putBoolean("instant", instant);
		data.putFloat("totalHealing", totalHealing);
		data.putInt("ticksPerHeal", ticksPerHeal);
		data.putInt("durationInTicks", durationInTicks);
		data.putFloat("durationInTicks", healingPerTick);
		data.putFloat("totalHealingDone", totalHealingDone);
		return data;
	}

	public void Read(CompoundNBT data)
	{
		instant = data.getBoolean("instant");
		totalHealing = data.getFloat("totalHealing");
		ticksPerHeal = data.getInt("ticksPerHeal");
		durationInTicks = data.getInt("durationInTicks");
		healingPerTick = data.getFloat("healingPerTick");
		totalHealingDone = data.getFloat("totalHealingDone");
	}

}
