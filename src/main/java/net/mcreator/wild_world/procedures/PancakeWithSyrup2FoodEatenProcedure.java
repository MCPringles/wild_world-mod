package net.mcreator.wild_world.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wild_world.item.PancakeWithSyrupItem;
import net.mcreator.wild_world.item.PancakeWithSyrup2Item;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class PancakeWithSyrup2FoodEatenProcedure extends WildWorldElements.ModElement {
	public PancakeWithSyrup2FoodEatenProcedure(WildWorldElements instance) {
		super(instance, 290);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PancakeWithSyrup2FoodEaten!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).inventory.clearMatchingItems(p -> new ItemStack(PancakeWithSyrup2Item.block, (int) (1)).getItem() == p.getItem(),
					(int) 1);
		if (entity instanceof PlayerEntity) {
			ItemStack _setstack = new ItemStack(PancakeWithSyrupItem.block, (int) (1));
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
		}
	}
}
