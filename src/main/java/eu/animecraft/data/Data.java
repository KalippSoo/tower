package eu.animecraft.data;

import eu.animecraft.arena.Arena;
import eu.animecraft.group.Group;
import eu.animecraft.listerners.menu.TowerMenu;
import eu.animecraft.play.Play;
import eu.animecraft.tower.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Data {
    private Group group;
    private UUID uuid;
    private List<String> uniquePermissions;
    private List<Tower> towers = new ArrayList<>();
    private List<String> listSelected = new ArrayList<>();
    private int maxStorageSize;
    private int playLevel = 0;
    
    private Arena arena;
    public int po;
    
    //No need at first connect
    private TowerMenu towerMenu = new TowerMenu(-1);
    public Play play = null;
    
    public int gems = 0;
    public int gold = 0;
    public String title = null;
    public int experience = 0;
    public int level = 0;
    public int rerolls = 0;

    public Banner banner;

    public Data() {
    }
    
    public boolean isSelectedFull() {
    	
    	int i = 0;
    	for (String string : this.listSelected) {
    		if (string.length() > 8)
    			i++;
    	}
    	if (i == 6)
    		return true;
    	
    	return false;
    }
    
    public boolean addTowerToListedSelection(Tower tower) {
    	
    	int target = -1;
    	for (String string : getListSelected()) {
    		target++;
    		if (string.length() < 8) 
    			break;
    	}
    	if (target>-1) {
    		getListSelected().set(target, tower.uuid.toString());
    		return true;
    	}
    	return false;
    }
    public boolean removeTowerToListedSelection(Tower tower) {

    	int target = -1;
    	boolean found = false;
    	for (String string : getListSelected()) {
    		target++;
    		if (tower.uuid.toString().equals(string)) {
    			found=true;
    			break;
    		}
    			
    	}
    	if (found) {
    		getListSelected().set(target, "none");
    		return true;
    	}
    	return false;
    }
    
    public boolean reroll() {
    	if(rerolls<=0) {
    		return false;
    	}else {
    		rerolls--;
    		return true;
    	}
    }
    public TowerMenu towerMenu(Player player, int mode) {
    	towerMenu.mode = mode;
    	towerMenu.set(player);
    	return towerMenu;
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

	public boolean isPlaying() {
		return this.getArena()!=null;
	}

	public Arena getArena() {
		return arena;
	}	
	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public int getPlayLevel() {
		return playLevel;
	}

	public void setPlayLevel(int playLevel) {
		this.playLevel = playLevel;
	}
}
