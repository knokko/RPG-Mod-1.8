package knokko.rpg.recipes;

import knokko.rpg.items.main.RPGItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ShapedRecipes {
	
	public static final ItemStack woodwand = new ItemStack(RPGItems.woodWand);
	public static final ItemStack skullrod = new ItemStack(RPGItems.skullRod);
	
	public static void load(){
		GameRegistry.addShapedRecipe(woodwand, "  l", " s ", "s  ", 'l', Items.wheat_seeds, 's', Items.stick);
		GameRegistry.addShapedRecipe(skullrod, " s ", " w ", " w ", 'w', Items.stick, 's', Items.skull);
	}
}
