
package net.mcreator.wild_world.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.LivingEntity;

import net.mcreator.wild_world.procedures.PineTeaFoodHealEventProcedure;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class PineTeaItem extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:pinetea")
	public static final Item block = null;
	public PineTeaItem(WildWorldElements instance) {
		super(instance, 169);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(ItemGroup.FOOD).maxStackSize(1)
					.food((new Food.Builder()).hunger(-1).saturation(20f).setAlwaysEdible().build()));
			setRegistryName("pinetea");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.DRINK;
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
			ItemStack retval = super.onItemUseFinish(itemStack, world, entity);
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				PineTeaFoodHealEventProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
