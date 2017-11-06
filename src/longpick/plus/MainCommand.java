package longpick.plus;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class MainCommand implements TabExecutor{
	
	LongPickPlus plugin = LongPickPlus.plugin;
	Config config = LongPickPlus.config;

	public MainCommand(LongPickPlus plugin){
		this.plugin = plugin;
	}
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		/*装飾コード使い分け
		 * ゴールド - タイトル
		 * グレー - 補足
		 * 黄色 - 情報
		 * 黄緑 - 成功
		 * 赤色 - 失敗、警告
		 * 白 - 説明
		 */
		if(args.length == 0){
			sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "- LongPick+ -");
			sender.sendMessage(ChatColor.YELLOW + "Spigotバージョン: 1.12");
			sender.sendMessage(ChatColor.YELLOW + "Pluginバージョン: " + plugin.getDescription().getVersion());
			sender.sendMessage(ChatColor.YELLOW + "コマンド一覧: /longpick+ commands");
			sender.sendMessage(ChatColor.YELLOW + "トラブルシューティング: /longpick+ help");
			sender.sendMessage(ChatColor.YELLOW + "公開トピック: " + plugin.getTopicURL());
			sender.sendMessage(ChatColor.GRAY + "Developed by amata1219(Twitter: @amata1219)");
			return true;
		}else if(args[0].equalsIgnoreCase("commands")){
			sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "LongPick+ - コマンド一覧");
			sender.sendMessage(ChatColor.YELLOW + "/longpick+");
			sender.sendMessage(ChatColor.WHITE + "本プラグインの詳細を表示します。");
			sender.sendMessage(ChatColor.YELLOW + "/longpick+ commands");
			sender.sendMessage(ChatColor.WHITE + "本プラグインのコマンド一覧を表示します。");
			sender.sendMessage(ChatColor.YELLOW + "/longpick+ help");
			sender.sendMessage(ChatColor.WHITE + "トラブルシューティングを表示します。");
			sender.sendMessage(ChatColor.YELLOW + "/longpick+ mode [true/false]");
			sender.sendMessage(ChatColor.WHITE + "ロングピック機能を使用するか設定します。trueで有効、falseで無効になります。");
			sender.sendMessage(ChatColor.YELLOW + "/longpick+ webtype [true/false]");
			sender.sendMessage(ChatColor.WHITE + "見た目が蜘蛛の巣になる代わりに、全ブロックに対応したロングピック機能を使用するか設定します。trueで有効、falseで無効になります。");
			sender.sendMessage(ChatColor.YELLOW + "/longpick+ pickmessage [true/false]");
			sender.sendMessage(ChatColor.WHITE + "ブロックピック時に対象ブロックの詳細を表示するか設定します。trueで有効、falseで無効になります。");
			sender.sendMessage(ChatColor.YELLOW + "/longpick+ copymessage [true/false]");
			sender.sendMessage(ChatColor.WHITE + "ブロックコピー時に対象ブロックの詳細を表示するか設定します。trueで有効、falseで無効になります。");
			sender.sendMessage(ChatColor.YELLOW + "/longpick+ meta [0～15]");
			sender.sendMessage(ChatColor.WHITE + "メインハンドに持っているアイテムのメタ値を指定します。");
			sender.sendMessage(ChatColor.YELLOW + "/longpick+ none");
			sender.sendMessage(ChatColor.WHITE + "メインハンドに持っているアイテムをデフォルトのアイテムに戻します。");
			sender.sendMessage(ChatColor.YELLOW + "/lognpick+ clear");
			sender.sendMessage(ChatColor.WHITE + "インベントリに存在するアイテムの内、LongPick+と刻印されている物を全て消去します。");
			sender.sendMessage(ChatColor.YELLOW + "/longpick+ cycler");
			sender.sendMessage(ChatColor.WHITE + "ブロックのデータ値を直接書き換えられる機能を持つDataCyclerを入手します。");
			sender.sendMessage(ChatColor.YELLOW + "/longpick+ changer");
			sender.sendMessage(ChatColor.WHITE + "ブロック情報をコピーし貼り付けられる機能を持つBlockChangerを入手します。");
			return true;
		}else if(args[0].equalsIgnoreCase("help")){
			if(args.length == 1){
				sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "LongPick+ - トラブルシューティング");
				sender.sendMessage(ChatColor.WHITE + "ロングピック出来ない！ - /longpick+ help 1");
				sender.sendMessage(ChatColor.WHITE+ "一部のブロックだけピック出来ない！ - /longpick+ help 2");
				sender.sendMessage(ChatColor.WHITE + "ブロック設置時にメタデータが適用されない！ - /longpick+ help 3");
				sender.sendMessage(ChatColor.WHITE + "その他の場合や原因不明のエラーが発生する場合は本プラグイン公開トピックまでお問い合わせ下さい。");
				sender.sendMessage(ChatColor.GRAY + "URL: " + plugin.getTopicURL());
				return true;
			}else if(args.length == 2){
				if(args[1].equalsIgnoreCase("1")){
					sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "LongPick+  -トラブルシューティング 1");
					sender.sendMessage(ChatColor.WHITE + "1.longpick.plus.longpickというパーミッションを付与されていますか？");
					sender.sendMessage(ChatColor.WHITE + "2.ロングピック機能が無効になってはいませんか？");
					sender.sendMessage(ChatColor.WHITE + "3.操作設定の 持っているアイテムの切り替え に設定されているキーを入力していますか？");
					sender.sendMessage(ChatColor.WHITE + "4.以上の3つが原因でない場合又は不明、エラーが発生する場合は本プラグイン公開トピックまでお問い合わせ下さい。");
					sender.sendMessage(ChatColor.GRAY + "URL: " + plugin.getTopicURL());
					return true;
				}else if(args[1].equalsIgnoreCase("2")){
					sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "LongPick+  -トラブルシューティング 2");
					sender.sendMessage(ChatColor.WHITE + "1.一部未対応のブロックがございますのでプラグインの更新をお待ち下さい。");
					sender.sendMessage(ChatColor.WHITE + "2.本プラグインはある程度のバージョン互換がありますが、プラグインのSpigotバージョン以降に追加されたブロックは、ピック出来ない場合がありますのでプラグインの更新をお待ち下さい。");
					sender.sendMessage(ChatColor.WHITE + "3.以上の2つが原因でない場合又は不明、エラーが発生する場合は本プラグイン公開トピックまでお問い合わせ下さい。");
					sender.sendMessage(ChatColor.GRAY + "URL: " + plugin.getTopicURL());
					return true;
				}else if(args[1].equalsIgnoreCase("3")){
					sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "LongPick+  -トラブルシューティング 3");
					sender.sendMessage(ChatColor.WHITE + "1.longpick.plus.longpickというパーミッションを付与されていますか？");
					sender.sendMessage(ChatColor.WHITE + "2.他プラグインと処理が競合していませんか？");
					sender.sendMessage(ChatColor.WHITE + "3.以上の2つが原因でない場合又は不明、エラーが発生する場合は本プラグイン公開トピックまでお問い合わせ下さい。");
					sender.sendMessage(ChatColor.GRAY + "URL: " + plugin.getTopicURL());
					return true;
				}
			}else{
				sender.sendMessage(ChatColor.RED + "コマンドが正常に入力されていません。");
				return true;
			}
		}else if(args[0].equalsIgnoreCase("mode")){
			if(checkPlayer(sender))return true;
			Player p = (Player) sender;
			if(args.length == 1){
				p.sendMessage(ChatColor.YELLOW + "/longpick+ mode [true/false]");
				p.sendMessage(ChatColor.WHITE + "ロングピック機能を使用するか設定します。trueで有効、falseで無効になります。");
				return true;
			}else if(args.length == 2){
				String s = "PlayerData." + p.getUniqueId() + ".PickMode";
				if(args[1].equalsIgnoreCase("true")){
					if(plugin.getConfig().getBoolean(s)){
						p.sendMessage(ChatColor.RED + "既に有効になっています。");
						return true;
					}
					plugin.getConfig().set(s, true);
					config.saveConfig();
					config.loadConfig();
					p.sendMessage(ChatColor.GREEN + "ロングピック機能を有効にしました。");
					return true;
				}else if(args[1].equalsIgnoreCase("false")){
					if(!plugin.getConfig().getBoolean(s)){
						p.sendMessage(ChatColor.RED + "既に無効になっています。");
						return true;
					}
					plugin.getConfig().set(s, false);
					config.saveConfig();
					config.loadConfig();
					p.sendMessage(ChatColor.GREEN + "ロングピック機能を無効にしました。");
					return true;
				}
			}else{
				sender.sendMessage(ChatColor.RED + "コマンドが正常に入力されていません。");
				return true;
			}
		}else if(args[0].equalsIgnoreCase("webtype")){
			if(checkPlayer(sender))return true;
			Player p = (Player) sender;
			if(args.length == 1){
				sender.sendMessage(ChatColor.YELLOW + "/longpick+ webtype [true/false]");
				sender.sendMessage(ChatColor.WHITE + "見た目が蜘蛛の巣になる代わりに、全ブロックに対応したロングピック機能を使用するか設定します。trueで有効、falseで無効になります。");
				return true;
			}else if(args.length == 2){
				String s = "PlayerData." + p.getUniqueId() + ".WebTypeMode";
				if(args[1].equalsIgnoreCase("true")){
					if(plugin.getConfig().getBoolean(s)){
						p.sendMessage(ChatColor.RED + "既に有効になっています。");
						return true;
					}
					plugin.getConfig().set(s, true);
					config.saveConfig();
					config.loadConfig();
					p.sendMessage(ChatColor.GREEN + "WebTypeModeを有効にしました。");
					return true;
				}else if(args[1].equalsIgnoreCase("false")){
					if(!plugin.getConfig().getBoolean(s)){
						p.sendMessage(ChatColor.RED + "既に無効になっています。");
						return true;
					}
					plugin.getConfig().set(s, false);
					config.saveConfig();
					config.loadConfig();
					p.sendMessage(ChatColor.GREEN + "WebTypeModeを無効にしました。");
					return true;
				}else{
					p.sendMessage(ChatColor.RED + "コマンドが正常に入力されていません。");
					return true;
				}
			}
		}else if(args[0].equalsIgnoreCase("pickmessage")){
			if(checkPlayer(sender))return true;
			Player p = (Player) sender;
			if(args.length == 1){
				p.sendMessage(ChatColor.YELLOW + "/longpick+ pickmessage [true/false]");
				p.sendMessage(ChatColor.WHITE + "ブロックピック時に対象ブロックの詳細を表示するか設定します。trueで有効、falseで無効になります。");
				return true;
			}else if(args.length == 2){
				String s = "PlayerData." + p.getUniqueId() + ".PickMessage";
				if(args[1].equalsIgnoreCase("true")){
					if(plugin.getConfig().getBoolean(s)){
						p.sendMessage(ChatColor.RED + "既に有効になっています。");
						return true;
					}
					plugin.getConfig().set(s, true);
					config.saveConfig();
					config.loadConfig();
					p.sendMessage(ChatColor.GREEN + "ブロックピック時の対象ブロック詳細表示を有効にしました。");
					return true;
				}else if(args[1].equalsIgnoreCase("false")){
					if(!plugin.getConfig().getBoolean(s)){
						p.sendMessage(ChatColor.RED + "既に無効になっています。");
						return true;
					}
					plugin.getConfig().set(s, false);
					config.saveConfig();
					config.loadConfig();
					p.sendMessage(ChatColor.GREEN + "ブロックピック時の対象ブロック詳細表示を無効にしました。");
					return true;
				}
			}else{
				sender.sendMessage(ChatColor.RED + "コマンドが正常に入力されていません。");
				return true;
			}
		}else if(args[0].equalsIgnoreCase("copymessage")){
			if(checkPlayer(sender))return true;
			Player p = (Player) sender;
			if(args.length == 1){
				p.sendMessage(ChatColor.YELLOW + "/longpick+ copymessage [true/false]");
				p.sendMessage(ChatColor.WHITE + "ブロックピック時に対象ブロックの詳細を表示するか設定します。trueで有効、falseで無効になります。");
				return true;
			}else if(args.length == 2){
				String s = "PlayerData." + p.getUniqueId() + ".CopyMessage";
				if(args[1].equalsIgnoreCase("true")){
					if(plugin.getConfig().getBoolean(s)){
						p.sendMessage(ChatColor.RED + "既に有効になっています。");
						return true;
					}
					plugin.getConfig().set(s, true);
					config.saveConfig();
					config.loadConfig();
					p.sendMessage(ChatColor.GREEN + "ブロックコピー時の対象ブロック詳細表示を有効にしました。");
					return true;
				}else if(args[1].equalsIgnoreCase("false")){
					if(!plugin.getConfig().getBoolean(s)){
						p.sendMessage(ChatColor.RED + "既に無効になっています。");
						return true;
					}
					plugin.getConfig().set(s, false);
					config.saveConfig();
					config.loadConfig();
					p.sendMessage(ChatColor.GREEN + "ブロックコピー時の対象ブロック詳細表示を無効にしました。");
					return true;
				}
			}else{
				sender.sendMessage(ChatColor.RED + "コマンドが正常に入力されていません。");
				return true;
			}
		}else if(args[0].equalsIgnoreCase("meta")){
			if(checkPlayer(sender))return true;
			Player p = (Player) sender;
			if(args.length == 1){
				p.sendMessage(ChatColor.GREEN + "/longpick+ meta [0～15]");
				p.sendMessage(ChatColor.WHITE + "メインハンドに持っているアイテムのデータ値を変更します。");
			}else if(args.length == 2){
				int i = 0;
				try{
					i = Integer.valueOf(args[1]);
				}catch(NumberFormatException e){
					p.sendMessage(ChatColor.RED + "コマンドが正常に入力されていません。");
					return true;
				}
				if(i >= 0 && 1 <= 15){
					ItemStack item = p.getInventory().getItemInMainHand();
					if(nullCheck(item) && nullCheck(item.getType()) && item.getType() != Material.AIR){
						ItemMeta meta = item.getItemMeta();
						if(nullCheck(meta.getDisplayName()) && nullCheck(meta.getLore())){
							for(String s : meta.getLore()){
								if(s.equals(ChatColor.GRAY + "LongPick+")){
									meta.setDisplayName(ChatColor.WHITE + "" + item.getTypeId() + ":" + i);
									item.setItemMeta(meta);
								}
							}
						}
						item.setDurability((byte) i);
						p.sendMessage(ChatColor.GREEN + "データ値を変更しました。");
						return true;
					}
				}else{
					p.sendMessage(ChatColor.RED + "入力された数値は設定出来ません。0～15の範囲で指定して下さい。");
				}
			}
		}else if(args[0].equalsIgnoreCase("none")){
			if(checkPlayer(sender))return true;
			Player p = (Player) sender;
			ItemStack item = p.getInventory().getItemInMainHand();
			if(nullCheck(item) && nullCheck(item.getType()) && item.getType() != Material.AIR){
				ItemMeta meta = item.getItemMeta();
				if(nullCheck(meta.getDisplayName()) && nullCheck(meta.getLore())){
					for(String s : meta.getLore()){
						if(s.equals(ChatColor.GRAY + "LongPick+")){
							meta.setDisplayName(null);
							meta.setLore(null);
							item.setItemMeta(meta);
						}
					}
				}
				p.sendMessage(ChatColor.GREEN + "刻印を外しデフォルトのアイテムに戻しました。");
				return true;
			}
		}else if(args[0].equalsIgnoreCase("clear")){
			if(checkPlayer(sender))return true;
			Player p = (Player) sender;
			int i = 0;
			for(ItemStack item : p.getInventory().getContents()){
				if(nullCheck(item) && nullCheck(item.getType()) && item.getType() != Material.AIR){
					ItemMeta meta = item.getItemMeta();
					if(nullCheck(meta.getDisplayName()) && nullCheck(meta.getLore())){
						for(String s : meta.getLore()){
							if(s.equals(ChatColor.GRAY + "LongPick+")){
								item.setAmount(0);
								i++;
								continue;
							}
						}
					}
				}
			}
			if(i == 0){
				p.sendMessage(ChatColor.RED + "LongPick+と刻印されたアイテムはインベントリ内に存在しませんでした。");
				return true;
			}else{
				p.sendMessage(ChatColor.GREEN + "" + i + "個のアイテムを消去しました。");
				return true;
			}
		}else if(args[0].equalsIgnoreCase("cycler")){
			if(checkPlayer(sender))return true;
			Player p = (Player) sender;
			ItemStack item = getDataCycler();
			ItemStack hand = p.getInventory().getItemInMainHand();
			ItemStack clone = hand.clone();
			hand.setAmount(0);
			p.getInventory().setItemInMainHand(item);
			p.getInventory().addItem(clone);
			p.sendMessage(ChatColor.GREEN + "DataCyclerを入手しました。");
			return true;
		}else if(args[0].equalsIgnoreCase("changer")){
			if(checkPlayer(sender))return true;
			Player p = (Player) sender;
			ItemStack item = getBlockChanger();
			ItemStack hand = p.getInventory().getItemInMainHand();
			ItemStack clone = hand.clone();
			hand.setAmount(0);
			p.getInventory().setItemInMainHand(item);
			p.getInventory().addItem(clone);
			p.sendMessage(ChatColor.GREEN + "BlockChangerを入手しました。");
			return true;
		}
		return true;
	}
	
	public ItemStack getDataCycler(){
		ItemStack item = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE + "DataCycler");
		meta.setLore(Arrays.asList(ChatColor.GRAY + "右クリック:", ChatColor.GRAY + " 対象ブロックのデータ値を+1する。", 
				ChatColor.GRAY + "左クリック:", ChatColor.GRAY + " 対象ブロックのデータ値を-1する。", "", ChatColor.GRAY + "LongPick+"));
		item.setItemMeta(meta);
		return item;
	}
	
	public ItemStack getBlockChanger(){
		ItemStack item = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE + "BlockChanger");
		meta.setLore(Arrays.asList(ChatColor.GRAY + "右クリック:", ChatColor.GRAY + " コピーしたブロック情報に対象ブロックを書き換える。", 
				ChatColor.GRAY + "左クリック: ", ChatColor.GRAY + " 対象ブロックの情報をコピーする。", "", ChatColor.GRAY + "LongPick+"));
		item.setItemMeta(meta);
		return item;
	}
	
	private boolean checkPlayer(CommandSender sender){
		if((sender instanceof Player))return false;
		sender.sendMessage(ChatColor.RED + "ゲーム内から実行して下さい。");
		return true;
	}
	
	private boolean nullCheck(Object obj){
		if(obj != null)return true;
		return false;
	}
}
