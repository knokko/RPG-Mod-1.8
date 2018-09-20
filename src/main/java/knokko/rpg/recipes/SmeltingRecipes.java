package knokko.rpg.recipes;

import knokko.rpg.blocks.main.RPGBlocks;
import knokko.rpg.items.main.RPGItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingRecipes {
	
	public static void load(){
		GameRegistry.addSmelting(RPGBlocks.ruby_ore, new ItemStack(RPGItems.ruby), 1);
	}
}
