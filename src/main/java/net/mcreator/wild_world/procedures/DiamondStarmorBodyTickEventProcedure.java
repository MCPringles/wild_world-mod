package net.mcreator.wild_world.procedures;

import net.minecraft.util.math.Vec3d;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class DiamondStarmorBodyTickEventProcedure extends WildWorldElements.ModElement {
	public DiamondStarmorBodyTickEventProcedure(WildWorldElements instance) {
		super(instance, 279);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure DiamondStarmorBodyTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).getFoodStats().getFoodLevel() : 0) == 20)) {
			if ((entity.isSneaking())) {
				entity.setMotionMultiplier(null, new Vec3d(0.25D, (double) 0.05F, 0.25D));
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.RESISTANCE, (int) 1, (int) 5, (true), (true)));
			}
		}
	}
}
