package knokko.rpg.entity.minion;

import knokko.rpg.RPG;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityDreadLord extends EntityNecromancerMinion{

	public EntityDreadLord(Entity owner) {
		super(owner);
	}
	
	public EntityDreadLord(World world){
		super(world);
	}
	
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
	}

	@Override
	public void applyEntityAttributes(Entity master) {
		if(master instanceof EntityPlayer){
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3 * RPG.getPlayerSpirit((EntityPlayer) master));
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8 * RPG.getPlayerSpirit((EntityPlayer) master));
		}
		else {
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(15);
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60);
		}
	}
	
}
