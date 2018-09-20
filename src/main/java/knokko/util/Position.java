package knokko.util;

import java.util.Random;

import knokko.rpg.main.KnokkoRPG;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Position {
	public double x;
	public double y;
	public double z;
	/**
	 * Creates a new position on the given location.
	 * @param posX The x location of the position.
	 * @param posY The y location of the position.
	 * @param posZ The z location of the given position.
	 */
	public Position(double posX, double posY, double posZ){
		x = posX;
		y = posY;
		z = posZ;
	}
	
	Position() {}
	/**
	 * Creates a new position on the given location, this constructor works with integers.
	 * @param posX The x location of the position.
	 * @param posY The y location of the position.
	 * @param posZ The z location of the given position.
	 */
	public Position(int posX, int posY, int posZ){
		x = posX;
		y = posY;
		z = posZ;
	}
	/**
	 * Creates a new position on the given location, this constructor works with floats.
	 * @param posX The x location of the position.
	 * @param posY The y location of the position.
	 * @param posZ The z location of the given position.
	 */
	public Position(float posX, float posY, float posZ){
		x = posX;
		y = posY;
		z = posZ;
	}
	/**
	 * Creates a new position at the given entity.
	 * @param entity The entity where the position has to be created.
	 */
	public Position(Entity entity){
		x = entity.posX;
		y = entity.posY;
		z = entity.posZ;
	}
	
	public Position(MovingObjectPosition mop){
		x = mop.hitVec.xCoord;
		y = mop.hitVec.yCoord;
		z = mop.hitVec.zCoord;
	}
	public Position(Vec3 vec3){
		x = vec3.xCoord;
		y = vec3.yCoord;
		z = vec3.zCoord;
	}
	/**
	 * Returns this position as string.
	 */
	public String toString(){
		String string = "Position: x = " + x + ", y = " + y + ", z = " + z;
		return string;
	}
	/**
	 * Makes a string for the given position.
	 * @param p
	 * @return the string.
	 */
	public static String makeString(Position p){
		return p.toString();
	}
	/**
	 * Gives the x of this position as integer.
	 * @return The integer the double is the closest to.
	 */
	public int intX(){
		double t = x - (int)x;
		if(t >= 0.5){
			t = 1;
		}
		else {
			t = 0;
		}
		return (int) ((int)x + t);
	}
	/**
	 * Gives the y of this position as integer.
	 * @return The integer the double is the closest to.
	 */
	public int intY(){
		double t = y - (int)y;
		if(t >= 0.5){
			t = 1;
		}
		else {
			t = 0;
		}
		return (int) ((int)y + t);
	}
	/**
	 * Gives the z of this position as integer.
	 * @return The integer the double is the closest to.
	 */
	public int intZ(){
		double t = z - (int)z;
		if(t >= 0.5){
			t = 1;
		}
		else {
			t = 0;
		}
		return (int) ((int)z + t);
	}
	/**
	 * this method gives the x of the position as float.
	 * @return The x of the position as float.
	 */
	public float floatX(){
		return (float) x;
	}
	/**
	 * this method gives the y of the position as float.
	 * @return The y of the position as float.
	 */
	public float floatY(){
		return (float)y;
	}
	/**
	 * this method gives the z of the position as float.
	 * @return The z of the position as float.
	 */
	public float floatZ(){
		return (float)z;
	}
	/**
	 * Gives the distance to another position. It will use Math.hypot for getting it.
	 * @param p The other position.
	 * @return The distance from this position to the given position.
	 */
	public double getDistance(Position p){
		double distanceX;
		double distanceY;
		double distanceZ;
		if(x >= p.x){
			distanceX = x - p.x;
		}
		else {
			distanceX = p.x - x;
		}
		if(y >= p.y){
			distanceY = y - p.y;
		}
		else {
			distanceY = p.y - y;
		}
		if(z >= p.z){
			distanceZ = z - p.z;
		}
		else {
			distanceZ = p.z - z;
		}
		double distanceXZ = Math.hypot(distanceX, distanceZ);
		return Math.hypot(distanceXZ, distanceY);
	}
	/**
	 * Gives the distance to the given position. This will just use distanceX + distanceY + distanceZ.
	 * @param p The other position.
	 * @return The distance to the given position.
	 */
	public double getIndirectDistance(Position p){
		double distanceX;
		double distanceY;
		double distanceZ;
		if(x >= p.x){
			distanceX = x - p.x;
		}
		else {
			distanceX = p.x - x;
		}
		if(y >= p.y){
			distanceY = y - p.y;
		}
		else {
			distanceY = p.y - y;
		}
		if(z >= p.z){
			distanceZ = z - p.z;
		}
		else {
			distanceZ = p.z - z;
		}
		return distanceX + distanceY + distanceZ;
	}
	/**
	 * Gives the squared distance between two positions. It will use Math.hypot.
	 * @param a The first position.
	 * @param p The second position.
	 * @return The squared distance between the two positions.
	 */
	public static double getSquaredDistance(Position a, Position p){
		double distanceX;
		double distanceY;
		double distanceZ;
		if(a.x >= p.x){
			distanceX = a.x - p.x;
		}
		else {
			distanceX = p.x - a.x;
		}
		if(a.y >= p.y){
			distanceY = a.y - p.y;
		}
		else {
			distanceY = p.y - a.y;
		}
		if(a.z >= p.z){
			distanceZ = a.z - p.z;
		}
		else {
			distanceZ = p.z - a.z;
		}
		double distanceXZ = Math.hypot(distanceX, distanceZ);
		return Math.hypot(distanceXZ, distanceY);
	}
	/**
	 * Gives the distance between the given positions. This will just use distanceX + distanceY + distanceZ.
	 * @param p The first position.
	 * @param a The second position.
	 * @return The distance between the given positions.
	 */
	public static double getIndirectDistance(Position a, Position p){
		double distanceX;
		double distanceY;
		double distanceZ;
		if(a.x >= p.x){
			distanceX = a.x - p.x;
		}
		else {
			distanceX = p.x - a.x;
		}
		if(a.y >= p.y){
			distanceY = a.y - p.y;
		}
		else {
			distanceY = p.y - a.y;
		}
		if(a.z >= p.z){
			distanceZ = a.z - p.z;
		}
		else {
			distanceZ = p.z - a.z;
		}
		return distanceX + distanceY + distanceZ;
	}
	/**
	 * This method will write itself in the given NBTTagCompound.
	 * It will set a tag with the given key, and set the doubles there.
	 * This method is not called automatically, so save it where you need it.
	 * Be sure to use the same key at readFromNBt and to use another key for every position.
	 * @param nbt The NBTTagCompound where it will save its position.
	 * @param key The key it will use to create a new tag. 
	 */
	public void writeToNBT(NBTTagCompound nbt, String key){
		NBTTagCompound a = new NBTTagCompound();
		a.setDouble("x", x);
		a.setDouble("y", y);
		a.setDouble("z", z);
		nbt.setTag(key, a);
	}
	/**
	 * This method is made to read the position from the given NBTTagCompound.
	 * Be sure to use the same NBTTagCompound and string as writeToNBT.
	 * @param nbt The NBTTagCompound it will use to read the position.
	 * @param key The tag of the NBTTagCompound it will check.
	 */
	public void readFromNBT(NBTTagCompound nbt, String key){
		x = nbt.getCompoundTag(key).getDouble("x");
		y = nbt.getCompoundTag(key).getDouble("y");
		z = nbt.getCompoundTag(key).getDouble("z");
	}
	/**
	 * Spawns an entity at the given position.
	 * @param p The position the entity has to spawn.
	 * @param entity The entity to spawn.
	 * @param world The world to spawn the entity.
	 */
	public static void spawnEntity(Position p, Entity entity, World world){
		entity.posX = p.x;
		entity.posY = p.y;
		entity.posZ = p.z;
		world.spawnEntityInWorld(entity);
	}
	/**
	 * Spawns an entity at this position.
	 * @param entity The entity to spawn.
	 * @param world The world to spawn the entity.
	 */
	public void spawnEntity(Entity entity, World world){
		entity.posX = x;
		entity.posY = y;
		entity.posZ = z;
		world.spawnEntityInWorld(entity);
	}
	/**
	 * Sets a block at the given position.
	 * @param p The position to place the block.
	 * @param world The world to set the block.
	 * @param block The block to set.
	 */
	public static void setBlock(Position p, World world, IBlockState block){
		world.setBlockState(new BlockPos(p.intX(), p.intY(), p.intZ()), block);
	}
	/**
	 * Sets a block at this position.
	 * @param world The world to place the block.
	 * @param block The block to place.
	 */
	public void setBlock(World world, IBlockState block){
		world.setBlockState(new BlockPos(intX(), intY(), intZ()), block);
	}
	/**
	 * Spawns a particle at the given position with the given colors.
	 * @param world The world to spawn the particle.
	 * @param p The position to spawn the particle.
	 * @param color1 The amount red.
	 * @param color2 The amount green.
	 * @param color3 The amount purple/blue.
	 */
	public static void spawnParticle(World world, Position p, double color1, double color2, double color3){
		world.spawnParticle(EnumParticleTypes.REDSTONE, p.x, p.y, p.z, color1, color2, color3);
	}
	/**
	 * Spawn a particle at this position with the given colors.
	 * @param world The world to spawn the particle.
	 * @param color1 The amount red.
	 * @param color2 The amount green.
	 * @param color3 The amount purple/blue.
	 */
	public void spawnParticle(World world, double color1, double color2, double color3){
		KnokkoRPG.proxy.spawnParticle(world, x, y, z, color1, color2, color3, 1);
	}
	
	public boolean equals(Object b){
		if(b instanceof Position){
			Position c = (Position) b;
			if(c.x == x && c.y == y && c.z == z){
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
}
