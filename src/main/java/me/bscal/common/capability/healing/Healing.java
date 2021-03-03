package me.bscal.common.capability.healing;

import me.bscal.core.Healthy;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Healing implements IHealing
{

	private float m_currentHealing;
	private float m_lastHealing;

	private List<Heal> m_heals = new ArrayList<>();

	@Override
	public void Put(Heal heal)
	{
		m_heals.add(heal);
	}

	@Override
	public void Set(Heal heal)
	{
		m_heals.clear();
		m_heals.add(heal);
	}

	@Override
	public float Consume()
	{
		m_lastHealing = m_currentHealing;
		m_currentHealing = 0;

		for (Iterator<Heal> iter = m_heals.iterator(); iter.hasNext();)
		{
			Heal next = iter.next();

			if (Healthy.PROXY.GetServer().getTickCounter() % next.ticksPerHeal == 0)
			{
				m_currentHealing += next.ConsumeHealing();

				if (next.finished)
					iter.remove();
			}
		}

		return m_currentHealing;
	}

	@Override
	public float GetMaxFromRegen()
	{
		return 0;
	}

	@Override
	public float GetHealingFromComfort()
	{
		return 0;
	}

	@Override
	public float GetHealingFromFood()
	{
		return 0;
	}

	@Override
	public float GetTotalHealing()
	{
		return 0;
	}

	@Override
	public List<Heal> GetHeals()
	{
		return m_heals;
	}

	@Override
	public ListNBT Write()
	{
		ListNBT data = new ListNBT();

		for (int i = 0; i < m_heals.size(); i++)
			data.addNBTByIndex(i, m_heals.get(i).Write());

		return data;
	}


	@Override
	public void Read(INBT nbt)
	{
		if (nbt instanceof ListNBT)
		{
			ListNBT data = (ListNBT)nbt;
			m_heals.clear();
			for (int i = 0; i < data.size(); i++)
			{
				Heal heal = new Heal();
				heal.Read(data.getCompound(i));
				Put(heal);
			}
		}
	}

}
