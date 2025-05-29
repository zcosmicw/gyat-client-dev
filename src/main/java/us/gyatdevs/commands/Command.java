package us.gyatdevs.commands;

import net.minecraft.client.Minecraft;
import us.gyatdevs.helpers.BypassHelper;
import us.gyatdevs.helpers.MessageHelper;

public interface Command {
    String getName();
    void onCommand(String[] args);

    MessageHelper msgHelper = new MessageHelper();
    BypassHelper bypassHelper = new BypassHelper();
    Minecraft mc = Minecraft.getInstance();
}
