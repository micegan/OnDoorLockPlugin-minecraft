package plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.ChatColor;




public class Main extends JavaPlugin implements Listener {

	@Override
	   public void onEnable() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(this, this);
        
        
        setups1x(this);
   
        
        
        
        
        reloadDatas1x();
    
        
        if (mobnammx.get("data.doors") != null) {
        	
        	
        	ArrayList<Location> lo = (ArrayList<Location> ) mobnammx.get("data.doors");
        	
        	for (Location lx : lo) {
        		doors.add(lx.getBlock());
        	}
        	
        	
        }
        if (mobnammx.get("data.keys") != null) {
        	key = (ArrayList<ItemStack> ) mobnammx.get("data.keys");
        	
        }
        
	}
	
	
	@Override
	public void onDisable() {
		
		ArrayList<Location> lo = new ArrayList<Location>();
		for (Block door : doors) {
		  lo.add(door.getLocation());
		}
		mobnammx.set("data.doors", lo);
		
		mobnammx.set("data.keys", key);
		saveDatas1x();
	}
	

	  public FileConfiguration mobnammx;
	   public File mobnamfx = new File(this.getDataFolder(), "x.yml");

	    public void setups1x(Plugin p) {
	        if (!p.getDataFolder().exists()) {
	            p.getDataFolder().mkdir();
	        }

	        mobnamfx = new File(p.getDataFolder(), "x.yml");

	        if (!mobnamfx.exists()) {
	            try {
	            	mobnamfx.createNewFile();
	            } catch (IOException e) {
	                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create x.yml!");
	            }
	        }

	       YamlConfiguration data = YamlConfiguration.loadConfiguration(mobnamfx);
	    }

	    public FileConfiguration getDatas1x() {
	        return mobnammx;
	    }

	    public void saveDatas1x() {
	        try {
	        	mobnammx.save(mobnamfx);
	        } catch (IOException e) {
	            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save x.yml");
	        }
	    }

	    public void reloadDatas1x() {
	    	mobnammx = YamlConfiguration.loadConfiguration(mobnamfx);
	    } 
	
	/*
	@EventHandler
    public void OnInventoryOpen(org.bukkit.event.inventory. e) {
		
			
		 
		
		
e.getPlayer().sendMessage("fuckdddddddd");

AnvilInventory inv = (AnvilInventory) Bukkit.createInventory(e.getPlayer(), InventoryType.ANVIL, "Anvil");

e.getPlayer().openInventory(inv);

inv.setItem(0, new ItemStack(Material.PAPER, -999));
				 
			
		
		
	}*/
	
	public ArrayList<Block> doors = new ArrayList<Block>();
	public ArrayList<ItemStack> key = new ArrayList<ItemStack>();
	
	
	
	
	@EventHandler
	public void llpxfeaax(org.bukkit.event.player.PlayerInteractEvent event) {
	 if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		 if (event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.ACACIA_DOOR||event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.BIRCH_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.CRIMSON_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.DARK_OAK_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.JUNGLE_DOOR  || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.OAK_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.SPRUCE_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.WARPED_DOOR) {
			 
		 if (doors.contains(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100))) {

			    event.getPlayer().sendMessage(ChatColor.RED + "This door is locked by the OnDoorLock plugin");
		 
			    event.setCancelled(true);
		 }	
		 }
	 }
	}
	
	@EventHandler
	public void ded12(org.bukkit.event.block.BlockPlaceEvent event) {
		if (key.contains(event.getItemInHand())) {
			event.setCancelled(true);
		}
		for (int x =0; x< doors.size();x++) {
			if (doors.get(x).getLocation().distance(event.getBlock().getLocation()) <=2) {
				event.getPlayer().sendMessage(ChatColor.DARK_RED + "You can't place blocks that close to the locked door!");
				event.setCancelled(true);
			}
		}
	}
	

	
	@EventHandler
	public void dexd12(org.bukkit.event.block.BlockBreakEvent event) {
		if (doors.contains(event.getBlock())) {
			event.setCancelled(true);
		}
		for (int x =0; x< doors.size();x++) {
			if (doors.get(x).getLocation().distance(event.getBlock().getLocation()) <=2) {
				event.getPlayer().sendMessage(ChatColor.DARK_RED + "You can't break blocks that close to the locked door!");
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void dexd12(org.bukkit.event.block.BlockExplodeEvent event) {
		if (doors.contains(event.getBlock())) {
			event.setCancelled(true);
		}
		for (int x =0; x< doors.size();x++) {
			if (doors.get(x).getLocation().distance(event.getBlock().getLocation()) <=2) {
				event.setCancelled(true);
			}
		}
	}
	
	
	@EventHandler
	public void dexd12(org.bukkit.event.block.BlockPhysicsEvent event) {
		if (doors.contains(event.getBlock())) {
			event.setCancelled(true);
			
			
			
		}
		for (int x =0; x< doors.size();x++) {
			if (doors.get(x).getLocation().equals(event.getBlock().getLocation()) || doors.get(x).getLocation().equals(new Location(event.getBlock().getLocation().getWorld(),event.getBlock().getLocation().getX(), event.getBlock().getLocation().getY()-1,event.getBlock().getLocation().getY())) || doors.get(x).getLocation().equals(new Location(event.getBlock().getLocation().getWorld(),event.getBlock().getLocation().getX(), event.getBlock().getLocation().getY()+1,event.getBlock().getLocation().getY()))) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void llpeaax(org.bukkit.event.player.PlayerInteractEvent event) {
		 if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
			 
			 
			 if (!doors.contains(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100)) || doors.get(key.indexOf(event.getPlayer().getInventory().getItemInMainHand())).equals(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getWorld().getBlockAt(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getLocation().getBlockX(), event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getLocation().getBlockY()-1, event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getLocation().getBlockZ()))|| doors.get(key.indexOf(event.getPlayer().getInventory().getItemInMainHand())).equals(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getWorld().getBlockAt(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getLocation().getBlockX(), event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getLocation().getBlockY()+1, event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getLocation().getBlockZ()))) {
			 
				 if (!key.contains(event.getPlayer().getInventory().getItemInMainHand())) {
			 if (event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.ACACIA_DOOR||event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.BIRCH_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.CRIMSON_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.DARK_OAK_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.JUNGLE_DOOR  || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.OAK_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.SPRUCE_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.WARPED_DOOR) {
				 
				 if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.TRIPWIRE_HOOK) {

					    
					    ItemMeta testEnchantMeta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
					    ArrayList<String> arr= new ArrayList<String>();
						 int kes = key.size();
						 arr.add(ChatColor.GRAY + "Key number: " + ChatColor.AQUA + kes);
						 testEnchantMeta.setDisplayName("Key");
					 testEnchantMeta.addEnchant(Enchantment.DAMAGE_ALL,3,true);
					 
					 testEnchantMeta.setLore(arr);
					 
					 event.getPlayer().getInventory().getItemInMainHand().setItemMeta(testEnchantMeta);
					 
					 doors.add(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100));
					 

					 event.getPlayer().sendMessage(ChatColor.RED + "You'v locked this door");
					 
					 
					 
					
					 
					 key.add(event.getPlayer().getInventory().getItemInMainHand());
					 
					 event.setCancelled(true);
				 }
					 
				 }
				 
			 }
			 }
			 else if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.TRIPWIRE_HOOK) {
				 if (event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.ACACIA_DOOR||event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.BIRCH_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.CRIMSON_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.DARK_OAK_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.JUNGLE_DOOR  || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.OAK_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.SPRUCE_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.WARPED_DOOR) {
					 
				 if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().get(0).contains(ChatColor.GRAY + "Key number: " + ChatColor.AQUA )) {
					 if (doors.contains(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100))) {
						 boolean dog =false;
					 int x = 0;
					 try
					 {
						 x = Integer.parseInt(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getLore().get(0).replace(ChatColor.GRAY + "Key number: " + ChatColor.AQUA ,""));
					 }
					 catch (Exception xc) {
					     dog = true;
					 }
					 if (!dog) {
						 if (key.contains(event.getPlayer().getInventory().getItemInMainHand())) {
							 if (doors.get(key.indexOf(event.getPlayer().getInventory().getItemInMainHand())).equals(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100)) || doors.get(key.indexOf(event.getPlayer().getInventory().getItemInMainHand())).equals(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getWorld().getBlockAt(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getLocation().getBlockX(), event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getLocation().getBlockY()-1, event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getLocation().getBlockZ()))|| doors.get(key.indexOf(event.getPlayer().getInventory().getItemInMainHand())).equals(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getWorld().getBlockAt(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getLocation().getBlockX(), event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getLocation().getBlockY()+1, event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getLocation().getBlockZ()))) {
								 
							ItemStack is = new ItemStack(Material.TRIPWIRE_HOOK);
							 ItemMeta testEnchantMeta = is.getItemMeta();
							 event.getPlayer().getInventory().getItemInMainHand().setItemMeta(testEnchantMeta);
							 
							 event.getPlayer().sendMessage(ChatColor.RED + "Unlocked the door");
							 key.remove(doors.indexOf(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100)));
							 doors.remove(doors.indexOf(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100)));
							
							
							 }
							 else if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.TRIPWIRE_HOOK) {
								 
								    event.getPlayer().sendMessage(ChatColor.RED + "Wrong door!");
								    event.setCancelled(true);
								 }
						 }
						 else if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.TRIPWIRE_HOOK) {
							 
							    event.getPlayer().sendMessage(ChatColor.RED + "Wrong key!");
							    event.setCancelled(true);
							 }
						/*  if (x <= key.size()) {
							if (doors.get(x-1).equals(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100))) {

								 event.getPlayer().sendMessage(ChatColor.RED + "1");
								ItemStack is = new ItemStack(Material.TRIPWIRE_HOOK);
								 event.getPlayer().sendMessage(ChatColor.RED + "2");
							 ItemMeta testEnchantMeta = is.getItemMeta();
							 event.getPlayer().sendMessage(ChatColor.RED + "3");
							 event.getPlayer().getInventory().getItemInMainHand().setItemMeta(testEnchantMeta);
							 event.getPlayer().sendMessage(ChatColor.RED + "4");
							 event.getPlayer().sendMessage(ChatColor.RED + "Unlocked");
							 doors.remove(x-1);
							 event.getPlayer().sendMessage(ChatColor.RED + "5");
								key.remove(x-1);
								
								
						 }
							else if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.TRIPWIRE_HOOK) {
								 
							    event.getPlayer().sendMessage(ChatColor.RED + "Wrong door!");
							    event.setCancelled(true);
							 }
						  }
						 else if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.TRIPWIRE_HOOK) {
						 
						    event.getPlayer().sendMessage(ChatColor.RED + "Wrong key!");
						    event.setCancelled(true);
						 }*/
						 
					 }
					 }
				 }
					 }
				 }
			 
			 else if (event.getPlayer().getInventory().getItemInMainHand().getType() != Material.TRIPWIRE_HOOK) {
				 if (event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.ACACIA_DOOR||event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.BIRCH_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.CRIMSON_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.DARK_OAK_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.JUNGLE_DOOR  || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.OAK_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.SPRUCE_DOOR || event.getPlayer().getTargetBlock((HashSet<Material>)null, 100).getType() == Material.WARPED_DOOR) {
					 
				 if (doors.contains(event.getPlayer().getTargetBlock((HashSet<Material>)null, 100))) {

			    event.getPlayer().sendMessage(ChatColor.RED + "This door is locked by the OnDoorLock plugin");
		 
			    event.setCancelled(true);
				 }
				 }
			 }
		 }
		 
        }
		
	}


		
		
		

	
	
		
	
		
		
	/*	for (int i = 0; i < nnn.size(); i++) {
			if (nnn.get(i).getClass() == ShulkerBox.class) {
				
				ArrayList<ItemStack> is = new ArrayList<ItemStack>();
				
				
				ShulkerBox sb = (ShulkerBox) nnn.get(i);
				
				
				
					if (sb.getInventory().getContents().length == 27) {
						
					}
				
			}
		}*/
	
	
	

