package us.gyatdevs.utils;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordRichPresence.Builder;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.minecraft.client.Minecraft;
import us.gyatdevs.GYATClient;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class DiscordRP implements ReadyCallback {
    public static boolean rpSwitch = true;
    private static final Minecraft mc = Minecraft.getInstance();
    DiscordRichPresence richPresence = (new Builder("Client Initialization")).setBigImage("logo", "GYATClient " + GYATClient.VERSION)
            .setDetails("Loading...")
            .setStartTimestamps(System.currentTimeMillis())
            .build();
    private boolean enabled = true;

    public void runDiscordRP() {
        this.init();
        this.startTask();
        DiscordRPC.discordUpdatePresence(this.richPresence);
    }

    public void apply(DiscordUser discordUser) {
        System.out.println("Initialized DiscordRichPresence API.");
    }

    private void init() {
        DiscordEventHandlers handlers = (new DiscordEventHandlers.Builder()).setReadyEventHandler((user) -> System.out.printf("Connected to %s#%s (%s)%n", user.username, user.discriminator, user.userId))
                .build();
        DiscordRPC.discordInitialize("1376966499314565273", handlers, true);

    }

    public void startTask() {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
            if (rpSwitch) {
                if (!enabled) {
                    DiscordEventHandlers handlers = (new DiscordEventHandlers.Builder()).setReadyEventHandler((user) -> System.out.printf("Connected to %s#%s (%s)%n", user.username, user.discriminator, user.userId))
                            .build();
                    DiscordRPC.discordInitialize("1376966499314565273", handlers, true);
                    enabled = true;
                }

                this.richPresence.details = mc.player == null ? "Disconnected" : "Connected";
                this.richPresence.state = "Discord: discord.gg/ybSHT7tWRM";
                DiscordRPC.discordUpdatePresence(this.richPresence);
            } else {
                DiscordRPC.discordShutdown();
                enabled = false;
            }
        }, 10L, 10L, TimeUnit.SECONDS);
    }
}


