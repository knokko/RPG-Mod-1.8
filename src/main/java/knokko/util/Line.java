package knokko.util;

import java.util.ArrayList;
import java.util.List;

import knokko.rpg.main.KnokkoRPG;
import knokko.util.bag.BlockBag;
import knokko.util.bag.EntityBag;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.particle.EntityReddustFX.Factory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * This class is a line in a 3D world that is made for minecraft.
 * It has some methods that can be useful, por example is gives the distances and can spawn particles over the whole line.
 * @author knokko
 *
 */
public class Line {
	/**
	 * Creates a new line between the given positions.
	 * It doesn't matter which position you add first.
	 * @param pos1 The first position of this line.
	 * @param pos2 The second position of this line.
	 */
	public Line(Position pos1, Position pos2){
		position1 = pos1;
		position2 = pos2;
		refresh();
	}
	protected Line() {}
	private Position position1;
	private Position position2;
	/**
	 * The distance of this line on the x-axis.
	 */
	public double distanceX;
	/**
	 * The distance of this line on the y-axis.
	 */
	public double distanceY;
	/**
	 * The distance of this line on the z-axis.
	 */
	public double distanceZ;
	
	public double distanceXT;
	public double distanceYT;
	public double distanceZT;
	/**
	 * the distance between the positions of this line.
	 */
	public double distance;
	/**
	 * This method refreshes all the data this line has, for now only the distances to the positions.
	 */
	public void refresh(){
		distanceX = position2.x - position1.x;
		distanceY = position2.y - position1.y;
		distanceZ = position2.z - position1.z;
		distanceXT = distanceX;
		distanceYT = distanceY;
		distanceZT = distanceZ;
		if(distanceX < 0){
			distanceX *= -1;
		}
		if(distanceY < 0){
			distanceY *= -1;
		}
		if(distanceZ < 0){
			distanceZ *= -1;
		}
		double distanceXZ = Math.hypot(distanceX, distanceZ);
		distance = Math.hypot(distanceXZ, distanceY);
	}
	/**
	 * This method returns one of the positions.
	 * @param i The position to get. i <= 1 will return position1, position2 otherwise.
	 * @return The position you asked.
	 */
	public Position getPosition(int i){
		if(i <= 1){
			return position1;
		}
		else {
			return position2;
		}
	}
	/**
	 * This method changes one of the positions and refreshes the distance.
	 * @param i The position to choose. i <= 1 will change position1, position2 otherwise.
	 * @param pos The new value of the position.
	 */
	public void setPosition(int i, Position pos){
		if(i <= 1){
			position1 = pos;
		}
		else {
			position2 = pos;
		}
		refresh();
	}
	
	/**
	 * 
	 * @param world the world to spawn the particles.
	 * @param red how red the particles will be.
	 * @param green how green the particles will be.
	 * @param blue how blue the particles will be.
	 * @param dbp distance between particles.
	 */
	public void spawnParticles(World world, double red, double green, double blue, double dbp, double twist){
		double speedX = distanceXT * dbp;
		double speedY = distanceYT * dbp;
		double speedZ = distanceZT * dbp;
		double x = position1.x;
		double y = position1.y;
		double z = position1.z;
		int times = 0;
		int particles = ExtraUtils.fromDouble(ExtraUtils.divineAccurate(position1.getDistance(position2), dbp));
		while(times <= particles && world.isRemote){
			KnokkoRPG.proxy.spawnParticle(world, x, y, z, red, green, blue);
			x += speedX;
			y += speedY;
			z += speedZ;
			++times;
			//TODO work in progress
		}
	}
	
	/**
	 * 
	 * @param world the world to spawn the particles.
	 * @param red how red the particles will be.
	 * @param green how green the particles will be.
	 * @param blue how blue the particles will be.
	 * @param dbp distance between particles.
	 */
	public void spawnParticles2(World world, double red, double green, double blue, double dbp, int lifetime){
		double speedX = distanceXT * dbp;
		double speedY = distanceYT * dbp;
		double speedZ = distanceZT * dbp;
		double x = position1.x;
		double y = position1.y;
		double z = position1.z;
		int times = 0;
		int particles = ExtraUtils.fromDouble(ExtraUtils.divineAccurate(position1.getDistance(position2), dbp));
		while(times <= particles && world.isRemote){
			world.spawnParticle(EnumParticleTypes.REDSTONE, x, y, z, red, green, blue);
			x += speedX;
			y += speedY;
			z += speedZ;
			++times;
		}
		
	}
	
	public String toString(){
		return "Line: distance = " + distance + "  position1 = [" + position1 + "], position2 = [" + position2 + "]";
	}
	/**
	 * This method makes and returns a list of all entities that are in this line.
	 * @param world The world to look for the entities
	 * @param entityClass the class the entities must have or extend.
	 * @param putInBag If this is true, the types in the given list will be EntityBag instead of normal Entities.
	 * @return A list of all entities in this line.
	 */
	public List getEntities(World world, Class entityClass, boolean putInBag){
		refresh();
		double minX = Math.min(position1.x, position2.x);
		double maxX = Math.max(position1.x, position2.x);
		double minY = Math.min(position1.y, position2.y);
		double maxY = Math.max(position1.y, position2.y);
		double minZ = Math.min(position1.z, position2.z);
		double maxZ = Math.max(position1.z, position2.z);
		List entities = world.getEntitiesWithinAABB(entityClass, AxisAlignedBB.fromBounds(minX, minY, minZ, maxX, maxY, maxZ));
		double factorXY = ExtraUtils.divineAccurate(distanceYT, distanceXT);
		double factorXZ = ExtraUtils.divineAccurate(distanceZT, distanceXT);
		double bufferXY = position1.y - (position1.x * factorXY);
		double bufferXZ = position1.z - (position1.x * factorXZ);
		List entities2 = new ArrayList();
		int times = 0;
		while(times < entities.size()){
			Entity entity = (Entity) entities.get(times);
			AxisAlignedBB aabb = entity.getEntityBoundingBox();
			double d = (aabb.minX * factorXY) + bufferXY;
			double e = (aabb.maxX * factorXY) + bufferXY;
			double m = (aabb.minX * factorXZ) + bufferXZ;
			double n = (aabb.maxX * factorXZ) + bufferXZ;
			boolean flag1 = m <= aabb.maxZ && n >= aabb.minZ || m >= aabb.minZ && n <= aabb.maxZ;
			boolean flag2 = d <= aabb.maxY && e >= aabb.minY || d >= aabb.minY && e <= aabb.maxY;
			if(flag1 && flag2){
				if(!putInBag){
					entities2.add(entity);
				}
				else {
					int times2 = 0;
					entities2.add(new EntityBag(entity, entity.posX, (entity.posX * factorXY) + bufferXY, (entity.posX * factorXZ) + bufferXZ));
				}
			}
			++times;
		}
		return entities2;
	}
	/**
	 * this method creates a new line that is equal to the rayTrace of the given entity.
	 * @param entity The entity whose rayTrace this method will use.
	 * @param light The maximum lenght this rayTrace can have.
	 * @return a new Line that is the rayTrace of the given entity.
	 */
	public static Line fromRaytrace(Entity entity, double lenght){
		Position pos1 = new Position(entity.posX, entity.height * 0.8 + entity.getEntityBoundingBox().minY, entity.posZ);
		Position pos2 = new Position(entity.getLookVec());
		Vec3 v3 = new Vec3(pos1.x, pos1.y, pos1.z);
		Position pos4 = new Position(v3.addVector(pos2.x * lenght, pos2.y * lenght, pos2.z * lenght));
		Position pos3 = new Position(entity.posX * pos2.x, (entity.height * 0.8 + entity.getEntityBoundingBox().minY) * pos2.y, entity.posZ * pos2.z);
		return new Line(pos1, pos4);
	}
	/**
	 * This method gives the nearest entity in this line to the given position.
	 * @param world The world to look for the entity.
	 * @param entityClass The class the entities must have or extend.
	 * @param position the position where the entity must be close too.
	 * @param exclude An entity that doesn't count in this method.
	 * @return The entity in this line that is the closest from the given position.
	 */
	public Entity getNearestEntity(World world, Class entityClass, Position position, Entity exclude){
		List entities = getEntities(world, entityClass, false);
		double maxDistance = -1;
		int times = 0;
		Entity entity2 = null;
		while(times < entities.size()){
			Entity entity = (Entity) entities.get(times);
			double distance = position.getDistance(new Position(entity));
			if(entity != exclude){
				if(maxDistance == -1){
					maxDistance = distance;
					entity2 = entity;
				}
				else if(distance < maxDistance){
					maxDistance = distance;
					entity2 = entity;
				}
			}
			++times;
		}
		return entity2;
	}
	/**
	 * This method does the same as the other method, but put the entity in a bag.
	 * @param world
	 * @param position
	 * @param entityClass
	 * @param exclude
	 * @return
	 */
	public EntityBag getNearestEntity(World world, Position position, Class entityClass, Entity exclude){
		List entities = getEntities(world, entityClass, true);
		double maxDistance = -1;
		int times = 0;
		EntityBag entity2 = null;
		while(times < entities.size()){
			EntityBag entity = (EntityBag) entities.get(times);
			double distance = position.getDistance(new Position(entity.x, entity.y, entity.z));
			if(entity.entity != exclude){
				if(maxDistance == -1){
					maxDistance = distance;
					entity2 = entity;
				}
				else if(distance < maxDistance){
					maxDistance = distance;
					entity2 = entity;
				}
			}
			++times;
		}
		return entity2;
	}
	/**
	 * This method writes the data of the positions to the given NBTTagCompound.
	 * @param nbt the NBTTagCompound to save the data.
	 */
	public void writeToNBT(NBTTagCompound nbt){
		position1.writeToNBT(nbt, "position1");
		position2.writeToNBT(nbt, "position2");
	}
	/**
	 * Reads the info of positions that are written in the NBTTagCompound.
	 * The line will be useless if the right data is not stored.
	 * @param nbt The NBTTagCompound to read.
	 */
	public void readFromNBT(NBTTagCompound nbt){
		position1 = new Position();
		position2 = new Position();
		position1.readFromNBT(nbt, "position1");
		position2.readFromNBT(nbt, "position2");
		refresh();
	}
	/**
	 * Creates a line from a NBTTagCompound.
	 * Be sure this is the same NBTTagCompound as the one where you saved the data.
	 * @param nbt The NBTTagCompound to read.
	 * @return
	 */
	public static Line createLineFromNBT(NBTTagCompound nbt){
		Line line = new Line();
		line.readFromNBT(nbt);
		return line;
	}
	/**
	 * This method gives a list of the blocks in the line.
	 * @param world The world to take the blocks.
	 * @param acceptWeakBlocks If this is false, it will not set blocks in the list with a hardness of 0.
	 * @return A list of blocks in the line.
	 */
	public List getBlocks(World world, boolean acceptWeakBlocks){
		double speedX = ExtraUtils.divineAccurate(distanceXT, distance);
		double speedY = ExtraUtils.divineAccurate(distanceYT, distance);
		double speedZ = ExtraUtils.divineAccurate(distanceZT, distance);
		if(!ExtraUtils.isNormalNumber(speedX)){
			speedX = 0;
		}
		if(!ExtraUtils.isNormalNumber(speedY)){
			speedY = 0;
		}
		if(!ExtraUtils.isNormalNumber(speedZ)){
			speedZ = 0;
		}
		int maxTimes = (int)distance + 1;
		int times = 0;
		List blocks = new ArrayList();
		double x = position1.x;
		double y = position1.y;
		double z = position1.z;
		while (times <= maxTimes){
			IBlockState blockstate = world.getBlockState(new BlockPos((int) x, (int) y, (int) z));
			if(acceptWeakBlocks || blockstate.getBlock().getBlockHardness(world, new BlockPos((int) x, (int) y, (int) z)) != 0){
				BlockBag bag = new BlockBag(blockstate, x, y, z);
				blocks.add(bag);
			}
			++times;
			x += speedX;
			y += speedY;
			z += speedZ;
		}
		return blocks;
	}
	/**
	 * This method gives the closest block in the line.
	 * @param world The world to look for the blocks.
	 * @param acceptWeakBlocks If this is true, blocks with a hardness of 0 will not count.
	 * @param pos if this is 1 or tinier, this method will search from position1, position2 otherwise.
	 * @return The nearest block in the line.
	 */
	public BlockBag getNearestBlockInLine(World world, boolean acceptWeakBlocks, Position pos){
		List blocks = getBlocks(world, acceptWeakBlocks);
		int times = 0;
		double distance = 1.0 / 0.0;
		BlockBag block = null;
		while(times < blocks.size()){
			BlockBag bag = (BlockBag) blocks.get(times);
			Position position = new Position(bag.xd, bag.yd, bag.zd);
			if(pos.getDistance(position) < distance){
				block = bag;
				distance = pos.getDistance(position);
			}
			++times;
		}
		return block;
	}
	/**
	 * This method makes a new line that will come not further than the nearest block from the given position.
	 * @param world The world to look for the blocks.
	 * @param acceptWeakBlocks If this is true, blocks with a hardness of 0 will count.
	 * @param position If this is 1 or tinier, it will look from position1, position2 otherwise.
	 * @return a new line that only reaches the nearest block from the old line.
	 */
	public Line toNearestBlock(World world, boolean acceptWeakBlocks, int position){
		Position p;
		Position q;
		if(position <= 1){
			p = position1;
			q = position2;
		}
		else {
			p = position2;
			q = position1;
		}
		BlockBag bag = getNearestBlockInLine(world, acceptWeakBlocks, p);
		if(bag != null){
			return new Line(p, new Position(bag.xd, bag.yd, bag.zd));
		}
		else {
			return this;
		}
	}
	/**
	 * Creates a new Line that starts from the given position and stops at the nearest entity of the right class.
	 * @param world The world to look for the entity.
	 * @param entityClass The class the entity must have or extend.
	 * @param position if it is 1 or tinier, this method will start looking from position1, position2 otherwise.
	 * @param exclude An entity that will not count, it can be null.
	 * @return A new line from the given position to the closest entity.
	 */
	public Line toNearestEntity(World world, Class entityClass, int position, Entity exclude){
		Position pos;
		if(position <= 1){
			pos = position1;
		}
		else {
			pos = position2;
		}
		EntityBag entity = getNearestEntity(world, pos, entityClass, exclude);
		if(entity != null){
			if(entity.entity != null){
				return new Line(pos, new Position(entity.x, entity.y, entity.z));
			}
			else {
				return this;
			}
		}
		else {
			return this;
		}
	}
}
