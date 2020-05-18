package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

import net.mcreator.wild_world.block.CornStageReadyBlock;
import net.mcreator.wild_world.block.CornStage7Block;
import net.mcreator.wild_world.block.CornStage6Block;
import net.mcreator.wild_world.block.CornStage5Block;
import net.mcreator.wild_world.block.CornStage4Block;
import net.mcreator.wild_world.block.CornStage3Block;
import net.mcreator.wild_world.block.CornStage2Block;
import net.mcreator.wild_world.block.CornStage1Block;
import net.mcreator.wild_world.block.CornStage0Block;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class CornProgressProcedure extends WildWorldElements.ModElement {
	public CornProgressProcedure(WildWorldElements instance) {
		super(instance, 356);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure CornProgress!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure CornProgress!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure CornProgress!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure CornProgress!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == CornStage7Block.block.getDefaultState().getBlock())) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), CornStageReadyBlock.block.getDefaultState(), 3);
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == CornStage6Block.block.getDefaultState().getBlock())) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), CornStage7Block.block.getDefaultState(), 3);
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == CornStage5Block.block.getDefaultState().getBlock())) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), CornStage6Block.block.getDefaultState(), 3);
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == CornStage4Block.block.getDefaultState().getBlock())) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), CornStage5Block.block.getDefaultState(), 3);
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == CornStage2Block.block.getDefaultState().getBlock())) {
			if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == CornStage3Block.block.getDefaultState()
					.getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), CornStage4Block.block.getDefaultState(), 3);
			} else if ((world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z)))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), CornStage3Block.block.getDefaultState(), 3);
			}
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == CornStage1Block.block.getDefaultState().getBlock())) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), CornStage2Block.block.getDefaultState(), 3);
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == CornStage0Block.block.getDefaultState().getBlock())) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), CornStage1Block.block.getDefaultState(), 3);
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == CornStage3Block.block.getDefaultState().getBlock())) {
			if ((world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z)))) {
				world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), CornStage0Block.block.getDefaultState(), 3);
			}
		}
	}
}
