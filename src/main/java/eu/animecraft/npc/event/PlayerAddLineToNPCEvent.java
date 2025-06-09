package eu.animecraft.npc.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import eu.animecraft.npc.NoPlayerCharacter;

public class PlayerAddLineToNPCEvent extends Event{

	private final NoPlayerCharacter npc;
	private final Player player;
	private final String line;
		
    private static final HandlerList HANDLERS = new HandlerList();
	
	public PlayerAddLineToNPCEvent(NoPlayerCharacter npc, Player player, String line) {
		this.npc = npc;
		this.player = player;
		this.line = line;
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

    public String getLine() {
    	return line;
    }
    
	public Player getPlayer() {
		return player;
	}
}
