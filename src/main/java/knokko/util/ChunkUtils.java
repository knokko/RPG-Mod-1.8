package knokko.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkUtils {
	
	public static void spawnOre(IBlockState ore, ChunkPrimer primer, int x, int y, int z, Random random, IBlockState old){
		try {
			IBlockState block1 = primer.getBlockState(x, y, z);
			IBlockState block2 = primer.getBlockState(x, y + 1, z);
			IBlockState block3 = primer.getBlockState(x, y, z + 1);
			IBlockState block4 = primer.getBlockState(x, y + 1, z + 1);
			IBlockState block5 = primer.getBlockState(x + 1, y, z);
			IBlockState block6 = primer.getBlockState(x + 1, y + 1, z);
			IBlockState block7 = primer.getBlockState(x + 1, y, z + 1);
			IBlockState block8 = primer.getBlockState(x + 1, y + 1, z + 1);
			if(block1 == old){
				primer.setBlockState(x, y, z, ore);
			}
			if(block2 == old){
				primer.setBlockState(x, y + 1, z, ore);
			}
			if(block3 == old){
				primer.setBlockState(x, y, z + 1, ore);
			}
			if(block4 == old){
				primer.setBlockState(x, y + 1, z + 1, ore);
			}
			if(block5 == old){
				primer.setBlockState(x + 1, y, z, ore);
			}
			if(block6 == old){
				primer.setBlockState(x + 1, y + 1, z, ore);
			}
			if(block7 == old){
				primer.setBlockState(x + 1, y, z + 1, ore);
			}
			if(block8 == old){
				primer.setBlockState(x + 1, y + 1, z + 1, ore);
			}
		} catch(Throwable throwable){}
	}
	
	public static void spawnOres(ChunkPrimer primer, IBlockState ore, IBlockState old, int ammount, int maxHeight, Random random){
		int times = 0;
		while(times <= ammount){
			spawnOre(ore, primer, random.nextInt(16), random.nextInt(maxHeight) + 1, random.nextInt(15), random, old);
			++times;
		}
	}
	
	public static void fill(ChunkPrimer primer, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, IBlockState block){
		int x = minX;
		while(x <= maxX){
			int y = minY;
			while(y <= maxY){
				int z = minZ;
				while(z <= maxZ){
					primer.setBlockState(x, y, z, block);
					++z;
				}
				++y;
			}
			++x;
		}
	}
}
