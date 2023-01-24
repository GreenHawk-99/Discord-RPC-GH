package me.greenhawk.discordrpc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class DiscordRpc {
    public DiscordRpc() {
        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = "id here";
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) -> System.out.println("Ready!");
        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.state = "Playing Solo";
        presence.details = "Competitive";
        presence.largeImageKey = "overwatch_3smw";
        presence.largeImageText = "Overwatch 3";
        presence.smallImageKey = "rust";
        presence.smallImageText = "Rust";
        presence.partyId = "ae488379-351d-4a4f-ad32-2b9b01c91657";
        presence.partySize = 1;
        presence.partyMax = 5;
        presence.joinSecret = "MTI4NzM0OjFpMmhuZToxMjMxMjM= ";
        presence.startTimestamp = System.currentTimeMillis() / 1000; // epoch second
        lib.Discord_UpdatePresence(presence);
        // in a worker thread
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler").start();
    }

    public static void main(String[] args) {
        new DiscordRpc();
    }
}
