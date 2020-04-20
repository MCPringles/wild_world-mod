package net.mcreator.wild_world.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wild_world.WildWorldElements;

import java.util.Collection;

@WildWorldElements.ModElement.Tag
public class PineTeaFoodHealEventProcedure extends WildWorldElements.ModElement {
	public PineTeaFoodHealEventProcedure(WildWorldElements instance) {
		super(instance, 159);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PineTeaFoodHealEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((Math.random() >= 0.8)) {
			if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) >= 20)) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) 10, (int) 1, (false), (false)));
			} else {
				if ((new Object() {
					boolean check() {
						if (entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == Effects.HEALTH_BOOST)
									return true;
							}
						}
						return false;
					}
				}.check())) {
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.ABSORPTION, (int) 90, (int) 2, (false), (false)));
				} else {
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.ABSORPTION, (int) 60, (int) 1, (false), (false)));
				}
			}
		}
		if (entity instanceof PlayerEntity) {
			ItemStack _setstack = new ItemStack(Items.BOWL, (int) (1));
			_setstack.setCount(1);
			ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
		}
	}
}
