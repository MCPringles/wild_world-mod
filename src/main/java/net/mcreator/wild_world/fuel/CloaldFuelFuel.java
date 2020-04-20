
package net.mcreator.wild_world.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

import net.mcreator.wild_world.item.CloaldItemItem;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class CloaldFuelFuel extends WildWorldElements.ModElement {
	public CloaldFuelFuel(WildWorldElements instance) {
		super(instance, 226);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(CloaldItemItem.block, (int) (1)).getItem())
			event.setBurnTime(1600);
	}
}
