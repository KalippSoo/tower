package eu.animecraft;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.bukkit.Location;

import eu.animecraft.arena.Arena;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.PathfinderGoal;

public class PathfinderGoalTower extends PathfinderGoal{

	private final EntityInsentient a; //Creature
	private final Arena region; //Get the region
	public List<Location> locations = new ArrayList<>();
	
	private final double f; //Speed
	private double c;
	private double d;
	private double e;
	
	public PathfinderGoalTower(EntityInsentient a, Arena region, double speed) {
		this.a = a;
		this.f = speed;
		this.region = region;
		this.locations = new ArrayList<Location>(region.paths);
		this.a(EnumSet.of(Type.MOVE));
	}
	
	@Override
	public boolean a() {
		
		if (locations.isEmpty()) {
			region.hurt((int) a.getHealth());
			this.a.dead = true;
			region.enemies.remove(this.a.getBukkitEntity());
			return false;
		}
		else if (a == null)
			return false;
		else if (a.getCustomName() == null)
			return false;
		else if (region == null)
			return false;
			
		Location loc = this.locations.get(0);
		
		this.c = loc.getX();
		this.d = loc.getY();
		this.e = loc.getZ();
		
		return true;
	}
	
	public void c() {
		
		if (this.a.getBukkitEntity().getLocation().distance(this.locations.get(0)) < 3) {
			this.locations.remove(0);
		}
		
		this.a.getNavigation().a(this.c, this.d, this.e, this.f);
	}
	
	public boolean b() {
		return !this.a.getNavigation().m();
	}
	
	public void d() {
		this.a.getNavigation().a(this.c, this.d, this.e, this.f);
	}
	
}
















