package com.nukkitworlds.utils;

import cn.nukkit.Server;

public class CoreDetector {

    private static final String PM1E_CLASS = "cn.nukkit.Player";
    private static final String MOT_CLASS = "cn.nukkit.Server";

    private final String coreName;
    private final String coreVersion;

    public CoreDetector() {
        String name = "Unknown";
        String version = "?";

        try {
            Server server = Server.getInstance();
            name = server.getName();
            version = server.getVersion();

            if (name.contains("Lumi") || name.contains("lumi")) {
                name = "LumiCore";
            } else if (name.contains("MOT") || name.contains("Mot")) {
                name = "Nukkit-MOT";
            } else if (name.contains("PM1E") || name.contains("Nukkit")) {
                name = "Nukkit-PM1E";
            }
        } catch (Exception e) {
            name = "Nukkit-PM1E";
        }

        this.coreName = name;
        this.coreVersion = version;
    }

    public boolean isPM1E() {
        return coreName.contains("PM1E") || coreName.contains("Nukkit");
    }

    public boolean isMOT() {
        return coreName.contains("MOT");
    }

    public boolean isLumi() {
        return coreName.contains("Lumi");
    }

    public String getCoreName() {
        return coreName;
    }

    public String getCoreVersion() {
        return coreVersion;
    }
}