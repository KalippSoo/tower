package eu.animecraft.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataManager {
    private Map<UUID, Data> playerData = new HashMap<>();

    public DataManager() {
    }

    public Map<UUID, Data> getPlayerData() {
        return this.playerData;
    }
}
