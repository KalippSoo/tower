package eu.animecraft.data.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import eu.animecraft.AnimeCraft;
import eu.animecraft.tower.Tower;

public class TowerComponent {
	
	public TowerComponent(Tower tower) {
		this.tower = tower;
	}
	
	public Tower tower;
	public Map<ArmorStand, Integer> towers=new HashMap<>();
	
	public static void check(Player player, Tower tower) {
		
		Map<Player, List<TowerComponent>> activeTower = AnimeCraft.instance.getTowerManager().activeTower;
		if (activeTower.get(player) == null) {
			activeTower.put(player, Arrays.asList(new TowerComponent(tower)));
			return;
		}
		List<TowerComponent> towers = new ArrayList<>(activeTower.get(player));
		TowerComponent tc = null;
		for (TowerComponent component : activeTower.get(player)) {
			if (component.tower != tower) {
				tc = new TowerComponent(tower);
			}else {
				tc = null;
			}
		}
		if (tc != null) {
			towers.add(tc);
			activeTower.put(player, towers);
		}
		return;
		
	}
}
