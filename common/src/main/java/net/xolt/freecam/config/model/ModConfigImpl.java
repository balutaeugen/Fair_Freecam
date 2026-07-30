package net.xolt.freecam.config.model;

import net.xolt.freecam.config.ModConfig;

public class ModConfigImpl implements ModConfig {

    private final ModConfigDTO data;

    public ModConfigImpl(ModConfigDTO data) {
        this.data = data;
    }

    public ModConfigDTO getData() {
        return data;
    }

    @Override
    public FlightMode getFlightMode() {
        return data.movement.flightMode;
    }

    @Override
    public double getHorizontalSpeed() {
        return data.movement.horizontalSpeed;
    }

    @Override
    public double getVerticalSpeed() {
        return data.movement.verticalSpeed;
    }

    @Override
    public Perspective getInitialPerspective() {
        return data.visual.perspective;
    }

    @Override
    public boolean shouldShowPlayer() {
        return data.visual.showPlayer;
    }

    @Override
    public boolean shouldShowHand() {
        return data.visual.showHand;
    }

    @Override
    public boolean isFullBrightEnabled() {
        return data.visual.fullBright;
    }

    @Override
    public boolean shouldShowSubmersionFog() {
        return data.visual.showSubmersion;
    }

    @Override
    public boolean shouldDisableOnDamage() {
        return data.utility.disableOnDamage;
    }

    @Override
    public boolean shouldFreezePlayer() {
        return data.utility.freezePlayer;
    }

    @Override
    public boolean shouldPreventInteractions() {
        return !data.utility.allowInteract;
    }

    public boolean allowInteractionsFrom(ModConfigDTO.InteractionMode mode) {
        return data.utility.allowInteract && data.utility.interactionMode == mode;
    }

    @Override
    public boolean allowInteractionsFromPlayer() {
        return allowInteractionsFrom(ModConfigDTO.InteractionMode.PLAYER);
    }

    @Override
    public boolean isRestrictedOnServer(String serverIp) {
        return switch (data.servers.mode) {
            case NONE -> false;
            case WHITELIST -> {
                String ip = serverIp.trim().toLowerCase();
                yield data.servers.whitelist.stream()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .noneMatch(ip::equals);
            }
            case BLACKLIST -> {
                String ip = serverIp.trim().toLowerCase();
                yield data.servers.blacklist.stream()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .anyMatch(ip::equals);
            }
        };
    }

    @Override
    public boolean shouldNotifyFreecam() {
        return data.notification.notifyFreecam;
    }

    @Override
    public boolean shouldNotifyTripod() {
        return data.notification.notifyTripod;
    }

    @Override
    public boolean shouldOutlinePlayer() {
        return data.visual.outlinePlayer;
    }
}
