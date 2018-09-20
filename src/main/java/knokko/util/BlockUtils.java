package knokko.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockUtils {
	public static void placeBlockInAir(World world, int x, int y, int z, IBlockState block){
		if(world.isAirBlock(new BlockPos(x, y, z))){
			world.setBlockState(new BlockPos(x, y, z), block);
		}
	}
	public static int getHighestBlockHeight(int x, int z, World world, IBlockState block){
		int y = 256;
		while(world.getBlockState(new BlockPos(new BlockPos(x, y, z))) == block){
			return y;
		}
		while(y >= 0 && world.getBlockState(new BlockPos(new BlockPos(x, y, z))) != block){
			--y;
		}
		return y;
	}
	public static int getLowestBlockHeight(int x, int z, World world, IBlockState block){
		int y = 0;
		while(world.getBlockState(new BlockPos(new BlockPos(x, y, z))) == block){
			return y;
		}
		while(y <= 256 && world.getBlockState(new BlockPos(new BlockPos(x, y, z))) != block){
			++y;
		}
		return y;
	}
	public static int getHighestBlock(World world, int x, int z){
		int y = 260;
		while(y >= 0){
			IBlockState state = world.getBlockState(new BlockPos(x, y, z));
			if(state.getBlock().getBlockHardness(world, new BlockPos(x, y, z)) == 0){
				--y;
			}
			else {
				return y;
			}
		}
		return y;
	}
	public static boolean isPositionSafeForEnderman(World world, int x, int y, int z){
		IBlockState block = world.getBlockState(new BlockPos(new BlockPos(x, y, z)));
		IBlockState block2 = world.getBlockState(new BlockPos(x, y, z));
		if(block.getBlock().getBlockHardness(world, new BlockPos(x, y, z)) == 0 && block2.getBlock().getBlockHardness(world, new BlockPos(x, y, z)) == 0){
			if(!(block2.getBlock() instanceof BlockLiquid) && !(block.getBlock() instanceof BlockLiquid)){
				if(world.isRaining()){
					if(getHighestBlock(world, x, z) > y){
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return true;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	public static int getHighestSafePointForEnderman(World world, int x, int z){
		int y = 260;
		while(y >= 0){
			IBlockState block = world.getBlockState(new BlockPos(new BlockPos(x, y, z)));
			if(block.getBlock().getBlockHardness(world, new BlockPos(new BlockPos(x, y, z))) == 0){
				--y;
			}
			else {
				if(isPositionSafeForEnderman(world, x, y + 1, z)){
					return y;
				}
				else {
					--y;
				}
			}
		}
		return y;
	}
	
	public static Block randomBlock(Random random, Block... block){
		return block[random.nextInt(block.length)];
	}
}
