package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.wild_world.block.PineSaplingBlock;
import net.mcreator.wild_world.block.FrigidTopsoilBlock;
import net.mcreator.wild_world.block.FrigidDirtBlock;
import net.mcreator.wild_world.block.BarrenTopsoilBlock;
import net.mcreator.wild_world.block.BarrenDirtBlock;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class PineSaplingItemPlantedProcedure extends WildWorldElements.ModElement {
	public PineSaplingItemPlantedProcedure(WildWorldElements instance) {
		super(instance, 179);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure PineSaplingItemPlanted!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure PineSaplingItemPlanted!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure PineSaplingItemPlanted!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure PineSaplingItemPlanted!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((!(world.isAirBlock(new BlockPos((int) x, (int) (y - 1), (int) z))))) {
			if ((!(world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z))))) {
				if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.GRASS_BLOCK.getDefaultState().getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), PineSaplingBlock.block.getDefaultState(), 3);
				}
				if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BarrenTopsoilBlock.block.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), PineSaplingBlock.block.getDefaultState(), 3);
				}
				if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == FrigidTopsoilBlock.block.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), PineSaplingBlock.block.getDefaultState(), 3);
				}
				if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.DIRT.getDefaultState().getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), PineSaplingBlock.block.getDefaultState(), 3);
				}
				if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == BarrenDirtBlock.block.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), PineSaplingBlock.block.getDefaultState(), 3);
				}
				if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.MYCELIUM.getDefaultState().getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), PineSaplingBlock.block.getDefaultState(), 3);
				}
				if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == FrigidDirtBlock.block.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), PineSaplingBlock.block.getDefaultState(), 3);
				}
			}
		}
	}
}
