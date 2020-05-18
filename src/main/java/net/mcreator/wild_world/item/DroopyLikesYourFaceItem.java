
package net.mcreator.wild_world.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class DroopyLikesYourFaceItem extends WildWorldElements.ModElement {
	@ObjectHolder("wild_world:droopylikesyourface")
	public static final Item block = null;
	public DroopyLikesYourFaceItem(WildWorldElements instance) {
		super(instance, 118);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}
	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, WildWorldElements.sounds.get(new ResourceLocation("wild_world:records.droopyface")),
					new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("droopylikesyourface");
		}
	}
}
