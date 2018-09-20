package knokko.rpg.items.tools;

import knokko.rpg.items.main.RPGItems;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class RubyPickaxe extends ItemPickaxe {

	public RubyPickaxe() {
		super(ToolMaterial.EMERALD);
		setMaxDamage(2000);
		setUnlocalizedName("rubypickaxe");
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
