package knokko.rpg.items.weapons;

import knokko.rpg.main.s;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BananaSword extends ItemSword{

	public BananaSword(int heal, float saturation, boolean wolfmeal) {
		super(ToolMaterial.IRON);
		setUnlocalizedName("bananasword");
		setCreativeTab(CreativeTabs.tabCombat);
		setFull3D();
	}
	
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity){
		player.getFoodStats().addStats(1, 0);
		if(entity instanceof EntityPlayer){
			((EntityPlayer) entity).getFoodStats().addStats(-1, 0);
		}
		entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 7);
        return false;
    }
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if(player.canEat(false)){
			player.getFoodStats().addStats(4, 0);
			stack.stackSize = 0;
		}
        return stack;
    }
}
