package knokko.rpg.items;

import knokko.rpg.data.WorldData;
import knokko.rpg.entity.minion.EntityNecromancerMinion;
import knokko.rpg.entity.minion.TargetType;
import knokko.rpg.main.KnokkoRPG;
import knokko.rpg.main.s;
import knokko.util.Line;
import knokko.util.Position;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SkullRod extends Item{
	
	public SkullRod(){
		setUnlocalizedName("skullrod");
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		player.openGui(KnokkoRPG.modInstance, 0, world, (int)player.posX, (int)player.posY, (int)player.posZ);
		return stack;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer attacker, Entity target){
		if(target instanceof EntityNecromancerMinion && ((EntityNecromancerMinion)target).master == attacker){
			if(TargetType.isTargetType(stack.getDisplayName()))
				((EntityNecromancerMinion)target).targetType = TargetType.fromString(stack.getDisplayName());
			return true;
		}
        return false;
    }
}
