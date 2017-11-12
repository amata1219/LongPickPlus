package longpick.plus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Banner;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.Leaves;
import org.bukkit.util.BlockIterator;

public class EventListeners implements Listener{

	LongPickPlus plugin = LongPickPlus.plugin;
	MainCommand mc = LongPickPlus.mc;

	public ItemStack item;

	@SuppressWarnings({ "deprecation" })
	@EventHandler
	public void onPlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent e){
		Player p = e.getPlayer();
		Block b = getTargetBlock(p);
		if(!nullCheck(b))return;
		Material m = b.getType();
		int id = b.getTypeId();
		byte data = b.getData();
		if(!p.hasPermission(plugin.permission))return;
		if(!plugin.getConfig().getBoolean("PlayerData." + p.getUniqueId() + ".PickMode"))return;
		if(m.equals(Material.DIODE_BLOCK_OFF) || m.equals(Material.DIODE_BLOCK_ON)){
			item = new ItemStack(Material.DIODE);
		}else if(m.equals(Material.CAKE_BLOCK)){
			item = new ItemStack(Material.CAKE);
		}else if(m.equals(Material.REDSTONE_TORCH_OFF)){
			item = new ItemStack(Material.REDSTONE_TORCH_ON);
		}else if(m.equals(Material.REDSTONE_COMPARATOR_OFF) || m.equals(Material.REDSTONE_COMPARATOR_ON)){
			item = new ItemStack(Material.REDSTONE_COMPARATOR);
		}else if(m.equals(Material.REDSTONE_LAMP_ON)){
			item = new ItemStack(Material.REDSTONE_LAMP_OFF);
		}else if(m.equals(Material.GLOWING_REDSTONE_ORE)){
			item = new ItemStack(Material.REDSTONE_ORE);
		}else if(m.equals(Material.IRON_DOOR_BLOCK)){
			item = new ItemStack(Material.IRON_DOOR);
		}else if(m.equals(Material.BURNING_FURNACE)){
			item = new ItemStack(Material.FURNACE);
		}else if(m.equals(Material.BED_BLOCK)){
			item = new ItemStack(Material.BED);
		}else if(m.equals(Material.DAYLIGHT_DETECTOR_INVERTED)){
			item = new ItemStack(Material.DAYLIGHT_DETECTOR);
		}else if(m.equals(Material.IRON_DOOR_BLOCK)){
			item = new ItemStack(Material.IRON_DOOR);
		}else{
			boolean flag = false;
			for(Material material : list1()){
				if(m.equals(material)){
					flag = true;
				}
			}
			if(!flag)item = new ItemStack(m);
		}

		if(m.equals(Material.LEAVES)||m.equals(Material.LEAVES_2)||m.equals(Material.LOG)||m.equals(Material.LOG_2)){
			item = new ItemStack(m);
			Leaves leaves = new Leaves();
			leaves.setData(data);
			item.setDurability(leaves.getSpecies().getData());
		}else if(m.equals(Material.DOUBLE_PLANT)){
			item = new ItemStack(Material.DOUBLE_PLANT);
			Block block = b.getLocation().add(0, -1, 0).getBlock();
			if(nullCheck(block.getType()) && block.getType() != Material.AIR && block.getType().equals(Material.DOUBLE_PLANT)){
				item.setDurability(block.getData());
			}else{
				item.setDurability(data);
			}
		}else if(m.equals(Material.SKULL)){
			item = new ItemStack(Material.SKULL_ITEM);
			Skull skull = (Skull) b.getState();
			SkullMeta meta = (SkullMeta) item.getItemMeta();
			SkullType st = skull.getSkullType();
			if(st.equals(SkullType.PLAYER)){
				item.setDurability((byte) 3);
				meta.setOwner(skull.getOwner());
				item.setItemMeta(meta);
			}
			if(st.equals(SkullType.SKELETON))item.setDurability((byte) 0);
			if(st.equals(SkullType.WITHER))item.setDurability((byte) 1);
			if(st.equals(SkullType.ZOMBIE))item.setDurability((byte) 2);
			if(st.equals(SkullType.CREEPER))item.setDurability((byte) 4);
			if(st.equals(SkullType.DRAGON))item.setDurability((byte) 5);
		}else if(m.equals(Material.STANDING_BANNER)||m.equals(Material.WALL_BANNER)){
			item = new ItemStack(Material.BANNER);
			Banner banner = (Banner) b.getState();
			BannerMeta meta = (BannerMeta) item.getItemMeta();
			meta.setBaseColor(banner.getBaseColor());
			meta.setPatterns(banner.getPatterns());
			item.setItemMeta(meta);
		}else if(m.equals(Material.STEP)){
			item = new ItemStack(m);
			item.setDurability(data);
			if(data >= 8 )item.setDurability((short) (data - 8));
		}else if(m.equals(Material.WOOD_STEP)){
			item = new ItemStack(m);
			item.setDurability(data);
			if(data >= 8 )item.setDurability((short) (data - 8));
		}else if(m.equals(Material.FLOWER_POT)){
			item = new ItemStack(Material.FLOWER_POT_ITEM);
			//nbt
		}else if(m.equals(Material.SIGN_POST)||m.equals(Material.WALL_SIGN)){
			item = new ItemStack(Material.SIGN);
			//nbt
		}else if(m.equals(Material.BED_BLOCK)){
			item = new ItemStack(Material.BED);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE + "355:" + item.getDurability());
			item.setItemMeta(meta);
		}else if(m.equals(Material.QUARTZ_BLOCK)){
			item = new ItemStack(Material.QUARTZ_BLOCK);
			if(data <= 1){
				item.setDurability(data);
			}else{
				item.setDurability((byte) 2);
			}
		}else if(m.equals(Material.ANVIL)){
			item = new ItemStack(Material.ANVIL);
			if(data >= 4 && data <=11){
				item.setDurability((byte) 1);
			}else if(data >= 8 && data <= 11){
				item.setDurability((byte) 2);
			}
		}else if(m.equals(Material.SAPLING)){
			item = new ItemStack(Material.SAPLING);
			item.setDurability(data);
			if(data >= 8)item.setDurability((short) (data - 8));
		}else if(m.equals(Material.CHEST)||m.equals(Material.TRAPPED_CHEST)){
			item = new ItemStack(m);
		}
		for(Material material : list2()){
			if(m.equals(material)){
				item = new ItemStack(Material.WEB);
			}
		}
		for(Material material : list3()){
			if(m.equals(material)){
				String name = m.toString() + "_ITEM";
				item = new ItemStack(Material.valueOf(name));
			}
		}
		if(plugin.getConfig().getBoolean("PlayerData." + p.getUniqueId() + ".WebTypeMode")){
			item = new ItemStack(Material.WEB);
		}
		Material mate = item.getType();
		boolean dataflag = false;
		for(Material material : list4()){
			if(mate.equals(material)){
				dataflag = true;
			}
		}
		if(!dataflag)item.setDurability((short) data);
		ItemMeta meta = item.getItemMeta();
		if(!nullCheck(meta.getDisplayName()))meta.setDisplayName(ChatColor.WHITE + "" + id + ":" + data);
		if(nullCheck(meta.getLore())){
			List<String> list = meta.getLore();
			list.add(ChatColor.GRAY + "LongPick+");
		}else{
			meta.setLore(Arrays.asList(ChatColor.GRAY + "LongPick+"));
		}
		item.setItemMeta(meta);
		BlockPickEvent event = new BlockPickEvent(p, b, item);
		plugin.getServer().getPluginManager().callEvent(event);
		if(!e.isCancelled()){
			ItemStack hand = p.getInventory().getItemInMainHand();
			ItemStack clone = hand.clone();
			hand.setAmount(0);
			p.getInventory().setItemInMainHand(item);
			p.getInventory().addItem(clone);
			if(plugin.getConfig().getBoolean("PlayerData." + p.getUniqueId() + ".PickMessage")){
				p.sendMessage(ChatColor.GRAY + "(" + b.getType().toString() + " " + b.getTypeId() + ":" + b.getData() + ")");
			}
			e.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent e){
		Player p = e.getPlayer();
		Block b = e.getBlock();
		Material m = b.getType();
		ItemStack hand = null;
		if(e.getHand().equals(EquipmentSlot.HAND)){
			hand = p.getInventory().getItemInMainHand();
		}else if(e.getHand().equals(EquipmentSlot.OFF_HAND)){
			hand = p.getInventory().getItemInOffHand();
		}else{
			return;
		}
		if(!nullCheck(b))return;
		if(!p.hasPermission(plugin.permission))return;
		if(!plugin.getConfig().getBoolean("PlayerData." + p.getUniqueId() + ".PickMode"))return;
		for(Material material : list6()){
			if(material.equals(m)){
				return;
			}
		}
		if(m.toString().matches(".*SHULKER_BOX.*"))return;
		if(hand != null && hand.getType() != null && hand.getType() != Material.AIR){
			ItemMeta meta = hand.getItemMeta();
			if(nullCheck(meta.getDisplayName()) && nullCheck(meta.getLore())){
				for(String s : meta.getLore()){
					if(s.equals(ChatColor.GRAY + "LongPick+")){
						String name = meta.getDisplayName().replaceAll("§f", "");
						String[] split = name.split(":");
						int id = Integer.valueOf(split[0]);
						int data = Integer.valueOf(split[1]);
						if(b.getTypeId() != id)b.setTypeId(id);
						if(b.getData() != data)b.setData((byte) data);
						return;
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e){
		Player p = e.getPlayer();
		ItemStack item = e.getItem();
		if(!nullCheck(item)||!nullCheck(item.getType())||item.getType().equals(Material.AIR))return;
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			if(item.equals(mc.getDataCycler())){
				setDataPlus(e.getClickedBlock());
				e.setCancelled(true);
				return;
			}else if(item.equals(mc.getBlockChanger())){
				if(nullCheck(plugin.changer.get(p.getUniqueId().toString()))){
					Block b = plugin.changer.get(p.getUniqueId().toString());
					Block block = e.getClickedBlock();
					if(block.getType() != b.getType())block.setType(b.getType());
					if(block.getData() != b.getData())block.setData(b.getData());
					e.setCancelled(true);
					return;
				}
			}
		}else if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){
			if(item.equals(mc.getDataCycler())){
				Block b = getTargetBlock(p);
				if(nullCheck(b)){
					setDataPlus(b);
					e.setCancelled(true);
					return;
				}
			}else if(item.equals(mc.getBlockChanger())){
				if(nullCheck(plugin.changer.get(p.getUniqueId().toString()))){
					Block b = plugin.changer.get(p.getUniqueId().toString());
					Block block = getTargetBlock(p);
					if(nullCheck(b)){
						if(block.getType() != b.getType())block.setType(b.getType());
						if(block.getData() != b.getData())block.setData(b.getData());
						e.setCancelled(true);
						return;
					}
				}
			}
		}else if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
			if(item.equals(mc.getDataCycler())){
				setDataMinus(e.getClickedBlock());
				e.setCancelled(true);
				return;
			}else if(item.equals(mc.getBlockChanger())){
				Block b = e.getClickedBlock();
				plugin.changer.put(p.getUniqueId().toString(), b);
				if(plugin.getConfig().getBoolean("PlayerData." + p.getUniqueId().toString() + ".CopyMessage")){
					p.sendMessage(ChatColor.GRAY + "(" + b.getType().toString() + " " + b.getTypeId() + ":" + b.getData() + ")");
				}
				e.setCancelled(true);
				return;
			}
		}else if(e.getAction().equals(Action.LEFT_CLICK_AIR)){
			if(item.equals(mc.getDataCycler())){
				Block b = getTargetBlock(p);
				if(nullCheck(b)){
					setDataMinus(b);
					e.setCancelled(true);
					return;
				}
			}else if(item.equals(mc.getBlockChanger())){
				Block b = getTargetBlock(p);
				if(nullCheck(b)){
					plugin.changer.put(p.getUniqueId().toString(), b);
					if(plugin.getConfig().getBoolean("PlayerData." + p.getUniqueId().toString() + ".CopyMessage")){
						p.sendMessage(ChatColor.GRAY + "(" + b.getType().toString() + " " + b.getTypeId() + ":" + b.getData() + ")");
					}
					e.setCancelled(true);
					return;
				}
			}
		}
	}

	@SuppressWarnings({ "deprecation" })
	private void setDataPlus(Block b){
		byte data = b.getData();
		byte newData = (byte) (b.getData() + 1);
		if(data == 15){
			newData = (byte) 0;
		}
		b.setData(newData);
	}

	@SuppressWarnings({ "deprecation" })
	private void setDataMinus(Block b){
		byte data = b.getData();
		byte newData = (byte) (b.getData() - 1);
		if(data == 0){
			newData = (byte) 15;
		}
		b.setData(newData);
	}

	private Block getTargetBlock(Player p){
		BlockIterator bi = new BlockIterator(p, 256);
		while(bi.hasNext()){
			Block b = bi.next();
			if(b != null && b.getType() != null && b.getType() != Material.AIR)return b;
		}
		return null;
	}

	public List<Material> list1(){
		List<Material> list = new ArrayList<Material>(Arrays.asList(Material.LEAVES, Material.LEAVES_2,
				Material.DOUBLE_PLANT, Material.WALL_BANNER, Material.STANDING_BANNER, Material.FLOWER_POT,
				Material.SKULL, Material.CHEST, Material.TRAPPED_CHEST, Material.LOG, Material.LOG_2, Material.STEP,
				Material.WOOD_STEP, Material.SIGN_POST, Material.WALL_SIGN, Material.BED_BLOCK, Material.QUARTZ_BLOCK,
				Material.ANVIL, Material.PISTON_EXTENSION, Material.SAPLING));
		return list;
	}

	public List<Material> list2(){
		List<Material> list = new ArrayList<Material>(Arrays.asList(Material.WATER, Material.STATIONARY_WATER, Material.LAVA,
				Material.STATIONARY_LAVA, Material.CROPS, Material.CARROT, Material.POTATO, Material.PUMPKIN_STEM,
				Material.MELON_STEM, Material.NETHER_WART_BLOCK, Material.SUGAR_CANE_BLOCK, Material.DOUBLE_STEP,
				Material.WOOD_DOUBLE_STEP, Material.PURPUR_DOUBLE_SLAB, Material.DOUBLE_STONE_SLAB2, Material.PORTAL,
				Material.ENDER_PORTAL, Material.END_GATEWAY, Material.CHORUS_PLANT, Material.CHORUS_FLOWER, Material.REDSTONE_WIRE,
				Material.TRIPWIRE, Material.FIRE, Material.FROSTED_ICE, Material.PISTON_EXTENSION, Material.BEETROOT_BLOCK));
		return list;
	}

	public List<Material> list3(){
		List<Material> list = new ArrayList<Material>(Arrays.asList(Material.ACACIA_DOOR, Material.BIRCH_DOOR, Material.DARK_OAK_DOOR,
				Material.JUNGLE_DOOR, Material.SPRUCE_DOOR, Material.WOOD_DOOR, Material.CAULDRON, Material.BREWING_STAND));
		return list;
	}

	public List<Material> list4(){
		List<Material> list = new ArrayList<Material>(Arrays.asList(Material.DIODE, Material.REDSTONE_COMPARATOR, Material.CAKE,
				Material.WOOD_DOOR, Material.IRON_DOOR, Material.ACACIA_DOOR_ITEM, Material.BIRCH_DOOR_ITEM, Material.DARK_OAK_DOOR_ITEM,
				Material.JUNGLE_DOOR_ITEM, Material.SPRUCE_DOOR_ITEM, Material.ANVIL, Material.PURPUR_SLAB, Material.STONE_SLAB2,
				Material.BED, Material.CAULDRON_ITEM, Material.LEAVES, Material.LEAVES_2,
				Material.DOUBLE_PLANT, Material.WALL_BANNER, Material.STANDING_BANNER, Material.FLOWER_POT,
				Material.SKULL_ITEM, Material.CHEST, Material.TRAPPED_CHEST, Material.LOG, Material.LOG_2, Material.STEP,
				Material.WOOD_STEP, Material.SIGN, Material.QUARTZ_BLOCK, Material.SAPLING));
		return list;
	}

	public List<Material> list6(){
		List<Material> list = new ArrayList<Material>(Arrays.asList(Material.SKULL, Material.WALL_BANNER, Material.STANDING_BANNER,
				Material.WALL_SIGN, Material.SIGN_POST, Material.FLOWER_POT, Material.BED_BLOCK, Material.CHEST, Material.TRAPPED_CHEST,
				Material.FURNACE, Material.DISPENSER, Material.DROPPER, Material.DOUBLE_PLANT, Material.ACACIA_DOOR, Material.BIRCH_DOOR,
				Material.DARK_OAK_DOOR, Material.JUNGLE_DOOR, Material.IRON_DOOR_BLOCK, Material.IRON_TRAPDOOR, Material.WOOD_DOOR,
				Material.TORCH, Material.REDSTONE_TORCH_ON));
		return list;
	}

	private boolean nullCheck(Object obj){
		if(obj != null)return true;
		return false;
	}
}
