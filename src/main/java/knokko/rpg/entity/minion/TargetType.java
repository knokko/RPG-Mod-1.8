package knokko.rpg.entity.minion;

import net.minecraft.nbt.NBTTagCompound;

public final class TargetType {
	
	private final byte t;
	
	private TargetType(byte type){
		t = type;
	}
	/**
	 * The mob will attack every entity that has attacked this team.
	 */
	public static final TargetType DEFENSIVE = new TargetType((byte) 0);
	/**
	 * The mob will attack every living entity that is not in this team.
	 */
	public static final TargetType OFFENSIVE = new TargetType((byte) 1);
	/**
	 * The mob will attack every entity that has attacked the team of the master or the master has attacked.
	 */
	public static final TargetType HELPER = new TargetType((byte) 2);
	/**
	 * The mob will not fight, this is useful for resting.
	 */
	public static final TargetType PASSIVE = new TargetType((byte) 3);
	/**
	 * The mob will only attack entities that have attacked the master.
	 */
	public static final TargetType GUARDIAN = new TargetType((byte) 4);
	/**
	 * The mob will attack every monster and further every entity that HELPER would attack.
	 */
	public static final TargetType MONSTERKILLER = new TargetType((byte) 5);
	
	public static final TargetType fromNBT(NBTTagCompound nbt){
		byte type = nbt.getByte("targetType");
		return new TargetType(type);
	}
	public final void writeToNBT(NBTTagCompound nbt){
		if(t != 0 && nbt != null){
			nbt.setByte("targetType", t);
		}
	}
	
	public String toString(){
		return "" + t;
	}
	
	public boolean equals(Object object){
		if(object instanceof TargetType){
			return t == ((TargetType) object).t;
		}
		return false;
	}
	
	public String getName(){
		if(this.equals(PASSIVE))
			return "passive";
		if(this.equals(GUARDIAN))
			return "guardian";
		if(this.equals(DEFENSIVE))
			return "defensive";
		if(this.equals(HELPER))
			return "helper";
		if(this.equals(MONSTERKILLER))
			return "monsterkiller";
		if(this.equals(OFFENSIVE))
			return "offensive";
		return null;
	}
	
	public static boolean isTargetType(String string){
		if(string.matches("defensive")){
			return true;
		}
		if(string.matches("guardian")){
			return true;
		}
		if(string.matches("helper")){
			return true;
		}
		if(string.matches("passive")){
			return true;
		}
		if(string.matches("monsterkiller")){
			return true;
		}
		if(string.matches("offensive")){
			return true;
		}
		return false;
	}
	
	public static final TargetType fromString(String string){
		if(string.matches("defensive")){
			return DEFENSIVE;
		}
		if(string.matches("offensive")){
			return OFFENSIVE;
		}
		if(string.matches("helper")){
			return HELPER;
		}
		if(string.matches("monsterkiller")){
			return MONSTERKILLER;
		}
		if(string.matches("guardian")){
			return GUARDIAN;
		}
		return PASSIVE;
	}
}
