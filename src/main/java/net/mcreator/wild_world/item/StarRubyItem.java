
package net.mcreator.wild_world.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import net.mcreator.wild_world.itemgroup.StarTechnologyItemGroup;
import net.mcreator.wild_world.WildWorldElements;

import java.util.List;

@WildWorldElements.ModElement.Tag
public class StarRubyItem extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:starruby")
	public static final Item block = null;
	public StarRubyItem(WildWorldElements instance) {
		super(instance, 106);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(StarTechnologyItemGroup.tab).maxStackSize(64));
			setRegistryName("starruby");
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

		@Override
		@OnlyIn(Dist.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("It channels the power of the"));
			list.add(new StringTextComponent("stars better than anything else."));
		}
	}
}