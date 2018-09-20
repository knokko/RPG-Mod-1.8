package knokko.rpg.items.tools;

import knokko.rpg.items.main.RPGItems;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class RubyShovel extends ItemSpade {

	public RubyShovel() {
		super(ToolMaterial.EMERALD);
		setUnlocalizedName("rubyshovel");
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
