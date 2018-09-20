package knokko.rpg.entity.minion;

import knokko.rpg.RPG;
import knokko.rpg.data.WorldData;
import knokko.util.Position;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityChow extends EntityNecromancerMinion{

	public EntityChow(World world) {
		super(world);
		setCurrentItemOrArmor(0, new ItemStack(Items.wooden_sword));
	}
	
	public EntityChow(Entity entity){
		this(entity, TargetType.fromString(WorldData.getString(entity, "standartnecromancytarget", "helper")));
		setCurrentItemOrArmor(0, new ItemStack(Items.wooden_sword));
	}
	
	public EntityChow(Entity entity, TargetType target){
		super(entity, target, new Position(entity));
	}
	
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5);
	}

	@Override
	public void applyEntityAttributes(Entity master) {
		if(master instanceof EntityPlayer){
			getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(RPG.getPlayerSpirit((EntityPlayer) master));
			getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4 * RPG.getPlayerSpirit((EntityPlayer) master));
		}
	}
}
