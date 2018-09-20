package knokko.rpg.worldgen.firelands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import knokko.rpg.blocks.main.RPGBlocks;
import knokko.rpg.entity.creature.EntityFireEye;
import knokko.rpg.entity.monster.EntityFireLander;
import knokko.rpg.entity.monster.EntityLavaShark;
import knokko.rpg.entity.monster.EntityPowerBlaze;
import knokko.rpg.entity.monster.FireDragon;
import knokko.util.ChunkUtils;
import knokko.util.Position;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class FireLandsChunkProvider implements IChunkProvider{
	
	public World world;
	
	public Random random = new Random();
	public NBTTagCompound heights = new NBTTagCompound();
	public NBTTagCompound biomes = new NBTTagCompound();
	public NBTTagCompound hasEntities = new NBTTagCompound();
	
	public int biome;
	
	public List monsterList = new ArrayList<SpawnListEntry>();
	
	public ChunkPrimer primer = new ChunkPrimer();
	
	public FireLandsChunkProvider(World worldObj){
		world = worldObj;
		monsterList.add(new SpawnListEntry(EntityPowerBlaze.class, 10, 4, 4));
		monsterList.add(new SpawnListEntry(EntityFireEye.class, 3, 4, 4));
		monsterList.add(new SpawnListEntry(EntityFireLander.class, 10, 4, 4));
	}
	
	@Override
	public boolean chunkExists(int x, int z) {
		return true;
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		if(random.nextInt(1000) == 351){
			biome = random.nextInt(4);
		}
		biomes.setInteger("x:" + x + "z:" + z, biome);
		if(biome == 0){
			return generateFireLands(x, z);
		}
		else if(biome == 1){
			return generateHighLands(x, z);
		}
		else if(biome == 2){
			return generateOcean(x, z);
		}
		else {
			return generateLowLands(x, z);
		}
	}
	
	public Chunk provideChunk(BlockPos pos){
		return provideChunk(pos.getX() * 16, pos.getZ() * 16);
	}

	@Override
	public void populate(IChunkProvider provider, int x, int z) {}

	@Override
	public boolean saveChunks(boolean b, IProgressUpdate update) {
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return true;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "FireLands";
	}

	@Override
	public List func_177458_a(EnumCreatureType type, BlockPos pos) {
		if(type == EnumCreatureType.MONSTER){
			return monsterList;
		}
		return null;
	}

	@Override
	public boolean func_177460_a(IChunkProvider provider, Chunk chunk, int x, int z) {
		return false;
	}

	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(Chunk chunk, int x, int z) {}

	@Override
	public void saveExtraData() {}
	
	public Chunk generateFireLands(int x, int z){
		ChunkPrimer primer = new ChunkPrimer();
		ChunkUtils.fill(primer, 0, 0, 0, 15, 50, 15, RPGBlocks.basalt.getDefaultState());
		ChunkUtils.fill(primer, 0, 51, 0, 15, 53, 15, Blocks.lava.getDefaultState());
		ChunkUtils.spawnOres(primer, RPGBlocks.ruby_ore.getDefaultState(), RPGBlocks.basalt.getDefaultState(), 2, 30, random);
		int times = 51;
		while(times < 65){
			makeHill(primer, x, z, times, 20, RPGBlocks.basalt.getDefaultState());
			++times;
		}
		return new Chunk(world, primer, x, z);
	}
	
	public Chunk generateHighLands(int x, int z){
		ChunkPrimer primer = new ChunkPrimer();
		ChunkUtils.fill(primer, 0, 0, 0, 15, 53, 15, RPGBlocks.basalt.getDefaultState());
		int times = 54;
		while(times < 70){
			makeHill(primer, x, z, times, 20, null);
			++times;
		}
		ChunkUtils.spawnOres(primer, RPGBlocks.ruby_ore.getDefaultState(), RPGBlocks.basalt.getDefaultState(), 2, 30, random);
		return new Chunk(world, primer, x, z);
	}
	
	public Chunk generateLowLands(int x, int z){
		ChunkPrimer primer = new ChunkPrimer();
		ChunkUtils.fill(primer, 0, 0, 0, 15, 48, 0, RPGBlocks.basalt.getDefaultState());
		ChunkUtils.fill(primer, 0, 49, 0, 15, 53, 15, Blocks.lava.getDefaultState());
		int times = 49;
		while(times < 60){
			makeHill(primer, x, z, times, 20, RPGBlocks.basalt.getDefaultState());
			++times;
		}
		ChunkUtils.spawnOres(primer, RPGBlocks.ruby_ore.getDefaultState(), RPGBlocks.basalt.getDefaultState(), 2, 30, random);
		return new Chunk(world, primer, x, z);
	}
	
	public Chunk generateOcean(int x, int z){
		ChunkPrimer primer = new ChunkPrimer();
		ChunkUtils.fill(primer, 0, 0, 0, 15, 20, 15, RPGBlocks.basalt.getDefaultState());
		ChunkUtils.fill(primer, 0, 21, 0, 15, 53, 15, Blocks.lava.getDefaultState());
		int times = 21;
		while(times < 30){
			makeHill(primer, x, z, times, 20, RPGBlocks.basalt.getDefaultState());
			++times;
		}
		ChunkUtils.spawnOres(primer, RPGBlocks.ruby_ore.getDefaultState(), RPGBlocks.basalt.getDefaultState(), 2, 30, random);
		return new Chunk(world, primer, x, z);
	}
	
	protected void makeHill(ChunkPrimer primer, int x, int z, int height, int chance, IBlockState block){
		int x2 = 0;
		while(x2 < 16){
			int z2 = 0;
			while(z2 < 16){
				int n = heights.getInteger("x:" + (x * 16 + x2) + "z:" +  (z * 16 + z2 - 1));
				int s = heights.getInteger("x:" + (x * 16 + x2) + "z:" +  (z * 16 + z2 + 1));
				int w = heights.getInteger("x:" + (x * 16 + x2 - 1) + "z:" +  (z * 16 + z2));
				int e = heights.getInteger("x:" + (x * 16 + x2 + 1) + "z:" +  (z * 16 + z2));
				if(n == 0){
					n = height;
				}
				if(s == 0){
					s = height;
				}
				if(w == 0){
					w = height;
				}
				if(e == 0){
					e = height;
				}
				if(n >= height - 1 && s >= height - 1 && w >= height - 1 && e >= height - 1 && random.nextInt(chance) != 0){
					primer.setBlockState(x2, height, z2, block);
					heights.setInteger("x:" + (x * 16 + x2) + "z:" + (z * 16 + z2), height);
				}
				++z2;
			}
			++x2;
		}
	}


	@Override
	public BlockPos getStrongholdGen(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
		return null;
	}
}
