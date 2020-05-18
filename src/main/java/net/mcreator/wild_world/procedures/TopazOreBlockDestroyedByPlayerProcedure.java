package net.mcreator.wild_world.procedures;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class TopazOreBlockDestroyedByPlayerProcedure extends WildWorldElements.ModElement {
	public TopazOreBlockDestroyedByPlayerProcedure(WildWorldElements instance) {
		super(instance, 283);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure TopazOreBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure TopazOreBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure TopazOreBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure TopazOreBlockDestroyedByPlayer!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure TopazOreBlockDestroyedByPlayer!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
			if ((0 != (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH,
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))))) {
				if (!world.isRemote) {
					world.addEntity(new ExperienceOrbEntity(world, x, y, z, (int) 3));
				}
				if ((Math.random() <= 0.6)) {
					if (!world.isRemote) {
						world.addEntity(new ExperienceOrbEntity(world, x, y, z, (int) 1));
					}
				}
				if ((Math.random() <= 0.65)) {
					if (!world.isRemote) {
						world.addEntity(new ExperienceOrbEntity(world, x, y, z, (int) 1));
					}
				}
				if ((Math.random() <= 0.5)) {
					if (!world.isRemote) {
						world.addEntity(new ExperienceOrbEntity(world, x, y, z, (int) 1));
					}
				}
				if ((Math.random() <= 0.45)) {
					if (!world.isRemote) {
						world.addEntity(new ExperienceOrbEntity(world, x, y, z, (int) 1));
					}
				}
			}
		}
	}
}
