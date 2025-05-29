package us.gyatdevs.crashers;

import net.minecraft.client.Minecraft;
import us.gyatdevs.helpers.BypassHelper;
import us.gyatdevs.helpers.MessageHelper;
import us.gyatdevs.utils.OptionUtil;

import java.util.List;

public interface Crasher {
    String getName();
    void onMethod(String[] args);
    default boolean getEnabled() { return false; }
    default void setEnabled(boolean bool) {}
    String getArgsUsage();
    String getDescription();
    List<OptionUtil> getOptions();

    MessageHelper msgHelper = new MessageHelper();
    BypassHelper bypassHelper = new BypassHelper();
    Minecraft mc = Minecraft.getInstance();
}