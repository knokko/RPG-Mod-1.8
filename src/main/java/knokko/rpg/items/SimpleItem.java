package knokko.rpg.items;

import knokko.rpg.main.s;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class SimpleItem extends Item{
	
	public SimpleItem(String name, CreativeTabs tab){
		setUnlocalizedName(name);
		setCreativeTab(tab);
	}
}
