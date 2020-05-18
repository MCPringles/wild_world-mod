
package net.mcreator.wild_world.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;
import net.minecraft.state.IntegerProperty;

import net.mcreator.wild_world.WildWorldElements;
import net.mcreator.customclass.MidnightMangroveTree;

import java.util.List;
import java.util.Collections;

@WildWorldElements.ModElement.Tag
public class MidnightMangroveSaplingBlock extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:midnightmangrovesapling")
	public static final SaplingBlock block = null;
	public MidnightMangroveSaplingBlock(WildWorldElements instance) {
		super(instance, 434);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends SaplingBlock {
		public CustomBlock() {
			super(new MidnightMangroveTree(), Block.Properties.create(Material.PLANTS).sound(SoundType.WET_GRASS).hardnessAndResistance(0.05f, 0f).lightValue(0)
					.doesNotBlockMovement().tickRandomly());
			setRegistryName("midnightmangrovesapling");
		}

		public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 1);
		
		@OnlyIn(Dist.CLIENT)
		@Override
		public BlockRenderLayer getRenderLayer() {
			return BlockRenderLayer.CUTOUT_MIPPED;
		}

		@Override
		public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return false;
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
			return 1;
		}

		@Override
		public MaterialColor getMaterialColor(BlockState state, IBlockReader blockAccess, BlockPos pos) {
			return MaterialColor.LIGHT_BLUE;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
