
package net.mcreator.wild_world.potion;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effect;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.LivingEntity;

import net.mcreator.wild_world.procedures.WoundTickProcedure;
import net.mcreator.wild_world.procedures.WoundEndProcedure;
import net.mcreator.wild_world.item.SyrupBottleItem;
import net.mcreator.wild_world.item.PringlePearItem;
import net.mcreator.wild_world.item.GoldenPearItem;
import net.mcreator.wild_world.item.BirchSyrupBottleItem;
import net.mcreator.wild_world.WildWorldElements;

import java.util.List;
import java.util.ArrayList;

@WildWorldElements.ModElement.Tag
public class WoundPotion extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:wound")
	public static final Effect potion = null;
	public WoundPotion(WildWorldElements instance) {
		super(instance, 411);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerEffect(RegistryEvent.Register<Effect> event) {
		event.getRegistry().register(new EffectCustom());
	}
	public static class EffectCustom extends Effect {
		private final ResourceLocation potionIcon;
		public EffectCustom() {
			super(EffectType.HARMFUL, -11921641);
			setRegistryName("wound");
			potionIcon = new ResourceLocation("wild_world:textures/icon_wound.png");
		}

		@Override
		public String getName() {
			return "effect.wound";
		}

		@Override
		public boolean isBeneficial() {
			return false;
		}

		@Override
		public boolean isInstant() {
			return false;
		}

		@Override
		public List<ItemStack> getCurativeItems() {
			List<ItemStack> ret = new ArrayList<>();
			ret.add(new ItemStack(GoldenPearItem.block, (int) (1)));
			ret.add(new ItemStack(PringlePearItem.block, (int) (1)));
			ret.add(new ItemStack(Items.GOLDEN_APPLE, (int) (1)));
			ret.add(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, (int) (1)));
			ret.add(new ItemStack(Items.GOLDEN_CARROT, (int) (1)));
			ret.add(new ItemStack(SyrupBottleItem.block, (int) (1)));
			ret.add(new ItemStack(BirchSyrupBottleItem.block, (int) (1)));
			return ret;
		}

		@Override
		public boolean shouldRenderInvText(EffectInstance effect) {
			return true;
		}

		@Override
		public boolean shouldRenderHUD(EffectInstance effect) {
			return true;
		}

		@Override
		public void performEffect(LivingEntity entity, int amplifier) {
			World world = entity.world;
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				WoundTickProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public void removeAttributesModifiersFromEntity(LivingEntity entity, AbstractAttributeMap attributeMapIn, int amplifier) {
			super.removeAttributesModifiersFromEntity(entity, attributeMapIn, amplifier);
			World world = entity.world;
			int x = (int) entity.posX;
			int y = (int) entity.posY;
			int z = (int) entity.posZ;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				WoundEndProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public boolean isReady(int duration, int amplifier) {
			return true;
		}
	}
}
