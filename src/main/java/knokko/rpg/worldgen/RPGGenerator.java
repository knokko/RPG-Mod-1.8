package knokko.rpg.worldgen;

import java.util.Random;

import knokko.rpg.blocks.main.RPGBlocks;
import knokko.rpg.data.WorldData;
import knokko.rpg.main.KnokkoRPG;
import knokko.util.BlockUtils;
import knokko.util.BuildUtils;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class RPGGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		String structure = WorldData.getChunkStructure(world, chunkX, chunkZ);
		if(structure.startsWith("build ")){
			
		}
		if(!structure.isEmpty())
			return;
		int dimension = world.provider.getDimensionId();
		int randomX = chunkX * 16 + random.nextInt(16);
		int randomZ = chunkZ * 16 + random.nextInt(16);
		BiomeGenBase biome = world.getBiomeGenForCoords(new BlockPos(randomX, 10, randomZ));
		int y = BlockUtils.getHighestBlock(world, randomX, randomZ);
		int randomizer = random.nextInt(1000);
		if(dimension == KnokkoRPG.fireLandsId){
			if(random.nextInt(300) == 17){
				BuildUtils.makeVulcano(world, RPGBlocks.basalt, random, randomX, 51, randomZ);
			}
		}
		if(dimension == KnokkoRPG.voidWorldId){
			if(randomizer == 15)
				RPGBuildings.buildVoidFortress(world, random, chunkX, chunkZ);
		}
		if(dimension == 0){
			if(y >= 0){
				if(randomizer == 10)
					RPGBuildings.buildHouse(world, random, randomX, y, randomZ, randomX + random.nextInt(48) + 16, y + random.nextInt(10) + 10, randomZ + random.nextInt(20) + 10, Blocks.brick_block, Blocks.planks);
				if(randomizer == 50 && (biome == BiomeGenBase.plains || biome == BiomeGenBase.desert))
					RPGBuildings.buildEmpireVillage(world, randomX, randomZ, random);
				if(randomizer == 100)
					RPGBuildings.buildVoidPortal(world, random, chunkX, chunkZ);
			}
		}
	}
}
