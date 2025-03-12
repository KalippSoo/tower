package eu.animecraft.arena;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class Bound {

	private Location min, max;
	
	public Bound() {
		// TODO Auto-generated constructor stub
	}
	
	public Bound(Location min, Location max) {
		this.min = min;
		this.max = max;
	}
	
    public void assignCorrectBound(){

        int minX = min.getBlockX(), minY = min.getBlockY(), minZ = min.getBlockZ();
        int maxX = max.getBlockX(), maxY = max.getBlockY(), maxZ = max.getBlockZ();

        if (minX > maxX){
            int tempX = maxX;
            maxX = minX;
            minX = tempX;
        }
        if (minY > maxY){
            int tempY = maxY;
            maxY = minY;
            minY = tempY;
        }
        if (minZ > maxZ){
            int tempZ = maxZ;
            maxZ = minZ;
            minZ = tempZ;
        }
        min = new Location(min.getWorld(), minX, minY, minZ);
        max = new Location(max.getWorld(), maxX, maxY, maxZ);
    }
    public boolean isComplete(){

        if (min != null && max != null){
            return true;
        }
        return false;
    }
    
    public boolean isWhithinBounds(Location location){
        int minX = min.getBlockX(), minY = min.getBlockY(), minZ = min.getBlockZ();
        int maxX = max.getBlockX()+1, maxY = max.getBlockY()+1, maxZ = max.getBlockZ()+1;
        double x = location.getX(), y = location.getY(), z = location.getZ();
        return x >= minX && x < maxX && y >= minY && y < maxY && z >= minZ && z < maxZ;
    }
    
    public List<Block> getCopy(){
    	List<Block>blocks=new ArrayList<Block>();
        for (int x = min.getBlockX(); x <=max.getBlockX();x++) {
        	for (int y = min.getBlockY(); y <=max.getBlockY();y++) {
        		for (int z = min.getBlockZ(); z <=max.getBlockZ();z++) {
        			blocks.add(min.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }
    
	
	public Location getMin() {
		return min;
	}

	public void setMin(Location min) {
		this.min = min;
	}

	public Location getMax() {
		return max;
	}

	public void setMax(Location max) {
		this.max = max;
	}
	
	
}
