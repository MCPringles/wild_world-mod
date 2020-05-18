
package net.mcreator.wild_world.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.LogBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.wild_world.WildWorldElements;

import java.util.List;
import java.util.Collections;

@WildWorldElements.ModElement.Tag
public class BirchLogWildBlock extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:birchlogwild")
	public static final Block block = null;
	public BirchLogWildBlock(WildWorldElements instance) {
		super(instance, 203);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(null)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends LogBlock {
		private final MaterialColor verticalColor;
		public CustomBlock() {
			super(MaterialColor.SNOW, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2f, 10f).lightValue(0)
					.harvestLevel(-1).harvestTool(ToolType.AXE));
			setRegistryName("birchlogwild");
			this.verticalColor = MaterialColor.SNOW;
		}

		public boolean canSustainLeaves(BlockState state, World world, BlockPos pos) {
			return true;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(Items.BIRCH_WOOD, (int) (1)));
		}
	}
}
