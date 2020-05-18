
package net.mcreator.wild_world.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.Entity;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.wild_world.WildWorldElements;

import java.util.Random;
import java.util.List;
import java.util.Collections;

@WildWorldElements.ModElement.Tag
public class MarshFarmLandBlock extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:marshfarmland")
	public static final FarmlandBlock block = null;
	public MarshFarmLandBlock(WildWorldElements instance) {
		super(instance, 378);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends FarmlandBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.EARTH).sound(SoundType.WET_GRASS).hardnessAndResistance(1f, 3f).lightValue(0).harvestLevel(-1)
					.harvestTool(ToolType.SHOVEL).slipperiness(0.7f).tickRandomly());
			setRegistryName("marshfarmland");
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public BlockRenderLayer getRenderLayer() {
			return BlockRenderLayer.CUTOUT;
		}

		@Override
		public BlockState getStateForPlacement(BlockItemUseContext context) {
			return !this.getDefaultState().isValidPosition(context.getWorld(), context.getPos())
					? MarshDirtBlock.block.getDefaultState()
					: super.getStateForPlacement(context);
		}

		public static void turnToDirt(BlockState state, World worldIn, BlockPos pos) {
			worldIn.setBlockState(pos, nudgeEntitiesWithNewState(state, MarshDirtBlock.block.getDefaultState(), worldIn, pos));
		}

		@Override
		public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
			if (!worldIn.isRemote && net.minecraftforge.common.ForgeHooks.onFarmlandTrample(worldIn, pos, MarshDirtBlock.block.getDefaultState(),
					fallDistance, entityIn)) { // Forge: Move logic to
																																														// Entity#canTrample
				turnToDirt(worldIn.getBlockState(pos), worldIn, pos);
			}
			super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
		}

		private boolean hasCrops(IBlockReader worldIn, BlockPos pos) {
			BlockState state = worldIn.getBlockState(pos.up());
			return state.getBlock() instanceof net.minecraftforge.common.IPlantable
					&& canSustainPlant(state, worldIn, pos, Direction.UP, (net.minecraftforge.common.IPlantable) state.getBlock());
		}

		private static boolean hasWater(IWorldReader worldIn, BlockPos pos) {
			for (BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
				if (worldIn.getFluidState(blockpos).isTagged(FluidTags.WATER)) {
					return true;
				}
			}
			return net.minecraftforge.common.FarmlandWaterManager.hasBlockWaterTicket(worldIn, pos);
		}

		@Override
		public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
			if (!state.isValidPosition(worldIn, pos)) {
				turnToDirt(state, worldIn, pos);
			} else {
				int i = state.get(MOISTURE);
				if (!hasWater(worldIn, pos) && !worldIn.isRainingAt(pos.up())) {
					if (i > 0) {
						worldIn.setBlockState(pos, state.with(MOISTURE, Integer.valueOf(i - 1)), 2);
					} else if (!hasCrops(worldIn, pos)) {
						turnToDirt(state, worldIn, pos);
					}
				} else if (i < 7) {
					worldIn.setBlockState(pos, state.with(MOISTURE, Integer.valueOf(7)), 2);
				}
			}
		}

		@Override
		public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return false;
		}

		@Override
		public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
			return VoxelShapes.create(0D, 0D, 0D, 1D, 0.9375D, 1D);
		}

		@Override
		public MaterialColor getMaterialColor(BlockState state, IBlockReader blockAccess, BlockPos pos) {
			return MaterialColor.BLACK;
		}

		@Override
		public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction direction, IPlantable plantable) {
			return true;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(MarshDirtBlock.block, (int) (1)));
		}
	}
}
