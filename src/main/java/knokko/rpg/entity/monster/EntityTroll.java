package knokko.rpg.entity.monster;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityTroll extends EntityMob{

	public EntityTroll(World world) {
		super(world);
		stepHeight = 2;
		setSize(1, 3);
		tasks.addTask(5, new EntityAIWander(this, 0.8D));
	}
	public void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(15);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(50);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3);
	}
}
