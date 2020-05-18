package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wild_world.WildWorldVariables;
import net.mcreator.wild_world.WildWorldElements;

import java.util.HashMap;

@WildWorldElements.ModElement.Tag
public class DebugModeCommandExecutedProcedure extends WildWorldElements.ModElement {
	public DebugModeCommandExecutedProcedure(WildWorldElements instance) {
		super(instance, 402);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure DebugModeCommandExecuted!");
			return;
		}
		if (dependencies.get("cmdparams") == null) {
			System.err.println("Failed to load dependency cmdparams for procedure DebugModeCommandExecuted!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure DebugModeCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap cmdparams = (HashMap) dependencies.get("cmdparams");
		World world = (World) dependencies.get("world");
		if ((((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText())).equals(""))) {
			if ((WildWorldVariables.WorldVariables.get(world).DebugMode)) {
				WildWorldVariables.WorldVariables.get(world).DebugMode = (boolean) (false);
				WildWorldVariables.WorldVariables.get(world).syncData(world);
			} else {
				WildWorldVariables.WorldVariables.get(world).DebugMode = (boolean) (true);
				WildWorldVariables.WorldVariables.get(world).syncData(world);
			}
			if (entity instanceof PlayerEntity && !world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(
						new StringTextComponent(
								(("Debug Mode (Wild World) set to ") + "" + ((WildWorldVariables.WorldVariables.get(world).DebugMode)) + "" + ("."))),
						(false));
			}
		} else {
			if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("0");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("on"))) {
				WildWorldVariables.WorldVariables.get(world).DebugMode = (boolean) (true);
				WildWorldVariables.WorldVariables.get(world).syncData(world);
				if (entity instanceof PlayerEntity && !world.isRemote) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(("Debug Mode (Wild World) set to ") + "" + ((WildWorldVariables.WorldVariables.get(world).DebugMode)) + "" + ("."))),
							(false));
				}
			} else if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("0");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("off"))) {
				WildWorldVariables.WorldVariables.get(world).DebugMode = (boolean) (false);
				WildWorldVariables.WorldVariables.get(world).syncData(world);
				if (entity instanceof PlayerEntity && !world.isRemote) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent(
							(("Debug Mode (Wild World) set to ") + "" + ((WildWorldVariables.WorldVariables.get(world).DebugMode)) + "" + ("."))),
							(false));
				}
			} else {
				if (entity instanceof PlayerEntity && !world.isRemote) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent((("Unknown parameter: \"") + "" + ((new Object() {
						public String getText() {
							String param = (String) cmdparams.get("0");
							if (param != null) {
								return param;
							}
							return "";
						}
					}.getText())) + "" + ("\"."))), (false));
				}
			}
		}
	}
}
