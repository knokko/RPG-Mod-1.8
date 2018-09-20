package knokko.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BuildUtils {
	/**
	 * This method makes a box from 2 positions and fills this box with the given block.
	 * @param world The world to place the blocks.
	 * @param pos1 The first position.
	 * @param pos2 The second position.
	 * @param block The block this method uses to fill the box.
	 */
	public static void fillBox(World world, Position pos1, Position pos2, Block block){
		fillBox(world, pos1.x, pos1.y, pos1.z, pos2.x, pos2.y, pos2.z, block);
	}
	/**
	 * This method fills a box with the given block between the given coordinates.
	 * @param world The world to place the blocks.
	 * @param x1 The first x position.
	 * @param y1 The first y position.
	 * @param z1 The first z position.
	 * @param x2 The second x position.
	 * @param y2 The second y position.
	 * @param z2 The second z position.
	 * @param block The block this method will use to fill the box.
	 */
	public static void fillBox(World world, double x1, double y1, double z1, double x2, double y2, double z2, Block block){
		fillBox(world, (int)x1, (int)y1, (int)z1, (int)x2, (int)y2, (int)z2, block);
	}
	/**
	 * This method fills a box with the given block between the given coordinates.
	 * @param world The world to place the blocks.
	 * @param x1 The first x position.
	 * @param y1 The first y position.
	 * @param z1 The first z position.
	 * @param x2 The second x position.
	 * @param y2 The second y position.
	 * @param z2 The second z position.
	 * @param block The block this method will use to fill the box.
	 */
	public static void fillBox(World world, float x1, float y1, float z1, float x2, float y2, float z2, Block block){
		fillBox(world, (int)x1, (int)y1, (int)z1, (int)x2, (int)y2, (int)z2, block);
	}
	/**
	 * This method creates a hollow box with the given block between the given coordinates.
	 * @param world The world to place the blocks.
	 * @param x1 The first x position.
	 * @param y1 The first y position.
	 * @param z1 The first z position.
	 * @param x2 The second x position.
	 * @param y2 The second y position.
	 * @param z2 The second z position.
	 * @param block The block this method will use to create the box.
	 */
	public static void makeHollowBox(World world, int x1, int y1, int z1, int x2, int y2, int z2, Block block){
		fillBox(world, x1, y1, z1, x2, y2, z1, block);
		fillBox(world, x1, y1, z1, x1, y2, z2, block);
		fillBox(world, x1, y1, z1, x2, y1, z2, block);
		fillBox(world, x1, y2, z1, x2, y2, z2, block);
		fillBox(world, x1, y1, z2, x2, y2, z2, block);
		fillBox(world, x2, y1, z1, x2, y2, z2, block);
	}
	/**
	 * This method creates a hollow box with the given block between the given coordinates.
	 * @param world The world to place the blocks.
	 * @param x1 The first x position.
	 * @param y1 The first y position.
	 * @param z1 The first z position.
	 * @param x2 The second x position.
	 * @param y2 The second y position.
	 * @param z2 The second z position.
	 * @param block The block this method will use to create the box.
	 */
	public static void makeHollowBox(World world, float x1, float y1, float z1, float x2, float y2, float z2, Block block){
		makeHollowBox(world, (int)x1, (int)y1, (int)z1, (int)x2, (int)y2, (int)z2, block);
	}
	/**
	 * This method creates a hollow box with the given block between the given coordinates.
	 * @param world The world to place the blocks.
	 * @param x1 The first x position.
	 * @param y1 The first y position.
	 * @param z1 The first z position.
	 * @param x2 The second x position.
	 * @param y2 The second y position.
	 * @param z2 The second z position.
	 * @param block The block this method will use to create the box.
	 */
	public static void makeHollowBox(World world, double x1, double y1, double z1, double x2, double y2, double z2, Block block){
		makeHollowBox(world, (int)x1, (int)y1, (int)z1, (int)x2, (int)y2, (int)z2, block);
	}
	/**
	 * This method creates a hollow box with the given block between the given positions.
	 * @param world The world to place the blocks.
	 * @param pos1 The first position.
	 * @param pos2 The second position.
	 * @param block The block this method will use to create the box.
	 */
	public static void makeHollowBox(World world, Position pos1, Position pos2, Block block){
		makeHollowBox(world, pos1.x, pos1.y, pos1.z, pos2.x, pos2.y, pos2.z, block);
	}
	/**
	 * This method creates 4 walls, the walls fills the space between the given coordinates.
	 * @param world The world to place the blocks.
	 * @param x1 The first x position.
	 * @param y1 The first y position.
	 * @param z1 The first z position.
	 * @param x2 The second x position.
	 * @param y2 The second y position.
	 * @param z2 The second z position.
	 * @param block The block this method will use to build the walls.
	 */
	public static void makeWalls(World world, int x1, int y1, int z1, int x2, int y2, int z2, Block block){
		makeXWall(world, x1, y1, z1, x2, y2, block); //x to x2 on z1
		makeZWall(world, x1, y1, z1, y2, z2, block); //z to z2 on x1
		makeXWall(world, x1, y1, z2, x2, y2, block); //x to x2 on z2
		makeZWall(world, x2, y1, z1, y2, z2, block); //z to z2 on x2
	}
	/**
	 * This method creates 4 walls, the walls fills the space between the given coordinates.
	 * @param world The world to place the blocks.
	 * @param x1 The first x position.
	 * @param y1 The first y position.
	 * @param z1 The first z position.
	 * @param x2 The second x position.
	 * @param y2 The second y position.
	 * @param z2 The second z position.
	 * @param block The block this method will use to build the walls.
	 */
	public static void makeWalls(World world, double x1, double y, double z1, double x2, double y2, double z2, Block block){
		makeWalls(world, (int)x1, (int)y, (int)z1, (int)x2, (int)y2, (int)z2, block);
	}
	public static void makeXWall(World world, int x1, int y1, int z, int x2, int y2, Block block){
		boolean isFinished = false;
		int x = x1;
		int y = y1;
		while(!isFinished){
			if(x < x1){
				x = x1;
			}
			if(y < y1){
				y = y1;
			}
			if(x > x2){
				x = x1;
				++y;
			}
			if(y > y2){
				isFinished = true;
			}
			if(x >= x1 && x <= x2 && y >= y1 && y <= y2){
				world.setBlockState(new BlockPos(x,y,z), block.getDefaultState());
				++x;
			}
		}
	}
	public static void makeZWall(World world, int x, int y1, int z1, int y2, int z2, Block block){
		boolean isFinished = false;
		int z = z1;
		int y = y1;
		while(!isFinished){
			if(z < z1){
				z = z1;
			}
			if(y < y1){
				y = y1;
			}
			if(z > z2){
				z = z1;
				++y;
			}
			if(y > y2){
				isFinished = true;
			}
			if(z >= z1 && z <= z2 && y >= y1 && y <= y2){
				world.setBlockState(new BlockPos(x,y,z), block.getDefaultState());
				++z;
			}
		}
	}
	public static void makeFloor(World world, int x1, int y, int z1, int x2, int z2, Block block){
		boolean isFinished = false;
		int z = z1;
		int x = x1;
		while(!isFinished){
			if(z < z1){
				z = z1;
			}
			if(x < x1){
				x = x1;
			}
			if(x > x2){
				x = x1;
				++z;
			}
			if(z > z2){
				isFinished = true;
			}
			if(z >= z1 && z <= z2 && x >= x1 && x <= x2){
				world.setBlockState(new BlockPos(x,y,z), block.getDefaultState());
				++x;
			}
		}
	}
	public static void fillBox(World world, int x1, int y1, int z1, int x2, int y2, int z2, Block block){
		int x = x1;
		boolean isFinished = false;
		while(!isFinished){
			if(x < x1){
				x = x1;
			}
			else if(x > x2){
				isFinished = true;
			}
			else {
				makeZWall(world, x, y1, z1, y2, z2, block);
				++x;
			}
		}
	}
	public static void makeHollowPyramide(World world, int x1, int y, int z1, int x2, int z2, Block block){
		int i = 0;
		makeFloor(world, x1, y, z1, x2, z2, block);
		while(x1 + i <= x2 - i && z1 + i <= z2 - i){
			makeWalls(world, x1 + i, y + i, z1 + i, x2 - i, y + i, z2 - i, block);
			++i;
		}
	}
	public static void makeHollowPyramide(World world, double x1, double y, double z1, double x2, double z2, Block block){
		makeHollowPyramide(world, (int)x1, (int)y, (int)z1, (int)x2, (int)z2, block);
	}
	public static void makeFilledPyramide(World world, int x1, int y, int z1, int x2, int z2, Block block){
		int i = 0;
		while(x1 + i <= x2 - i && z1 + i <= z2 - i){
			makeFloor(world, x1 + i, y + i, z1 + i, x2 - i, z2 - i, block);
			++i;
		}
	}
	public static void makeFilledPyramide(World world, double x1, double y, double z1, double x2, double z2, Block block){
		makeFilledPyramide(world, (int)x1, (int)y, (int)z1, (int)x2, (int)z2, block);
	}
	public static void placeBlockInCircle(World world, int x, int y, int z, Block block, Position base, double range, double buffer){
		Position position = new Position(x, y, z);
		if(position.getDistance(base) >= range - buffer && position.getDistance(base) <= range + buffer){
			world.setBlockState(new BlockPos(x,y,z), block.getDefaultState());
		}
	}
	
	public static void fillCircle(World world, Position base, double range, double buffer, Block block){
		int y = base.intY();
		int x1 = base.intX() - ExtraUtils.fromDouble(range);
		int z1 = base.intZ() - ExtraUtils.fromDouble(range);
		int x2 = base.intX() + ExtraUtils.fromDouble(range);
		int z2 = base.intZ() + ExtraUtils.fromDouble(range);
		int x = x1;
		while(x <= x2){
			int z = z1;
			while(z <= z2){
				if(base.getDistance(new Position(x, y, z)) - buffer <= range)
					world.setBlockState(new BlockPos(x, y, z), block.getDefaultState());
				++z;
			}
			++x;
		}
	}
	
	public static void fillShpere(World world, Position base, double radius, double buffer, IBlockState block){
		int rad = ExtraUtils.fromDouble(radius);
		int minX = base.intX() - rad;
		int minY = base.intY() - rad;
		int minZ = base.intZ() - rad;
		int maxX = base.intX() + rad;
		int maxY = base.intY() + rad;
		int maxZ = base.intZ() + rad;
		int x = minX;
		while(x <= maxX){
			int y = minY;
			while(y <= maxY){
				int z = minZ;
				while(z <= maxZ){
					if(base.getDistance(new Position(x + 0.5, y + 0.5, z + 0.5)) + buffer <= radius)
						world.setBlockState(new BlockPos(x, y, z), block);
					++z;
				}
				++y;
			}
			++x;
		}
	}
	
	public static void makeSphere(World world, Position base, double radius, double buffer, IBlockState block){
		int rad = ExtraUtils.fromDouble(radius);
		int minX = base.intX() - rad;
		int minY = base.intY() - rad;
		int minZ = base.intZ() - rad;
		int maxX = base.intX() + rad;
		int maxY = base.intY() + rad;
		int maxZ = base.intZ() + rad;
		int x = minX;
		while(x <= maxX){
			int y = minY;
			while(y <= maxY){
				int z = minZ;
				while(z <= maxZ){
					if(Math.abs(radius - base.getDistance(new Position(x + 0.5, y + 0.5, z + 0.5))) <= buffer)
						world.setBlockState(new BlockPos(x, y, z), block);
					++z;
				}
				++y;
			}
			++x;
		}
	}
	
	public static void makeCircle(World world, Position base, double range, double buffer, Block block){
		int y = base.intY();
		int x = base.intX() - ExtraUtils.fromDouble(range);
		int z = base.intZ() - ExtraUtils.fromDouble(range);
		int z1 = base.intZ() - ExtraUtils.fromDouble(range);
		int z2 = base.intZ() + ExtraUtils.fromDouble(range);
		boolean isFinished = false;
		while(!isFinished){
			if(z >= z1 && z <= z2){
				makeXCircleLine(world, base, range, buffer, y, z, x, ExtraUtils.fromDouble(x + range * 2), block);
			}
			if(z < z1){
				z= z1;
			}
			++z;
			if(z > z2){
				isFinished = true;
			}
		}
	}
	private static void makeXCircleLine(World world, Position base, double range, double buffer, int y, int z, int x1, int x2, Block block){
		int x = x1;
		while(x <= x2){
			if(x < x1){
				x = x1;
			}
			placeBlockInCircle(world, x, y, z, block, base, range, buffer);
			++x;
		}
	}
	public static void makeCircleTower(World world, Position base, double range, double buffer, int height, Block block){
		int y = base.intY();
		int x = base.intX();
		int z = base.intZ();
		while(y <= height + base.intY()){
			if(y < base.intY()){
				y = base.intY();
			}
			else {
				makeCircle(world, new Position(x, y, z), range, buffer, block);
				++y;
			}
		}
	}
	public static void fillBox2(World world, int x1, int y1, int z1, int x2, int y2, int z2, Block block){
		fillBox(world, Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2), Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2), block);
	}
	public static void makeVulcano(World world, Block block, Random random, int x, int y, int z){
		int height = random.nextInt(40);
		int lenght = height;
		world.setBlockState(new BlockPos(x, y + height + 1, z), Blocks.lava.getDefaultState());
		int x2 = x - lenght;
		while(x2 <= x + lenght){
			int z2 = z - lenght;
			while(z2 <= z + lenght){
				try {
					int height2 = (int) ((new Position(x2, height, z2).getDistance(new Position(x, height, z))) * 2);
					fillBox(world, x2, y, z2, x2, y + height - height2, z2, block);
				} catch(Throwable throwable){
					System.out.println("an error occured during worldgen: ");
					throwable.printStackTrace();
				}
				++z2;
			}
			++x2;
		}
	}
	
	public static int placeBlockAtTop(World world, int x, int z, IBlockState state){
		int y = BlockUtils.getHighestBlock(world, x, z);
		if(y >= 0)
			world.setBlockState(new BlockPos(x, y, z), state);
		return y;
	}
	
	public static int placeBlockAtTop(World world, int x, int z, Block block){
		return placeBlockAtTop(world, x, z, block.getDefaultState());
	}
}
