
package net.mcreator.wild_world.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;

import net.mcreator.wild_world.procedures.PwSEaten4Procedure;
import net.mcreator.wild_world.procedures.PancakeWithSyrupItemIsCraftedsmeltedProcedure;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class PancakeWithSyrup4Item extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:pancakewithsyrup4")
	public static final Item block = null;
	public PancakeWithSyrup4Item(WildWorldElements instance) {
		super(instance, 334);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(null).maxStackSize(1).food((new Food.Builder()).hunger(9).saturation(3.7f).build()));
			setRegistryName("pancakewithsyrup4");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
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
				PwSEaten4Procedure.executeProcedure($_dependencies);
			}
			return retval;
		}

		@Override
		public void onCreated(ItemStack itemstack, World world, PlayerEntity entity) {
			super.onCreated(itemstack, world, entity);
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				PancakeWithSyrupItemIsCraftedsmeltedProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
