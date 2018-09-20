package knokko.rpg.worldgen.voidworld;

import java.util.List;

import knokko.util.BuildUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class VoidChunkProvider implements IChunkProvider {
	
	public World world;
	public ChunkPrimer basicChunk;
	
	public VoidChunkProvider(World worldObj){
		world = worldObj;
		basicChunk = new ChunkPrimer();
	}
	
	public VoidChunkProvider(World worldObj, ChunkPrimer chunkBlueprint){
		world = worldObj;
		basicChunk = chunkBlueprint;
	}
	
	@Override
	public boolean chunkExists(int x, int z) {
		return true;
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		ChunkPrimer primer = new ChunkPrimer();
		return new Chunk(world, primer, x, z);
	}

	@Override
	public void populate(IChunkProvider provider, int x, int z) {
	}

	@Override
	public boolean saveChunks(boolean b, IProgressUpdate update) {
		return true;
	}

	@Override
	public boolean unloadQueuedChunks() {
		return false;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	@Override
	public String makeString() {
		return "Void Chunk Provider";
	}



	@Override
	public int getLoadedChunkCount() {
		return 0;
	}

	@Override
	public void recreateStructures(Chunk chunk, int x, int z) {
	}

	@Override
	public void saveExtraData() {
	}

	@Override
	public Chunk provideChunk(BlockPos blockPosIn) {
		return provideChunk(blockPosIn.getX(), blockPosIn.getZ());
	}

	@Override
	public boolean func_177460_a(IChunkProvider provider, Chunk chunk, int x, int z) {
		return false;
	}

	@Override
	public List func_177458_a(EnumCreatureType type, BlockPos pos) {
		return null;
	}

	@Override
	public BlockPos getStrongholdGen(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
		return null;
	}

}
