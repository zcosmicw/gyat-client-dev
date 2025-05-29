package us.gyatdevs;

import com.mojang.blaze3d.platform.NativeImage;
import de.florianmichael.viamcp.ViaMCP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.Main;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import us.gyatdevs.commands.CommandManager;
import us.gyatdevs.commands.impl.*;
import us.gyatdevs.crashers.CrashManager;
import us.gyatdevs.crashers.impl.*;
import us.gyatdevs.exploits.ExploitManager;
import us.gyatdevs.exploits.impl.*;
import us.gyatdevs.gui.clickgui.ClickGui;
import us.gyatdevs.helpers.AccountHelper;
import us.gyatdevs.utils.DiscordRP;

import java.io.IOException;
import java.io.InputStream;

public class GYATClient {
    private final AccountHelper accountHelper = new AccountHelper();
    public static GYATClient GYATClient;
    public static boolean DEBUG_MODE = false;
    public static final String VERSION = "5.0";

    public static GYATClient getInstance(){
        if(GYATClient == null) GYATClient = new GYATClient();
        return GYATClient;
    }

    public void start(){
        try {
            ViaMCP.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Minecraft.LOGGER.info("GYATClient Initialization...");
        initializeClient();
        new Thread(ClickGui::initGui).start();
    }

    private void initializeClient(){
        Minecraft.LOGGER.info("Registration Commands...");
        CommandManager.getManager().addCommands(
                new StopCommand(),
                new AuthorsCommand(),
                new HelpCommand(),
                new CrashCommand(),
                new ExploitCommand(),
                new BypassListCommand(),
                new FakeGmCommand(),
                new DetectCommand(),
                new ProxyCommand(),
                new ConfigCommand()
        );
        Minecraft.LOGGER.info("Registration Crash Methods...");
        CrashManager.getManager().addMethod(
                new Strong1(),
                new Rogue1(),
                new Rogue2(),
                new Shift1(),
                new Shift2(),
                new Nova1(),
                new Nova2(),
                new Creative1(),
                new Vortex1(),
                new Vortex2(),
                new Vortex3(),
                new Vortex4(),
                new Onyx1(),
                new Onyx2(),
                new Echo1(),
                new Cipher1(),
                new Spectre1(),
                new Spectre2(),
                new Spectre3()
        );
        Minecraft.LOGGER.info("Registration Crash Exploits...");
        ExploitManager.getManager().addExploit(
                new BungeeExploit(),
                new ConsoleSpammer(),
                new CommandsExploit(),
                new EntityExploit(),
                new EssentialsExploit(),
                new FaweExploit(),
                new ForceOpExploit(),
                new Log4JExploit(),
                new LuckPermsExploit(),
                new BundleExploit(),
                new MVCExploit(),
                new PexExploit(),
                new SignExploit(),
                new PluginExploit(),
                new LogsExploit(),
                new LPXExploit(),
                new WorldEditExploit(),
                new PVExploit(),
                new PurpurExploit()
        );
        registerBGImage();
        new DiscordRP().runDiscordRP();
        Minecraft.LOGGER.info("Loading accounts...");
        accountHelper.readAccountsMap();
    }

    private void registerBGImage(){
        Minecraft.LOGGER.info("Registration Custom Images...");
        InputStream inputStream = Main.class.getResourceAsStream("/client/images/background.png");
        try {
            if(inputStream != null) {
                NativeImage image = NativeImage.read(inputStream);
                ResourceLocation customBackground = new ResourceLocation("GYAT", "textures/gui/custom_background.png");
                Minecraft.getInstance().getTextureManager().register(customBackground, new DynamicTexture(image));
                inputStream.close();
            }
        } catch (IOException e) {
            Minecraft.LOGGER.info("Error while reading images!");
            e.printStackTrace();
        }
    }

    public String getTitle(){
        return "GYATClient [1.20.1] - " + VERSION;
    }

}
