
package net.mcreator.wild_world.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class SyrupBirchBucketItem extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:syrupbirchbucket")
	public static final Item block = null;
	public SyrupBirchBucketItem(WildWorldElements instance) {
		super(instance, 133);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1));
			setRegistryName("syrupbirchbucket");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
