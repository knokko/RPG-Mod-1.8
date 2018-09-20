package knokko.rpg.teleporters;

import knokko.rpg.blocks.main.RPGBlocks;
import knokko.rpg.data.WorldData;
import knokko.rpg.main.KnokkoRPG;
import knokko.util.BuildUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class VoidTeleporter extends Teleporter {
	
	public final WorldServer world;

	public VoidTeleporter(WorldServer worldIn) {
		super(worldIn);
		world = worldIn;
	}
	
	@Override
	public void placeInPortal(Entity entity, float rotation){
		if(world.getMapStorage() == null)
			world.init();
		if(!placeInExistingPortal(entity, rotation)){
			makePortal(entity);
			placeInExistingPortal(entity, rotation);
		}
	}
	
	@Override
	public boolean placeInExistingPortal(Entity entity, float rotation){
		double relativeX = Math.abs(entity.posX - ((int)(entity.posX / 16) * 16));
		double relativeZ = Math.abs(entity.posZ - ((int)(entity.posZ / 16) * 16));
		if(relativeX < 5)
			entity.posX += entity.posX >= 0 ? 4 : -4;
		if(relativeX > 11)
			entity.posX -= entity.posX >= 0 ? 4 : -4;
		if(relativeZ < 5)
			entity.posZ += entity.posZ >= 0 ? 4 : -4;
		if(relativeZ > 11)
			entity.posZ -= entity.posZ >= 0 ? 4 : -4;
		BlockPos destination = entity.getPosition();
		if(world.getBlockState(destination.down()) == RPGBlocks.voidportal.getDefaultState()){
			entity.posX = ((int)(entity.posX / 16) * 16) + (entity.posX > 0 ? 1 : -1);
			entity.posY += 10;
			entity.posZ = ((int)(entity.posZ / 16) * 16) + (entity.posZ > 0 ? 1 : -1);
			entity.motionX = 0;
			entity.motionY = 0;
			entity.motionZ = 0;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean makePortal(Entity entity){
		int chunkX = (int) (entity.posX / 16);
		int chunkZ = (int) (entity.posZ / 16);
		int minX = chunkX * 16;
		int minY = (int) entity.posY - 1;
		int minZ = chunkZ * 16;
		if(entity.posX < 0)
			minX -= 16;
		if(entity.posZ < 0)
			minZ -= 16;
		int maxX = minX + 15;
		int maxY = minY + 9;
		int maxZ = minZ + 15;
		BuildUtils.fillBox(world, minX, minY, minZ, maxX, maxY, maxZ, Blocks.air);
		BuildUtils.makeWalls(world, minX, minY, minZ, maxX, maxY, maxZ, Blocks.obsidian);
		BuildUtils.makeWalls(world, minX + 1, minY, minZ + 1, maxX - 1, maxY, maxZ - 1, Blocks.obsidian);
		BuildUtils.makeWalls(world, minX + 2, minY, minZ + 2, maxX - 2, maxY, maxZ - 2, Blocks.obsidian);
		BuildUtils.makeFloor(world, minX + 3, minY, minZ + 3, maxX - 3, maxZ - 3, RPGBlocks.voidportal);
		BuildUtils.makeFloor(world, minX, minY - 1, minZ, maxX, maxZ, Blocks.obsidian);
		WorldData.setChunkStructure(world, "Void Portal", chunkX, chunkZ);
		WorldData.markGenerateBlock(world, minX, minY, minZ, maxX, maxY, maxZ);
		return true;
	}
}
