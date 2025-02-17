package eu.animecraft.data;

import eu.animecraft.group.Group;
import eu.animecraft.tower.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Data {
    private Group group;
    private UUID uuid;
    private List<String> uniquePermissions;
    private List<Tower> towers = new ArrayList<>();
    private List<String> listSelected = new ArrayList<>();
    private int maxStorageSize;

    public int gems = 0;
    public int gold = 0;
    public String title = null;
    public int experience = 0;
    public int level = 0;

    public Banner banner;

    public Data() {
    }

    public List<String> getListSelected() {
        return this.listSelected;
    }

    public void setListSelected(List<String> list) {
        this.listSelected = new ArrayList<>(list);
    }

    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group groups) {
        this.group = groups;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<String> getUniquePermissions() {
        return this.uniquePermissions;
    }

    public void setUniquePermissions(List<String> uniquePermissions) {
        this.uniquePermissions = uniquePermissions;
    }

    public List<Tower> getTowers() {
        return this.towers;
    }

    public Tower containsTower(String uuid) {
        for (Tower tower : getTowers()){
            if (tower.uuid.toString().equals(uuid)){
                return tower;
            }
        }
        return null;
    }

    public void update() {
    }

    public int getMaxStorageSize() {
        return this.maxStorageSize;
    }

    public void setMaxStorageSize(int maxStorageSize) {
        this.maxStorageSize = maxStorageSize;
    }
}
