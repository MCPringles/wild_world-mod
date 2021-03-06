package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

import net.mcreator.wild_world.block.PalmLeavesBlock;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class PalmGen4Procedure extends WildWorldElements.ModElement {
	public PalmGen4Procedure(WildWorldElements instance) {
		super(instance, 378);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure PalmGen4!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure PalmGen4!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure PalmGen4!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure PalmGen4!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double Prog = 0;
		double Index = 0;
		double Y = 0;
		if ((true)) {
			Prog = (double) 0;
			Index = (double) 0;
		}
		for (int index0 = 0; index0 < (int) (5); index0++) {
			if (((Index) == 0)) {
				Y = (double) 0;
			}
			if (((Index) == 1)) {
				Y = (double) 1;
			}
			if (((Index) == 2)) {
				Y = (double) 1;
			}
			if (((Index) == 3)) {
				Y = (double) 1;
			}
			if (((Index) == 4)) {
				Y = (double) 0;
			}
			if ((world.isAirBlock(new BlockPos((int) (x + (Prog)), (int) (y + (Y)), (int) z)))) {
				world.setBlockState(new BlockPos((int) (x + (Prog)), (int) (y + (Y)), (int) z), PalmLeavesBlock.block.getDefaultState(), 3);
			}
			Prog = (double) ((Prog) + 1);
			Index = (double) ((Index) + 1);
		}
		if ((true)) {
			Prog = (double) 0;
			Index = (double) 0;
		}
		for (int index1 = 0; index1 < (int) (5); index1++) {
			if (((Index) == 0)) {
				Y = (double) 0;
			}
			if (((Index) == 1)) {
				Y = (double) 1;
			}
			if (((Index) == 2)) {
				Y = (double) 1;
			}
			if (((Index) == 3)) {
				Y = (double) 1;
			}
			if (((Index) == 4)) {
				Y = (double) 0;
			}
			if ((world.isAirBlock(new BlockPos((int) (x - (Prog)), (int) (y + (Y)), (int) z)))) {
				world.setBlockState(new BlockPos((int) (x - (Prog)), (int) (y + (Y)), (int) z), PalmLeavesBlock.block.getDefaultState(), 3);
			}
			Prog = (double) ((Prog) + 1);
			Index = (double) ((Index) + 1);
		}
		if ((true)) {
			Prog = (double) 0;
			Index = (double) 0;
		}
		for (int index2 = 0; index2 < (int) (5); index2++) {
			if (((Index) == 0)) {
				Y = (double) 0;
			}
			if (((Index) == 1)) {
				Y = (double) 1;
			}
			if (((Index) == 2)) {
				Y = (double) 1;
			}
			if (((Index) == 3)) {
				Y = (double) 1;
			}
			if (((Index) == 4)) {
				Y = (double) 0;
			}
			if ((world.isAirBlock(new BlockPos((int) x, (int) (y + (Y)), (int) (z + (Prog)))))) {
				world.setBlockState(new BlockPos((int) x, (int) (y + (Y)), (int) (z + (Prog))), PalmLeavesBlock.block.getDefaultState(), 3);
			}
			Prog = (double) ((Prog) + 1);
			Index = (double) ((Index) + 1);
		}
		if ((true)) {
			Prog = (double) 0;
			Index = (double) 0;
		}
		for (int index3 = 0; index3 < (int) (5); index3++) {
			if (((Index) == 0)) {
				Y = (double) 0;
			}
			if (((Index) == 1)) {
				Y = (double) 1;
			}
			if (((Index) == 2)) {
				Y = (double) 1;
			}
			if (((Index) == 3)) {
				Y = (double) 1;
			}
			if (((Index) == 4)) {
				Y = (double) 0;
			}
			if ((world.isAirBlock(new BlockPos((int) x, (int) (y + (Y)), (int) (z - (Prog)))))) {
				world.setBlockState(new BlockPos((int) x, (int) (y + (Y)), (int) (z - (Prog))), PalmLeavesBlock.block.getDefaultState(), 3);
			}
			Prog = (double) ((Prog) + 1);
			Index = (double) ((Index) + 1);
		}
	}
}
