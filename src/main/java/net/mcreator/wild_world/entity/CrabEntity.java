
package net.mcreator.wild_world.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.wild_world.item.HalvedCoconutItem;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class CrabEntity extends WildWorldElements.ModElement {
	public static EntityType entity = null;
	public CrabEntity(WildWorldElements instance) {
		super(instance, 141);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.AMBIENT).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.4f, 0.3f)).build("crab")
						.setRegistryName("crab");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -2532057, -12769767, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("crab"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("wild_world:tropicalflatbeach")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("wild_world:tropicalislands")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("wild_world:tropicallowlands")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("wild_world:tropicalhighlands")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("beach")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.AMBIENT).add(new Biome.SpawnListEntry(entity, 2, 1, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::func_223315_a);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new modelcrabmodel(), 0.25f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("wild_world:textures/carb.png");
				}
			};
		});
	}
	public static class CustomEntity extends CreatureEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 1;
			setNoAI(false);
			enablePersistence();
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new PanicGoal(this, 1.2));
			this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, DriftwoodDwellerEntity.CustomEntity.class, true, true));
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, SilverfishEntity.class, true, true));
			this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 0.4));
			this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(7,
					new TemptGoal(this, 1, Ingredient.fromItems(new ItemStack(HalvedCoconutItem.block, (int) (1)).getItem()), false));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.ARTHROPOD;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.TROPICAL_FISH, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.tropical_fish.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.tropical_fish.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.DROWN)
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
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1);
		}

		@Override
		public boolean canBreatheUnderwater() {
			return true;
		}

		@Override
		public boolean isNotColliding(IWorldReader worldreader) {
			return worldreader.checkNoEntityCollision(this);
		}

		@Override
		public boolean isPushedByWater() {
			return false;
		}
	}

	// Made with Blockbench
	// Paste this code into your mod.
	public static class modelcrabmodel extends EntityModel<Entity> {
		private final RendererModel crab;
		private final RendererModel body;
		private final RendererModel legl1;
		private final RendererModel legr2;
		private final RendererModel legl3;
		private final RendererModel legr4;
		private final RendererModel legl5;
		private final RendererModel legr6;
		private final RendererModel eyel;
		private final RendererModel eyer;
		public modelcrabmodel() {
			textureWidth = 32;
			textureHeight = 32;
			crab = new RendererModel(this);
			crab.setRotationPoint(0.0F, 24.0F, 0.0F);
			setRotationAngle(crab, 0.0F, -1.5708F, 0.0F);
			body = new RendererModel(this);
			body.setRotationPoint(0.0F, 0.0F, 0.0F);
			crab.addChild(body);
			body.cubeList.add(new ModelBox(body, 6, 0, -2.0F, -2.5F, -2.5F, 4, 2, 5, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 6, 8, -3.0F, -2.0F, 0.75F, 1, 2, 3, 0.0F, false));
			body.cubeList.add(new ModelBox(body, 14, 8, -3.0F, -2.0F, -3.75F, 1, 2, 3, 0.0F, true));
			legl1 = new RendererModel(this);
			legl1.setRotationPoint(0.0F, -1.0F, 2.0F);
			crab.addChild(legl1);
			legl1.cubeList.add(new ModelBox(legl1, 0, 0, 0.0F, -1.0F, 0.0F, 0, 2, 3, 0.0F, false));
			legr2 = new RendererModel(this);
			legr2.setRotationPoint(0.0F, -1.0F, -2.0F);
			crab.addChild(legr2);
			legr2.cubeList.add(new ModelBox(legr2, 0, 2, 0.0F, -1.0F, -3.0F, 0, 2, 3, 0.0F, false));
			legl3 = new RendererModel(this);
			legl3.setRotationPoint(1.0F, -1.0F, 2.0F);
			setRotationAngle(legl3, 0.0F, 0.2618F, 0.0F);
			crab.addChild(legl3);
			legl3.cubeList.add(new ModelBox(legl3, 0, 0, 0.0F, -1.0F, 0.0F, 0, 2, 3, 0.0F, false));
			legr4 = new RendererModel(this);
			legr4.setRotationPoint(1.0F, -1.0F, -2.0F);
			setRotationAngle(legr4, 0.0F, -0.2618F, 0.0F);
			crab.addChild(legr4);
			legr4.cubeList.add(new ModelBox(legr4, 0, 2, 0.0F, -1.0F, -3.0F, 0, 2, 3, 0.0F, true));
			legl5 = new RendererModel(this);
			legl5.setRotationPoint(-1.0F, -1.0F, 2.0F);
			setRotationAngle(legl5, 0.0F, -0.2618F, 0.0F);
			crab.addChild(legl5);
			legl5.cubeList.add(new ModelBox(legl5, 0, 0, 0.0F, -1.0F, 0.0F, 0, 2, 3, 0.0F, true));
			legr6 = new RendererModel(this);
			legr6.setRotationPoint(-1.0F, -1.0F, -2.0F);
			setRotationAngle(legr6, 0.0F, 0.2618F, 0.0F);
			crab.addChild(legr6);
			legr6.cubeList.add(new ModelBox(legr6, 0, 2, 0.0F, -1.0F, -3.0F, 0, 2, 3, 0.0F, false));
			eyel = new RendererModel(this);
			eyel.setRotationPoint(-0.75F, -2.0F, 1.0F);
			crab.addChild(eyel);
			eyel.cubeList.add(new ModelBox(eyel, 0, 10, -0.5F, -2.25F, -0.5F, 1, 2, 1, 0.0F, false));
			eyer = new RendererModel(this);
			eyer.setRotationPoint(-0.75F, -2.0F, -1.0F);
			crab.addChild(eyer);
			eyer.cubeList.add(new ModelBox(eyer, 0, 10, -0.5F, -2.25F, -0.5F, 1, 2, 1, 0.0F, false));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			crab.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.legr4.rotateAngleY = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.legl1.rotateAngleY = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.legr6.rotateAngleY = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.legl3.rotateAngleY = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.eyel.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.eyel.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.legl5.rotateAngleY = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.eyer.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.eyer.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.legr2.rotateAngleY = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
