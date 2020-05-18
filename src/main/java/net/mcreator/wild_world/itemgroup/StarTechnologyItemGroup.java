
package net.mcreator.wild_world.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.wild_world.item.StarItemItem;
import net.mcreator.wild_world.WildWorldElements;

@WildWorldElements.ModElement.Tag
public class StarTechnologyItemGroup extends WildWorldElements.ModElement {
	public StarTechnologyItemGroup(WildWorldElements instance) {
		super(instance, 277);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabstartechnology") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(StarItemItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
