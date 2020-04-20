package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

import net.mcreator.wild_world.block.StarBoxBlock;
import net.mcreator.wild_world.WildWorldVariables;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class StarAltarTestForEventProcedure extends WildWorldElements.ModElement {
	public StarAltarTestForEventProcedure(WildWorldElements instance) {
		super(instance, 215);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure StarAltarTestForEvent!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure StarAltarTestForEvent!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure StarAltarTestForEvent!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure StarAltarTestForEvent!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((WildWorldVariables.MapVariables.get(world).StarBossKilled) == (true))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), StarBoxBlock.block.getDefaultState(), 3);
		}
	}
}
