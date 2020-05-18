
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
import net.minecraft.world.Difficulty;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.monster.PillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.IllusionerEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.merchant.villager.WanderingTraderEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.wild_world.potion.WoundPotion;
import net.mcreator.wild_world.procedures.AsatchEntityDiesProcedure;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class AerodusMobEntity extends WildWorldElements.ModElement {
	public static EntityType entity = null;
	public AerodusMobEntity(WildWorldElements instance) {
		super(instance, 383);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(128).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(1.5f, 1.5f)).build("aerodusmob")
						.setRegistryName("aerodusmob");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -12957156, -417013, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("aerodusmob"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new Modelaerodus(), 0.7999999999999999f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("wild_world:textures/aerodus_texture.png");
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
			experienceValue = 8;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(5, new HurtByTargetGoal(this));
			if (this.getHealth() <= 15) {
				this.goalSelector.addGoal(1, new AvoidEntityGoal(this, PlayerEntity.class, (float) 16, 1.5, 1.5));
			}
			this.targetSelector.addGoal(7, new NearestAttackableTargetGoal(this, BoarEntity.CustomEntity.class, false, true));
			this.targetSelector.addGoal(8, new NearestAttackableTargetGoal(this, SheepEntity.class, false, true));
			this.targetSelector.addGoal(9, new NearestAttackableTargetGoal(this, TurtleEntity.class, false, true));
			this.targetSelector.addGoal(10, new NearestAttackableTargetGoal(this, LlamaEntity.class, false, true));
			this.targetSelector.addGoal(11, new NearestAttackableTargetGoal(this, PigEntity.class, false, true));
			this.targetSelector.addGoal(12, new NearestAttackableTargetGoal(this, RabbitEntity.class, false, true));
			this.targetSelector.addGoal(13, new NearestAttackableTargetGoal(this, FoxEntity.class, false, true));
			this.targetSelector.addGoal(14, new NearestAttackableTargetGoal(this, HorseEntity.class, false, true));
			this.targetSelector.addGoal(15, new NearestAttackableTargetGoal(this, CowEntity.class, false, true));
			this.targetSelector.addGoal(16, new NearestAttackableTargetGoal(this, CreeperEntity.class, false, true));
			this.targetSelector.addGoal(17, new NearestAttackableTargetGoal(this, IllusionerEntity.class, false, true));
			this.targetSelector.addGoal(18, new NearestAttackableTargetGoal(this, PillagerEntity.class, false, true));
			this.targetSelector.addGoal(19, new NearestAttackableTargetGoal(this, VindicatorEntity.class, false, true));
			this.targetSelector.addGoal(20, new NearestAttackableTargetGoal(this, VillagerEntity.class, false, true));
			this.targetSelector.addGoal(21, new NearestAttackableTargetGoal(this, WanderingTraderEntity.class, false, true));
			if (world.getDifficulty() != Difficulty.PEACEFUL) {
				this.targetSelector.addGoal(22, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false));
				this.targetSelector.addGoal(23, new NearestAttackableTargetGoal(this, ServerPlayerEntity.class, false, false));
			}
			this.goalSelector.addGoal(24, new MeleeAttackGoal(this, 1.2, true));
			this.goalSelector.addGoal(25, new LeapAtTargetGoal(this, (float) 0.5));
			this.goalSelector.addGoal(1, new AvoidEntityGoal(this, PolarBearEntity.class, (float) 16, 1, 0.4));
			this.goalSelector.addGoal(2, new AvoidEntityGoal(this, RavagerEntity.class, (float) 16, 1, 0.4));
			this.goalSelector.addGoal(3, new AvoidEntityGoal(this, IronGolemEntity.class, (float) 16, 1, 0.4));
			this.goalSelector.addGoal(4, new AvoidEntityGoal(this, AerodusMobEntity.CustomEntity.class, (float) 32, -1, 0.4));
			this.goalSelector.addGoal(26, new SwimGoal(this));
			this.goalSelector.addGoal(27, new LookRandomlyGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.BONE, (int) (1)));
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

		public boolean attackEntityAsMob(Entity entityIn) {
			boolean flag = super.attackEntityAsMob(entityIn);
			if (flag && entityIn instanceof LivingEntity) {
				float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
				((LivingEntity) entityIn).addPotionEffect(new EffectInstance(WoundPotion.potion, 400 + 200 * (int) f));
			}
			return flag;
		}

		@Override
		public void onDeath(DamageSource source) {
			super.onDeath(source);
			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;
			if (source.getTrueSource() instanceof PlayerEntity) {
				Entity entity = source.getTrueSource();
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				AsatchEntityDiesProcedure.executeProcedure($_dependencies);
			}
		}


		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				if (Math.max(amount - 7, 0) > 0) {
					return super.attackEntityFrom(source, amount);
				} else {
					return false;
				}
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.4);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.34);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
			if (this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE) != null)
				this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.4F);
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
