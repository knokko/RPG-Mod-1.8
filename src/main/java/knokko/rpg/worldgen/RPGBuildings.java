package knokko.rpg.worldgen;

import java.util.Random;

import knokko.rpg.blocks.main.RPGBlocks;
import knokko.rpg.data.WorldData;
import knokko.rpg.entity.monster.EntityEmpire;
import knokko.util.BlockUtils;
import knokko.util.BuildUtils;
import knokko.util.ExtraUtils;
import knokko.util.Position;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class RPGBuildings {
	
	public static void buildHouse(World world, Random random, int x1, int y1, int z1, int x2, int y2, int z2, Block block1, Block block2){
		if(WorldData.isGenerateBlock(world, x1, y1, z1, x2, y2, z2))
			return;
		int glassLengtX = ExtraUtils.fromDouble((x2 - x1) * 0.1);
		int glassPosX = ExtraUtils.fromDouble(x1 + ((x2 - x1) * 0.25));
		int glassPosX2 = x2 - ExtraUtils.fromDouble(((x2 - x1) * 0.25)) - glassLengtX;
		int glassPosY = ExtraUtils.fromDouble(y1 + ((y2 - y1) * 0.2));
		int height = y2 - y1;
		int cubeHeight = (int) (height * 0.6);
		int glassHeight = ExtraUtils.fromDouble(cubeHeight * 0.2);
		int doorLenght = ExtraUtils.fromDouble((x2 - x1) * 0.1);
		int doorPosX = ExtraUtils.fromDouble(x1 + ((x2 - x1) * 0.5) - (doorLenght * 0.5));
		int doorHeight = ExtraUtils.fromDouble(cubeHeight * 0.25);
		int tables = ExtraUtils.fromDouble(((x2 - x1) * (z2 - z1)) * 0.01);
		BuildUtils.makeHollowBox(world, x1, y1, z1, x2, y1 + cubeHeight, z2, block1);
		BuildUtils.fillBox(world, x1 + 1, y1 + 1, z1 + 1, x2 - 1, y1 + cubeHeight - 1, z2 - 1, Blocks.air);
		BuildUtils.makeFilledPyramide(world, x1, y1 + cubeHeight + 1, z1, x2, z2, block2);
		BuildUtils.makeXWall(world, glassPosX, glassPosY, z1, glassPosX + glassLengtX, glassPosY + glassHeight, Blocks.glass);
		BuildUtils.makeXWall(world, glassPosX2, glassPosY, z1, glassPosX2 + glassLengtX, glassPosY + glassHeight, Blocks.glass);
		BuildUtils.makeXWall(world, doorPosX, y1 + 1, z1, doorPosX + doorLenght, y1 + doorHeight, Blocks.air);
		while(tables > 0){
			int xLenght = 2 + random.nextInt(ExtraUtils.fromDouble((x2 - x1) * 0.1));
			int zLenght = ExtraUtils.fromDouble(xLenght * random.nextDouble());
			int tableX = x1 + 2 + random.nextInt(x2 - x1 - 5);
			int tableZ = z1 + 2 + random.nextInt(z2 - z1 - 4);
			BuildUtils.makeFloor(world, tableX, y1 + 1, tableZ, tableX + xLenght, tableZ + zLenght, RPGBlocks.table);
			--tables;
		}
		WorldData.markGenerateBlock(world, x1, y1, z1, x2, y2, z2);
		WorldData.setChunkStructure(world, "House", x1, z1, x2, z2);
	}
	
	public static void buildEmpireVillage(World world, int startX, int startZ, Random random){
		int startY = BlockUtils.getHighestBlock(world, startX, startZ);
		int chance = -5;
		int x = startX;
		int z = startZ;
		int previousDirection = -10;
		boolean bool = true;
		int direction = random.nextInt(4);
		while(chance <= 0 || random.nextInt(chance) == 0){
			if(bool)
				direction = random.nextInt(4);
			bool = false;
			if(previousDirection == direction)
				direction = previousDirection + 1;
			int length = 10 + random.nextInt(15);
			if(direction == 0){
				bool = buildEmpireRoad(world, random, x - 1, z + 1, x + 1, z - length);
				if(bool)
					z -= length + 2;
			}
			if(direction == 1){
				bool = buildEmpireRoad(world, random, x - 1, z - 1, x + length, z + 1);
				if(bool)
					x += length + 2;
			}
			if(direction == 2){
				bool = buildEmpireRoad(world, random, x - 1, z - 1, x + 1, z + length);
				if(bool)
					z += length + 2;
			}
			if(direction == 3){
				bool = buildEmpireRoad(world, random, x + 1, z - 1, x - length, z + 1);
				if(bool)
					x -= length + 2;
			}
			previousDirection = direction - 2;
			if(previousDirection < 0)
				previousDirection += 4;
			direction++;
			++chance;
		}
	}
	
	public static void buildEmpireHouse(World world, int x1, int y1, int z1, int x2, int y2, int z2, int doorX1, int doorZ1, int doorX2, int doorZ2, Random random){
		if(WorldData.isGenerateBlock(world, x1, y1, z1, x2, y2, z2))
			return;
		int minX = Math.min(x1, x2);
		int minY = Math.min(y1, y2);
		int minZ = Math.min(z1, z2);
		int maxX = Math.max(x1, x2);
		int maxY = Math.max(y1, y2);
		int maxZ = Math.max(z1, z2);
		int baseHeight = (int) ((y2 - y1) * 0.7);
		BuildUtils.makeWalls(world, x1, y1 + 1, z1, x2, y1 + baseHeight, z2, Blocks.stonebrick);
		BuildUtils.fillBox(world, x1 + 1, y1 + 1, z1 + 1, x2 - 1, y1 + baseHeight - 1, z2 - 1, Blocks.air);
		BuildUtils.fillBox(world, doorX1, y1 + 1, doorZ1, doorX2, y1 + 2, doorZ2, Blocks.air);
		BuildUtils.makeFilledPyramide(world, x1, y1 + baseHeight, z1, x2, z2, Blocks.obsidian);
		world.spawnEntityInWorld(new EntityEmpire(world, minX + 2 + random.nextInt(maxX - minX - 2), minY + 1, minZ + 2 + random.nextInt(maxZ - minZ - 2)));
		world.spawnEntityInWorld(new EntityEmpire(world, minX + 2 + random.nextInt(maxX - minX - 2), minY + 1, minZ + 2 + random.nextInt(maxZ - minZ - 2)));
		world.spawnEntityInWorld(new EntityEmpire(world, minX + 2 + random.nextInt(maxX - minX - 2), minY + 1, minZ + 2 + random.nextInt(maxZ - minZ - 2)));
		BuildUtils.makeFloor(world, x1, y1, z1, x2, z2, RPGBlocks.paved_obsidian);
		WorldData.markGenerateBlock(world, minX, minY, minZ, maxX, maxY, maxZ);
		WorldData.setChunkStructure(world, "Empire Village", minX, minZ, maxX, maxZ);
	}
	
	public static void buildEmpireTower(World world, Block walls, Block ground, int x, int y, int z, int size, int height, int doorX1, int doorZ1, int doorX2, int doorZ2){
		if(WorldData.isGenerateBlock(world, x - size, y, z - size, x + size, y + height, z + size))
			return;
		BuildUtils.fillCircle(world, new Position(x, y, z), size, 0.5, ground);
		BuildUtils.makeCircleTower(world, new Position(x, y, z), size, 0.5, height, walls);
		int s = size;
		y += height;
		while(s >= 0){
			BuildUtils.makeCircle(world, new Position(x, y, z), s, 0.5, ground);
			--s;
			++y;
		}
		y -= height;
		y -= size;
		world.spawnEntityInWorld(new EntityEmpire(world, x, y + 1, z));
		world.spawnEntityInWorld(new EntityEmpire(world, x, y + 1, z));
		BuildUtils.fillBox(world, doorX1, y, doorZ1, doorX2, y + 2, doorZ2, Blocks.air);
		WorldData.markGenerateBlock(world, x - size, y, z - size, x + size, y + height, z + size);
	}
	
	public static void buildVoidPortal(World world, Random random, int chunkX, int chunkZ){
		int minX = chunkX * 16;
		int minY = random.nextInt(15) + 5;
		int minZ = chunkZ * 16;
		if(chunkX < 0)
			minX -= 16;
		if(chunkZ < 0)
			minZ -= 16;
		int maxX = minX + 15;
		int maxY = minY + 9;
		int maxZ = minZ + 15;
		BuildUtils.makeSphere(world, new Position(minX + 8, maxY, minZ + 8), 15, 0.5, Blocks.obsidian.getDefaultState());
		BuildUtils.fillBox(world, minX, minY, minZ, maxX, maxY, maxZ, Blocks.air);
		BuildUtils.makeWalls(world, minX, minY, minZ, maxX, maxY, maxZ, Blocks.obsidian);
		BuildUtils.makeWalls(world, minX + 1, minY, minZ + 1, maxX - 1, maxY, maxZ - 1, Blocks.obsidian);
		BuildUtils.makeWalls(world, minX + 2, minY, minZ + 2, maxX - 2, maxY, maxZ - 2, Blocks.obsidian);
		BuildUtils.makeFloor(world, minX + 3, minY, minZ + 3, maxX - 3, maxZ - 3, RPGBlocks.voidportal);
		BuildUtils.makeFloor(world, minX, minY - 1, minZ, maxX, maxZ, Blocks.obsidian);
		WorldData.setChunkStructure(world, "Void Portal", chunkX, chunkZ);
		WorldData.markGenerateBlock(world, minX - 8, maxY - 16, minZ - 8, maxX + 8, maxY + 16, maxZ + 8);
	}
	
	public static void buildVoidFortress(World world, Random random, int chunkX, int chunkZ){
		int startX = chunkX * 16 + 8;
		int startZ = chunkZ * 16 + 8;
		int startY = BlockUtils.getHighestBlock(world, startX, startZ);
		if(startY <= 10)
			startY = 20 + random.nextInt(200);
		BuildUtils.fillShpere(world, new Position(startX + 0.5, startY, startZ + 0.5), 15, 0.5, Blocks.air.getDefaultState());
		BuildUtils.makeSphere(world, new Position(startX + 0.5, startY, startZ + 0.5), 15, 0.5, RPGBlocks.voidbrick.getDefaultState());
		WorldData.markGenerateBlock(world, startX - 15, startY - 15, startZ - 15, startX + 15, startY + 15, startZ + 15);
		WorldData.setChunkStructure(world, "Void Fortress", startX - 15, startZ - 15, startX + 15, startZ + 15);
		int x = startX - 25;
		int y = startY - 5;
		int z = startZ;
		int chance = -5;
		int prevDirection = 3;
		int direction = 3;
		while(chance <= 0 || random.nextInt(chance) == 0){
			BuildUtils.makeFloor(world, x - 5, y, z - 5, x + 5, z + 5, RPGBlocks.voidbrick);
			if(random.nextInt(5) == 0){
				BuildUtils.makeWalls(world, x - 5, y + 10, z - 5, x + 5, y + 30, z + 5, RPGBlocks.voidbrick);
				BuildUtils.makeHollowPyramide(world, x - 5, y + 30, z - 5, x + 5, z + 5, RPGBlocks.voidbrick);
			}
			else
				BuildUtils.makeFloor(world, x - 5, y + 10, z - 5, x + 5, z + 5, RPGBlocks.voidbrick);
			if(direction != 0 && prevDirection != 2)
				BuildUtils.makeXWall(world, x - 5, y, z - 5, x + 5, y + 10, RPGBlocks.voidbrick);
			if(direction != 1 && prevDirection != 3)
				BuildUtils.makeZWall(world, x + 5, y, z - 5, y + 10, z + 5, RPGBlocks.voidbrick);
			if(direction != 2 && prevDirection != 0)
				BuildUtils.makeXWall(world, x - 5, y, z + 5, x + 5, y + 10, RPGBlocks.voidbrick);
			if(direction != 3 && prevDirection != 1)
				BuildUtils.makeZWall(world, x - 5, y, z - 5, y + 10, z + 5, RPGBlocks.voidbrick);
			if(direction + 2 == prevDirection || direction - 2 == prevDirection)
				direction += 2;
			if(direction > 3)
				direction -= 4;
			if(direction == 0){
				if(buildVoidTunnel(world, random, x - 5, y, z - 44, x + 5, y + 10, z - 6))
					z -= 50;
			}
			if(direction == 1){
				if(buildVoidTunnel(world, random, x + 6, y, z - 5, x + 44, y + 10, z + 5))
					x += 50;
			}
			if(direction == 2){
				if(buildVoidTunnel(world, random, x - 5, y, z + 6, x + 5, y + 10, z + 44))
					z += 50;
			}
			if(direction == 3){
				if(buildVoidTunnel(world, random, x - 44, y, z - 5, x - 6, y + 10, z + 5))
					x -= 50;
			}
			prevDirection = direction;
			direction = random.nextInt(4);
			++chance;
		}
	}
	
	private static boolean buildVoidTunnel(World world, Random random, int x1, int y1, int z1, int x2, int y2, int z2){
		//if(WorldData.isGenerateBlock(world, x1, y1, z1, x2, y2, z2))
			//return false;
		//System.out.println("x1 = " + x1 + " y1 = " + y1 + " z1 = " + z1 + " x2 = " + x2 + " y2 = " + y2 + " z2 = " + z2);
		BuildUtils.makeFloor(world, x1, y1, z1, x2, z2, RPGBlocks.voidbrick);
		BuildUtils.makeFloor(world, x1, y2, z1, x2, z2, RPGBlocks.voidbrick);
		boolean openX = Math.abs(z1 - z2) >= Math.abs(x1 - x2);
		if(openX){
			BuildUtils.makeZWall(world, x1, y1, z1, y2, z2, RPGBlocks.voidbrick);
			BuildUtils.makeZWall(world, x2, y1, z1, y2, z2, RPGBlocks.voidbrick);
		}
		else {
			BuildUtils.makeXWall(world, x1, y1, z1, x2, y2, RPGBlocks.voidbrick);
			BuildUtils.makeXWall(world, x1, y1, z2, x2, y2, RPGBlocks.voidbrick);
		}
		WorldData.markGenerateBlock(world, x1, y1, z1, x2, y2, z2);
		return true;
	}
	
	private static boolean buildEmpireRoad(World world, Random random, int x1, int z1, int x2, int z2){
		int minX = x1 < x2 ? x1 : x2;
		int minZ = z1 < z2 ? z1 : z2;
		int maxX = x1 < x2 ? x2 : x1;
		int maxZ = z1 < z2 ? z2 : z1;
		int y = BlockUtils.getHighestBlock(world, minX, minZ);
		if(WorldData.isGenerateBlock(world, minX, y - 5, minZ, maxX, y + 5, maxZ))
			return false;
		WorldData.setChunkStructure(world, "Empire Village", minX, minZ, maxX, maxZ);
		int x = minX;
		y = -10;
		int prevX = minX;
		int prevZ = minZ;
		int minY = 300;
		int maxY = 0;
		if(maxZ - minZ > maxX - minX){
			while(x <= maxX){
				int z = minZ;
				while(z <= maxZ){
					int prevY = y;
					y = BlockUtils.getHighestBlock(world, x, z);
					if(prevY >= 0 && Math.abs(y - prevY) > 3)
						return false;
					world.setBlockState(new BlockPos(x, y, z), Blocks.brick_block.getDefaultState());
					prevX = x;
					prevY = y;
					prevZ = z;
					if(y > maxY)
						maxY = y;
					if(y < minY)
						minY = y;
					++z;
				}
				++x;
				y = BlockUtils.getHighestBlock(world, x, z);
			}
			int distanceZ = maxZ - minZ;
			if(distanceZ > 15){
				if(random.nextBoolean()){
					int minHouseX = minX - random.nextInt(7) - 5;
					int maxHouseX = minX - 1;
					int minHouseZ = minZ + random.nextInt(distanceZ / 2);
					int maxHouseZ = minHouseZ + random.nextInt(maxZ - minHouseZ - 3) + 3;
					int doorZ = minHouseZ + (maxHouseZ - minHouseZ) / 2;
					int minHouseY = BlockUtils.getHighestBlock(world, maxHouseX, doorZ);
					int maxHouseY = minHouseY + 7 + random.nextInt(4);
					buildEmpireHouse(world, minHouseX, minHouseY, minHouseZ, maxHouseX, maxHouseY, maxHouseZ, maxHouseX, doorZ - 1, maxHouseX, doorZ + 1, random);
				}
				else if(random.nextInt(5) == 0){
					int radius = 3 + random.nextInt(3);
					int height = 8 + random.nextInt(7);
					int z = minZ + radius + random.nextInt(maxZ - minZ - radius);
					int minHouseY = BlockUtils.getHighestBlock(world, minX - 1, z);
					buildEmpireTower(world, Blocks.stonebrick, RPGBlocks.paved_obsidian, minX - 1 - radius, minHouseY, z, radius, height, minX - 1, z - 1, minX - 1, z + 1);
				}
				if(random.nextBoolean()){
					int minHouseX = maxX + 1;
					int maxHouseX = maxX + random.nextInt(7) + 5;
					int minHouseZ = minZ + random.nextInt(distanceZ / 2);
					int maxHouseZ = minHouseZ + random.nextInt(maxZ - minHouseZ - 3) + 3;
					int doorZ = minHouseZ + (maxHouseZ - minHouseZ) / 2;
					int minHouseY = BlockUtils.getHighestBlock(world, minHouseX, doorZ);
					int maxHouseY = minHouseY + 7 + random.nextInt(4);
					buildEmpireHouse(world, minHouseX, minHouseY, minHouseZ, maxHouseX, maxHouseY, maxHouseZ, minHouseX, doorZ - 1, minHouseX, doorZ + 1, random);
				}
				else if(random.nextInt(5) == 0){
					int radius = 3 + random.nextInt(3);
					int height = 8 + random.nextInt(7);
					int z = minZ + radius + random.nextInt(maxZ - minZ - radius);
					int minHouseY = BlockUtils.getHighestBlock(world, maxX + 1, z);
					buildEmpireTower(world, Blocks.stonebrick, RPGBlocks.paved_obsidian, maxX + 1 + radius, minHouseY, z, radius, height, maxX + 1, z - 1, maxX + 1, z + 1);
				}
			}
		}
		else {
			int z = minZ;
			while(z <= maxZ){
				x = minX;
				while(x <= maxX){
					int prevY = y;
					y = BlockUtils.getHighestBlock(world, x, z);
					if(prevY >= 0 && Math.abs(y - prevY) > 3)
						return false;
					world.setBlockState(new BlockPos(x, y, z), Blocks.brick_block.getDefaultState());
					prevX = x;
					prevY = y;
					prevZ = z;
					if(y > maxY)
						maxY = y;
					if(y < minY)
						minY = y;
					++x;
				}
				++z;
				y = BlockUtils.getHighestBlock(world, x, z);
			}
			int distanceX = maxX - minX;
			if(distanceX > 15){
				if(random.nextBoolean()){
					int minHouseZ = minZ - random.nextInt(7) - 5;
					int maxHouseZ = minZ - 1;
					int minHouseX = minX + random.nextInt(distanceX / 2);
					int maxHouseX = minHouseX + random.nextInt(maxX - minHouseX - 3) + 3;
					int doorX = minHouseX + (maxHouseX - minHouseX) / 2;
					int minHouseY = BlockUtils.getHighestBlock(world, doorX, maxHouseZ);
					int maxHouseY = minHouseY + 7 + random.nextInt(4);
					buildEmpireHouse(world, minHouseX, minHouseY, minHouseZ, maxHouseX, maxHouseY, maxHouseZ, doorX - 1, maxHouseZ, doorX + 1, maxHouseZ, random);
				}
				if(random.nextBoolean()){
					int minHouseZ = maxZ + 1;
					int maxHouseZ = maxZ + random.nextInt(7) + 5;
					int minHouseX = minX + random.nextInt(distanceX / 2);
					int maxHouseX = minHouseX + random.nextInt(maxX - minHouseX - 3) + 3;
					int doorX = minHouseX + (maxHouseX - minHouseX) / 2;
					int minHouseY = BlockUtils.getHighestBlock(world, doorX, minHouseZ);
					int maxHouseY = minHouseY + 7 + random.nextInt(4);
					buildEmpireHouse(world, minHouseX, minHouseY, minHouseZ, maxHouseX, maxHouseY, maxHouseZ, doorX - 1, minHouseZ, doorX + 1, minHouseZ, random);
				}
			}
		}
		if(minY <= maxY)
			WorldData.markGenerateBlock(world, minX, minY, minZ, maxX, maxY, maxZ);
		return true;
	}
}
