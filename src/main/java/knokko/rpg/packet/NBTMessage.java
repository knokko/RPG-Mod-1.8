package knokko.rpg.packet;

import io.netty.buffer.ByteBuf;
import knokko.rpg.data.WorldData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NBTMessage implements IMessage{
	
	public NBTTagCompound nbt;
	public String id;
	
	@Override
	public void fromBytes(ByteBuf buf) {
		nbt = ByteBufUtils.readTag(buf);
		id = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
		ByteBufUtils.writeUTF8String(buf, id);
	}
	
	public NBTMessage() {}
	public NBTMessage(NBTTagCompound compound, EntityPlayer player){
		nbt = compound;
		id = player.getUniqueID().toString();
	}
	public static class Handler implements IMessageHandler<NBTMessage, IMessage> {

		@Override
		public IMessage onMessage(NBTMessage message, MessageContext ctx) {
			if(WorldData.nbt == null){
				WorldData.nbt = new NBTTagCompound();
			}
			WorldData.nbt.setTag(message.id, message.nbt);
			return null;
		}
	}
}
