package eu.animecraft.controler;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_16_R3.EntityTypes;

public class ControlGame {
	
	private List<EnemyCustom> availableEntity = new ArrayList<>();

	public ControlGame() {
		
		availableEntity.add(new EnemyCustom(EntityTypes.CHICKEN, 1.2, 40, "&ePass Chicken", null, true, true));
		
//		availableEntity.add(new EnemyCustom(EntityTypes.ZOMBIE, 1.2, 450, "&cTerminator", Main.main.getRegionManager().getRegionList().get(0), true, true, 2, 4));
//		availableEntity.add(new EnemyCustom(EntityTypes.SHEEP, 1.3, 900, "&cSheep", Main.main.getRegionManager().getRegionList().get(0), false, true, 3, 5));
//		availableEntity.add(new EnemyCustom(EntityTypes.BEE, 1.6, 1400, "&aSUPER BEE", Main.main.getRegionManager().getRegionList().get(0), false, false, 4, 6));
//		availableEntity.add(new EnemyCustom(EntityTypes.ENDERMAN, 1.5, 8500, "&aSlider", Main.main.getRegionManager().getRegionList().get(0), true, true, 6, 9));
//		availableEntity.add(new EnemyCustom(EntityTypes.IRON_GOLEM, 1.5, 15000, "&cCompacteur de Fer", Main.main.getRegionManager().getRegionList().get(0), false, false, 9, 10));
//		availableEntity.add(new EnemyCustom(EntityTypes.CREEPER, 1.3, 250000, "&cFragile", Main.main.getRegionManager().getRegionList().get(0), false, true, 10, 11));
//		
	}
	
	public List<EnemyCustom> getAvailableEntity() {
		return availableEntity;	
	}

}
