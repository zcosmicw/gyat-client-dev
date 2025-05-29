package us.gyatdevs.protocol.packets.play;

import io.netty.buffer.ByteBuf;
import us.gyatdevs.protocol.components.ComponentsCodec;
import us.gyatdevs.protocol.components.objects.ItemStack;
import us.gyatdevs.protocol.packets.Packet;
import us.gyatdevs.protocol.packets.PacketCodec;

import java.util.Map;

public class PacketCreativeSlot implements Packet {
    private static final Map<Integer, Integer> PACKET_IDS = Map.of(
            766, 50,
            767, 50,
            768, 52,
            769, 54
    );

    private final int protocolId;
    private final short slot;
    private final ItemStack itemStack;

    public PacketCreativeSlot(int protocolId, short slot, ItemStack itemStack) {
        this.protocolId = protocolId;
        this.slot = slot;
        this.itemStack = itemStack;
    }

    @Override
    public void write(ByteBuf buf) {
        PacketCodec.writeVarInt(buf, PACKET_IDS.getOrDefault(protocolId, 16));
        buf.writeShort(slot);
        ComponentsCodec.writeItem(buf, itemStack);
    }
}
