
package net.mcreator.wild_world.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class ElevenItem extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:eleven")
	public static final Item block = null;
	public ElevenItem(WildWorldElements instance) {
		super(instance, 119);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}
	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, WildWorldElements.sounds.get(new ResourceLocation("wild_world:records.eleven")),
					new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("eleven");
		}
	}
}
