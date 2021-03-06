package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.wild_world.block.FrigidTopsoilBlock;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class FrigidTopsoilSnowyUpdateTickProcedure extends WildWorldElements.ModElement {
	public FrigidTopsoilSnowyUpdateTickProcedure(WildWorldElements instance) {
		super(instance, 243);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure FrigidTopsoilSnowyUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure FrigidTopsoilSnowyUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure FrigidTopsoilSnowyUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure FrigidTopsoilSnowyUpdateTick!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((!((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.SNOW.getDefaultState().getBlock()))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), FrigidTopsoilBlock.block.getDefaultState(), 3);
		}
	}
}
