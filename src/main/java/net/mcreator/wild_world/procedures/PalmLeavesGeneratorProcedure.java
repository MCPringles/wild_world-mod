package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class PalmLeavesGeneratorProcedure extends WildWorldElements.ModElement {
	public PalmLeavesGeneratorProcedure(WildWorldElements instance) {
		super(instance, 379);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure PalmLeavesGenerator!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure PalmLeavesGenerator!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure PalmLeavesGenerator!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure PalmLeavesGenerator!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((Math.random() <= 0.25)) {
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("world", world);
				$_dependencies.put("x", (int) (x));
				$_dependencies.put("y", (int) (y));
				$_dependencies.put("z", (int) (z));
				PalmGen1Procedure.executeProcedure($_dependencies);
			}
		} else if ((Math.random() <= 0.33)) {
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("world", world);
				$_dependencies.put("x", (int) (x));
				$_dependencies.put("y", (int) (y));
				$_dependencies.put("z", (int) (z));
				PalmGen2Procedure.executeProcedure($_dependencies);
			}
		} else if ((Math.random() <= 0.5)) {
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("world", world);
				$_dependencies.put("x", (int) (x));
				$_dependencies.put("y", (int) (y));
				$_dependencies.put("z", (int) (z));
				PalmGen3Procedure.executeProcedure($_dependencies);
			}
		} else {
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("world", world);
				$_dependencies.put("x", (int) (x));
				$_dependencies.put("y", (int) (y));
				$_dependencies.put("z", (int) (z));
				PalmGen4Procedure.executeProcedure($_dependencies);
			}
		}
	}
}
