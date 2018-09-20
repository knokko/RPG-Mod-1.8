package knokko.rpg.items.tools;

import knokko.rpg.items.main.RPGItems;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class RubyAxe extends ItemAxe {

	public RubyAxe() {
		super(ToolMaterial.EMERALD);
		setUnlocalizedName("rubyaxe");
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
