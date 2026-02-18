package dev.dawntalemodding;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import javax.annotation.Nonnull;

/**
 * DawntaleRPG – Minimaler Plugin/Mod-Einstiegspunkt.
 * Gibt lediglich einfache Meldungen beim Laden/Starten/Stoppen aus.
 */
public class DawntaleRPG extends JavaPlugin {

    public DawntaleRPG(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        getLogger().atInfo().log("DawntaleRPG initialized");
    }

    @Override
    protected void start() {
        getLogger().atInfo().log("DawntaleRPG started");
    }

    @Override
    protected void shutdown() {
        getLogger().atInfo().log("DawntaleRPG shutting down");
    }
}