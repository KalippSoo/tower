package eu.animecraft.npc.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.animecraft.data.Data;
import eu.animecraft.data.components.Utils;
import eu.animecraft.npc.NoPlayerCharacter;

public class PlayerInteractAtNPCEvent extends Event implements Cancellable{

	private final NoPlayerCharacter npc;
	private final Player player;
	private final String id;
	private final Data data;
		
    private static final HandlerList HANDLERS = new HandlerList();
	
	public PlayerInteractAtNPCEvent(NoPlayerCharacter npc, Player player, String id) {
		this.npc = npc;
		this.player = player;
		this.id = id;
		this.data = Utils.getData(player);
	}
	
	public NoPlayerCharacter getNpc() {
		return npc;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public String getID() {
    	return id;
    }
    
	public Player getPlayer() {
		return player;
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCancelled(boolean var1) {
		// TODO Auto-generated method stub
		
	}

	public Data getData() {
		return data;
	}

}
