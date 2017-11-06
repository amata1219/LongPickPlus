package longpick.plus;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class BlockPickEvent extends Event implements Cancellable{

	private static final HandlerList handlers = new HandlerList();
	private Player player;
	private Block block;
	private ItemStack itemStack;
	private boolean cancelled;
	
	public BlockPickEvent(Player player, Block block, ItemStack itemStack){
		this.player = player;
		this.block = block;
		this.itemStack = itemStack;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public Block getBlock(){
		return block;
	}
	
	public void setItem(ItemStack item){
		this.itemStack = item;
	}
	
	public ItemStack getItem(){
		return itemStack;
	}
	
	public boolean isCancelled(){
		return cancelled;
	}
	
	public void setCancelled(boolean cancel){
		cancelled = cancel;
	}
	
	public HandlerList getHandlers(){
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}
}
