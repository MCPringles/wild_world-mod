
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.wild_world.item.PhantomPhurItem;
import net.mcreator.wild_world.WildWorldElements;

import java.util.Random;

@WildWorldElements.ModElement.Tag
public class PhantomAerodusEntity extends WildWorldElements.ModElement {
	public static EntityType entity = null;
	public PhantomAerodusEntity(WildWorldElements instance) {
		super(instance, 446);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(128).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(1.5f, 1.5f)).build("phantomaerodus")
						.setRegistryName("phantomaerodus");
		elements.entities.add(() -> entity);
		elements.items.add(
				() -> new SpawnEggItem(entity, -13025225, -15401216, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("phantomaerodus"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelaerodus(), 0.7999999999999999f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("wild_world:textures/phantaerodus_texture.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 6;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(2, new LeapAtTargetGoal(this, (float) 0.8));
			this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false));
			this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, ServerPlayerEntity.class, false, false));
			this.targetSelector.addGoal(6, new NearestAttackableTargetGoal(this, CreeperEntity.class, false, true));
			this.goalSelector.addGoal(7, new MeleeAttackGoal(this, 1.2, true));
			this.goalSelector.addGoal(8, new AvoidEntityGoal(this, PolarBearEntity.class, (float) 12, 1.4, 1.2));
			this.goalSelector.addGoal(9, new AvoidEntityGoal(this, WolfEntity.class, (float) 12, 1.4, 1.2));
			this.goalSelector.addGoal(10, new AvoidEntityGoal(this, RavagerEntity.class, (float) 12, 1.4, 1.2));
			this.goalSelector.addGoal(11, new AvoidEntityGoal(this, AerodusMobEntity.CustomEntity.class, (float) 12, 1.4, 1.2));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEAD;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(PhantomPhurItem.block, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
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
			if (source == DamageSource.DROWN)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.2);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.34);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
		}

		public void livingTick() {
			super.livingTick();
			int i = (int) this.posX;
			int j = (int) this.posY;
			int k = (int) this.posZ;
			Random random = this.rand;
			if (true)
				for (int l = 0; l < 4; ++l) {
					double d0 = (i + random.nextFloat());
					double d1 = (j + random.nextFloat());
					double d2 = (k + random.nextFloat());
					int i1 = random.nextInt(2) * 2 - 1;
					double d3 = (random.nextFloat() - 0.5D) * 0.5D;
					double d4 = (random.nextFloat() - 0.5D) * 0.5D;
					double d5 = (random.nextFloat() - 0.5D) * 0.5D;
					world.addParticle(ParticleTypes.UNDERWATER, d0, d1, d2, d3, d4, d5);
				}
		}
	}

	// Made with Blockbench
	// Paste this code into your mod.
	public static class Modelaerodus extends EntityModel<Entity> {
		private final RendererModel aerodus;
		private final RendererModel head;
		private final RendererModel mouth;
		private final RendererModel ears;
		private final RendererModel body;
		private final RendererModel triangle;
		private final RendererModel neck;
		private final RendererModel leg1;
		private final RendererModel leg2;
		private final RendererModel leg3;
		private final RendererModel leg4;
		private final RendererModel tail;
		private final RendererModel tailend;
		public Modelaerodus() {
			textureWidth = 128;
			textureHeight = 128;
			aerodus = new RendererModel(this);
			aerodus.setRotationPoint(0.0F, 11.0F, -7.0F);
			head = new RendererModel(this);
			head.setRotationPoint(0.0F, -13.0F, -7.0F);
			setRotationAngle(head, 0.5236F, 0.0F, 0.0F);
			aerodus.addChild(head);
			head.cubeList.add(new ModelBox(head, 0, 60, -5.0F, -4.0F, -9.0F, 10, 10, 10, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 30, 60, -4.0F, -4.0F, -13.0F, 8, 6, 4, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 24, 80, -4.01F, 0.0F, -13.01F, 2, 6, 2, 0.0F, false));
			head.cubeList.add(new ModelBox(head, 24, 80, 2.01F, 0.0F, -13.01F, 2, 6, 2, 0.0F, false));
			mouth = new RendererModel(this);
			mouth.setRotationPoint(0.0F, -2.0F, -5.0F);
			head.addChild(mouth);
			mouth.cubeList.add(new ModelBox(mouth, 0, 80, -4.0F, 2.0F, -8.0F, 8, 4, 8, -0.1F, false));
			ears = new RendererModel(this);
			ears.setRotationPoint(0.0F, -2.0F, -3.0F);
			setRotationAngle(ears, 0.5236F, 0.0F, 0.0F);
			head.addChild(ears);
			ears.cubeList.add(new ModelBox(ears, 0, 80, 5.01F, -1.0F, 2.0F, 0, 4, 4, 0.0F, false));
			ears.cubeList.add(new ModelBox(ears, 0, 80, -5.01F, -1.0F, 2.0F, 0, 4, 4, 0.0F, true));
			body = new RendererModel(this);
			body.setRotationPoint(0.0F, -7.0F, 7.0F);
			aerodus.addChild(body);
			body.cubeList.add(new ModelBox(body, 0, 0, -5.0F, -4.0F, -13.0F, 10, 10, 28, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 76, 12, -7.0F, -5.0F, -15.0F, 14, 14, 12, 0.0F, false));
			triangle = new RendererModel(this);
			triangle.setRotationPoint(0.0F, 6.0F, -7.0F);
			setRotationAngle(triangle, 0.0873F, 0.0F, 0.0F);
			body.addChild(triangle);
			triangle.cubeList.add(new ModelBox(triangle, 0, 38, -4.0F, 0.0F, 1.0F, 8, 2, 20, 0.0F, false));
			neck = new RendererModel(this);
			neck.setRotationPoint(0.0F, -4.0F, -12.0F);
			setRotationAngle(neck, 1.0472F, 0.0F, 0.0F);
			body.addChild(neck);
			neck.cubeList.add(new ModelBox(neck, 36, 38, -4.0F, -4.0F, -5.0F, 8, 12, 8, 0.0F, false));
			leg1 = new RendererModel(this);
			leg1.setRotationPoint(-6.0F, -7.0F, -1.0F);
			aerodus.addChild(leg1);
			leg1.cubeList.add(new ModelBox(leg1, 0, 0, -2.0F, -2.0F, -3.0F, 4, 20, 4, 0.0F, false));
			leg1.cubeList.add(new ModelBox(leg1, 48, 20, -2.0F, 18.0F, -5.0F, 4, 2, 6, 0.0F, false));
			leg2 = new RendererModel(this);
			leg2.setRotationPoint(6.0F, -7.0F, -1.0F);
			aerodus.addChild(leg2);
			leg2.cubeList.add(new ModelBox(leg2, 0, 0, -2.0F, -2.0F, -3.0F, 4, 20, 4, 0.0F, false));
			leg2.cubeList.add(new ModelBox(leg2, 48, 20, -2.0F, 18.0F, -5.0F, 4, 2, 6, 0.0F, true));
			leg3 = new RendererModel(this);
			leg3.setRotationPoint(6.0F, -7.0F, 19.0F);
			aerodus.addChild(leg3);
			leg3.cubeList.add(new ModelBox(leg3, 0, 0, -2.0F, -2.0F, -3.0F, 4, 20, 4, 0.0F, false));
			leg3.cubeList.add(new ModelBox(leg3, 48, 20, -2.0F, 18.0F, -5.0F, 4, 2, 6, 0.0F, true));
			leg4 = new RendererModel(this);
			leg4.setRotationPoint(-6.0F, -7.0F, 19.0F);
			aerodus.addChild(leg4);
			leg4.cubeList.add(new ModelBox(leg4, 0, 0, -2.0F, -2.0F, -3.0F, 4, 20, 4, 0.0F, false));
			leg4.cubeList.add(new ModelBox(leg4, 48, 20, -2.0F, 18.0F, -5.0F, 4, 2, 6, 0.0F, false));
			tail = new RendererModel(this);
			tail.setRotationPoint(0.0F, -11.0F, 22.0F);
			setRotationAngle(tail, -1.0472F, 0.0F, 0.0F);
			aerodus.addChild(tail);
			tail.cubeList.add(new ModelBox(tail, 78, 38, -1.0F, 0.0F, 0.0F, 2, 2, 16, 0.0F, false));
			tailend = new RendererModel(this);
			tailend.setRotationPoint(0.0F, 2.0F, 16.0F);
			setRotationAngle(tailend, 1.0472F, 0.0F, 0.0F);
			tail.addChild(tailend);
			tailend.cubeList.add(new ModelBox(tailend, 98, 48, -1.0F, -2.0F, 0.0F, 2, 2, 4, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			aerodus.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = 0.5236F + f4 / (180F / (float) Math.PI);
			this.leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.leg4.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.leg3.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		}
	}
}
