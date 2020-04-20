
package net.mcreator.wild_world.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.wild_world.item.BluestoneItemItem;
import net.mcreator.wild_world.WildWorldElements;

import java.util.Random;

@WildWorldElements.ModElement.Tag
public class StarBoxerEntity extends WildWorldElements.ModElement {
	public static EntityType entity = null;
	public StarBoxerEntity(WildWorldElements instance) {
		super(instance, 93);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.5f, 0.6f)).build("starboxer")
						.setRegistryName("starboxer");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -5131855, -16769025, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("starboxer"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new modelcustom_model(), 0.2f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("wild_world:textures/starboxer.png");
				}
			};
		});
	}
	public static class CustomEntity extends BlazeEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 3;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1,
					new TemptGoal(this, 1, Ingredient.fromItems(new ItemStack(BluestoneItemItem.block, (int) (1)).getItem()), false));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, ServerPlayerEntity.class, false, true));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, true));
			this.targetSelector.addGoal(4, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(BluestoneItemItem.block, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.land"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.LIGHTNING_BOLT)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2);
		}

		public void livingTick() {
			super.livingTick();
			int i = (int) this.posX;
			int j = (int) this.posY;
			int k = (int) this.posZ;
			Random random = this.rand;
			if (true)
				for (int l = 0; l < 3; ++l) {
					double d0 = (double) ((float) i + 0.5) + (double) (random.nextFloat() - 0.5) * 0.2000000014901161D;
					double d1 = ((double) ((float) j + 0.7) + (double) (random.nextFloat() - 0.5) * 0.2000000014901161D) + 0.5;
					double d2 = (double) ((float) k + 0.5) + (double) (random.nextFloat() - 0.5) * 0.2000000014901161D;
					world.addParticle(ParticleTypes.FIREWORK, d0, d1, d2, 0, 0, 0);
				}
		}
	}

	// Made with Blockbench
	// Paste this code into your mod.
	public static class modelcustom_model extends EntityModel<Entity> {
		private final RendererModel head;
		private final RendererModel rods;
		private final RendererModel rods1;
		private final RendererModel rods2;
		public modelcustom_model() {
			textureWidth = 32;
			textureHeight = 32;
			head = new RendererModel(this);
			head.setRotationPoint(-3.0F, 17.0F, -4.0F);
			head.cubeList.add(new ModelBox(head, 0, 0, -1.0F, -3.0F, 0.0F, 8, 8, 8, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 9, 10, 0.0F, 4.0F, -0.05F, 2, 1, 0, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 9, 10, 4.0F, 4.0F, -0.05F, 2, 1, 0, 0.0F, true));
			rods = new RendererModel(this);
			rods.setRotationPoint(0.0F, 24.0F, 0.0F);
			rods1 = new RendererModel(this);
			rods1.setRotationPoint(0.0F, 0.0F, 0.0F);
			rods.addChild(rods1);
			rods1.cubeList.add(new ModelBox(rods1, 0, 0, -9.0F, -9.0F, -1.0F, 2, 6, 2, 0.0F, false));
			rods1.cubeList.add(new ModelBox(rods1, 0, 0, 7.0F, -9.0F, -1.0F, 2, 6, 2, 0.0F, false));
			rods1.cubeList.add(new ModelBox(rods1, 0, 0, -1.0F, -9.0F, -9.0F, 2, 6, 2, 0.0F, false));
			rods1.cubeList.add(new ModelBox(rods1, 0, 0, -1.0F, -9.0F, 7.0F, 2, 6, 2, 0.0F, false));
			rods2 = new RendererModel(this);
			rods2.setRotationPoint(0.0F, 0.0F, 0.0F);
			setRotationAngle(rods2, 0.0F, -0.7854F, 0.0F);
			rods.addChild(rods2);
			rods2.cubeList.add(new ModelBox(rods2, 0, 0, -9.0F, -9.0F, -1.0F, 2, 6, 2, 0.0F, false));
			rods2.cubeList.add(new ModelBox(rods2, 0, 0, 7.0F, -9.0F, -1.0F, 2, 6, 2, 0.0F, false));
			rods2.cubeList.add(new ModelBox(rods2, 0, 0, -1.0F, -9.0F, -9.0F, 2, 6, 2, 0.0F, false));
			rods2.cubeList.add(new ModelBox(rods2, 0, 0, -1.0F, -9.0F, 7.0F, 2, 6, 2, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			head.render(f5);
			rods.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.rods.rotateAngleY = f2 / 20.f;
		}
	}
}
