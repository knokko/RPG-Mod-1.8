package knokko.rpg.entity.minion;

import net.minecraft.nbt.NBTTagCompound;

public class FightType {
	private byte i;
	
	private FightType(byte type){
		i = type;
	}
	
	public static final FightType CLOSECOMBAT = new FightType((byte) 0);
	public static final FightType RANGED = new FightType((byte) 1);
	public static final FightType MULTIPLY = new FightType((byte) 2);
	
	public final void writeToNBT(NBTTagCompound nbt){
		if(nbt != null && i != 0){
			nbt.setByte("fightType", i);
		}
	}
	
	public static final FightType fromNBT(NBTTagCompound nbt){
		byte b = 0;
		if(nbt != null){
			b = nbt.getByte("fightType");
		}
		return new FightType(b);
	}
	
	public boolean equals(Object object){
		if(object instanceof FightType){
			return i == ((FightType) object).i;
		}
		return false;
	}
}
