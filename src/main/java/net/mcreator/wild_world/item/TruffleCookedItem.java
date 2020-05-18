
package net.mcreator.wild_world.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class TruffleCookedItem extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:trufflecooked")
	public static final Item block = null;
	public TruffleCookedItem(WildWorldElements instance) {
		super(instance, 181);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(ItemGroup.FOOD).maxStackSize(64)
					.food((new Food.Builder()).hunger(8).saturation(9.200000000000001f).meat().build()));
			setRegistryName("trufflecooked");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}
	}
}
