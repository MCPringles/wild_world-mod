
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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.Pose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class AxolotlEntity extends WildWorldElements.ModElement {
	public static EntityType entity = null;
	public AxolotlEntity(WildWorldElements instance) {
		super(instance, 379);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.WATER_CREATURE)
				.setShouldReceiveVelocityUpdates(true).setTrackingRange(4).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new)
				.size(0.5f, 0.3f)).build("axolotl").setRegistryName("axolotl");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -858651, -1546907, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("axolotl"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("wild_world:midnightmarsh")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("swamp")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.WATER_CREATURE).add(new Biome.SpawnListEntry(entity, 2, 3, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				SquidEntity::func_223315_a);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(CustomEntity.class, renderManager -> {
			return new MobRenderer(renderManager, new modelaxolotl(), 0.2f) {
				protected ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("wild_world:textures/axolotl.png");
				}
			};
		});
	}
	public static class CustomEntity extends AnimalEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			enablePersistence();
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 0.5));
			this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(3, new PanicGoal(this, 1.2));
			this.goalSelector.addGoal(4, new TemptGoal(this, 0.9, Ingredient.fromItems(new ItemStack(Items.COD, (int) (1)).getItem()), true));
			this.goalSelector.addGoal(5, new TemptGoal(this, 0.9, Ingredient.fromItems(new ItemStack(Items.SALMON, (int) (1)).getItem()), true));
			this.goalSelector.addGoal(6,
					new TemptGoal(this, 0.9, Ingredient.fromItems(new ItemStack(Items.TROPICAL_FISH, (int) (1)).getItem()), true));
			this.goalSelector.addGoal(7, new AvoidEntityGoal(this, PlayerEntity.class, (float) 6, 1, 0.6));
			this.goalSelector.addGoal(8, new FollowMobGoal(this, (float) 0.5, 3, 2));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.cod.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.cod.death"));
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
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.8);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);
		}

		@Override
		public AgeableEntity createChild(AgeableEntity ageable) {
			return (CustomEntity) entity.create(this.world);
		}

		@Override
		public float getStandingEyeHeight(Pose pose, EntitySize size) {
			return this.isChild() ? size.height : 1.3F;
		}

		@Override
		public boolean isBreedingItem(ItemStack stack) {
			if (stack == null)
				return false;
			if (new ItemStack(Items.COD, (int) (1)).getItem() == stack.getItem())
				return true;
			if (new ItemStack(Items.SALMON, (int) (1)).getItem() == stack.getItem())
				return true;
			if (new ItemStack(Items.TROPICAL_FISH, (int) (1)).getItem() == stack.getItem())
				return true;
			return false;
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
	public static class modelaxolotl extends EntityModel<Entity> {
		private final RendererModel bodybone;
		private final RendererModel head;
		private final RendererModel gillsbone;
		private final RendererModel gillsbone2;
		private final RendererModel Leg1;
		private final RendererModel Leg2;
		private final RendererModel Leg3;
		private final RendererModel Leg4;
		public modelaxolotl() {
			textureWidth = 32;
			textureHeight = 32;
			bodybone = new RendererModel(this);
			bodybone.setRotationPoint(0.0F, 24.0F, 0.0F);
			bodybone.cubeList.add(new ModelBox(bodybone, 16, 0, -1.5F, -4.0F, -3.0F, 3, 3, 5, 0.0F, false));
			bodybone.cubeList.add(new ModelBox(bodybone, 11, 0, -1.0F, -4.0F, 2.0F, 2, 2, 3, 0.0F, false));
			bodybone.cubeList.add(new ModelBox(bodybone, 18, 13, -0.5F, -4.0F, 5.0F, 1, 1, 2, 0.0F, false));
			bodybone.cubeList.add(new ModelBox(bodybone, 0, 7, 0.0F, -5.0F, -2.0F, 0, 4, 12, 0.0F, false));
			head = new RendererModel(this);
			head.setRotationPoint(0.0F, 21.0F, -3.0F);
			setRotationAngle(head, 0.2618F, 0.0F, 0.0F);
			head.cubeList.add(new ModelBox(head, 0, 9, -2.0F, -2.0F, -4.0F, 4, 2, 5, 0.0F, false));
			gillsbone = new RendererModel(this);
			gillsbone.setRotationPoint(-2.0F, -1.0F, 1.0F);
			setRotationAngle(gillsbone, 0.0F, 0.5236F, 0.0F);
			head.addChild(gillsbone);
			gillsbone.cubeList.add(new ModelBox(gillsbone, 0, 5, -4.0F, -3.0F, 0.0F, 4, 4, 0, 0.0F, false));
			gillsbone2 = new RendererModel(this);
			gillsbone2.setRotationPoint(2.0F, -1.0F, 1.0F);
			setRotationAngle(gillsbone2, 0.0F, 2.618F, 0.0F);
			head.addChild(gillsbone2);
			gillsbone2.cubeList.add(new ModelBox(gillsbone2, 0, 5, -4.0F, -3.0F, 0.0F, 4, 4, 0, 0.0F, false));
			Leg1 = new RendererModel(this);
			Leg1.setRotationPoint(-1.0F, 21.0F, 0.0F);
			Leg1.cubeList.add(new ModelBox(Leg1, 0, 23, -3.0F, 0.0F, -4.0F, 3, 3, 3, 0.0F, false));
			Leg2 = new RendererModel(this);
			Leg2.setRotationPoint(1.0F, 21.0F, 0.0F);
			Leg2.cubeList.add(new ModelBox(Leg2, 0, 23, 0.0F, 0.0F, -4.0F, 3, 3, 3, 0.0F, true));
			Leg3 = new RendererModel(this);
			Leg3.setRotationPoint(-1.0F, 21.0F, 0.0F);
			Leg3.cubeList.add(new ModelBox(Leg3, 0, 23, -3.0F, 0.0F, 1.0F, 3, 3, 3, 0.0F, false));
			Leg4 = new RendererModel(this);
			Leg4.setRotationPoint(1.0F, 21.0F, 0.0F);
			Leg4.cubeList.add(new ModelBox(Leg4, 0, 23, 0.0F, 0.0F, 1.0F, 3, 3, 3, 0.0F, true));
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			bodybone.render(f5);
			head.render(f5);
			Leg1.render(f5);
			Leg2.render(f5);
			Leg3.render(f5);
			Leg4.render(f5);
		}

		public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
			super.setRotationAngles(e, f, f1, f2, f3, f4, f5);
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.Leg2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Leg3.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.Leg4.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.Leg1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
