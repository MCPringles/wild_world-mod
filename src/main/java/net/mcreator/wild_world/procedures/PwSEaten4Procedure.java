package net.mcreator.wild_world.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wild_world.item.PancakeWithSyrup4Item;
import net.mcreator.wild_world.item.PancakeWithSyrup3Item;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class PwSEaten4Procedure extends WildWorldElements.ModElement {
	public PwSEaten4Procedure(WildWorldElements instance) {
		super(instance, 292);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PwSEaten4!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).inventory.clearMatchingItems(p -> new ItemStack(PancakeWithSyrup4Item.block, (int) (1)).getItem() == p.getItem(),
					(int) 1);
		if (entity instanceof PlayerEntity) {
			ItemStack _setstack = new ItemStack(PancakeWithSyrup3Item.block, (int) (1));
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
		}
	}
}
