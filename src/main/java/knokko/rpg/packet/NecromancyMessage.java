package knokko.rpg.packet;

import knokko.rpg.data.WorldData;
import knokko.rpg.entity.data.UndeadTeam;
import knokko.rpg.entity.minion.EntityNecromancerMinion;
import knokko.rpg.entity.minion.TargetType;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NecromancyMessage implements IMessage {
	
	private byte index;
	private int id;
	private int dimension;
	
	public NecromancyMessage(){}

	public NecromancyMessage(EntityPlayer player, int buttonIndex) {
		index = (byte)buttonIndex;
		id = player.getEntityId();
		dimension = player.worldObj.provider.getDimensionId();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		id = buf.readInt();
		dimension = buf.readInt();
		index = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		buf.writeInt(dimension);
		buf.writeByte(index);
	}
	
	public static class Handler implements IMessageHandler<NecromancyMessage, IMessage> {

		@Override
		public IMessage onMessage(NecromancyMessage message, MessageContext ctx) {
			WorldServer world = MinecraftServer.getServer().worldServerForDimension(message.dimension);
			Entity entity = world.getEntityByID(message.id);
			if(!(entity instanceof EntityPlayer))
				return null;
			if(message.index < 18){
				byte i = message.index;
				while(i > 5){
					i -= 6;
				}
				TargetType type = TargetType.PASSIVE;
				if(i == 1)
					type = TargetType.GUARDIAN;
				if(i == 2)
					type = TargetType.DEFENSIVE;
				if(i == 3)
					type = TargetType.HELPER;
				if(i == 4)
					type = TargetType.MONSTERKILLER;
				if(i == 5)
					type = TargetType.OFFENSIVE;
				if(message.index < 6)
					WorldData.setString((EntityPlayer) entity, type.getName(), "standartnecromancytarget");
				else if(message.index < 12){
					UndeadTeam team = UndeadTeam.getTeam(entity);
					if(team != null){
						int t = 0;
						while(t < team.members.size()){
							if(team.members.get(t) instanceof EntityNecromancerMinion)
								((EntityNecromancerMinion)team.members.get(t)).targetType = type;
							++t;
						}
					}
				}
				else
					((EntityPlayer) entity).getHeldItem().setStackDisplayName(type.getName());
				return null;
			}
			if(message.index == 18){
				UndeadTeam team = UndeadTeam.getTeam(entity);
				if(team != null){
					int t = 0;
					while(t < team.members.size()){
						if(team.members.get(t) instanceof EntityNecromancerMinion){
							EntityNecromancerMinion minion = (EntityNecromancerMinion) team.members.get(t);
							minion.setPositionAndUpdate(entity.posX, entity.posY, entity.posZ);
							if(entity.dimension != minion.dimension){
								minion.worldObj.removeEntity(minion);
								minion.isDead = false;
								minion.setWorld(entity.worldObj);
								minion.dimension = entity.dimension;
								entity.worldObj.spawnEntityInWorld(minion);
							}
						}
						++t;
					}
				}
				team.setPositionAndUpdate(entity.posX, entity.posY, entity.posZ);
				if(entity.dimension != team.dimension){
					team.worldObj.removeEntity(team);
					team.setWorld(entity.worldObj);
					team.dimension = entity.dimension;
					entity.worldObj.spawnEntityInWorld(team);
				}
			}
			return null;
		}
		
	}
}
