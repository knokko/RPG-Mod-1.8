package knokko.rpg.items.weapons;

import knokko.rpg.items.main.RPGItems;
import knokko.rpg.main.s;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class RubySword extends ItemSword{

	public RubySword() {
		super(ToolMaterial.EMERALD);
		setUnlocalizedName("rubysword");
		setMaxDamage(2000);
	}
	
	@Override
	public int getItemEnchantability(){
	    return 15;
	}
	
	@Override
	public String getToolMaterialName(){
        return "ruby";
    }
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair){
        return repair.getItem() == RPGItems.ruby;
    }
}
