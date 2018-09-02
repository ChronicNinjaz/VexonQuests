package io.chronicninjaz.quests.commands;

import io.chronicninjaz.quests.Quests;
import io.chronicninjaz.quests.player.QuestPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

public class AdminCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String s, String[] args) {
        if(cmd.getName().equalsIgnoreCase("questpoints")){

            if(commandSender instanceof Player) {
                Player player = (Player) commandSender;

                switch (args.length) {
                    case 3:
                        if(args[0].equalsIgnoreCase("givepoints")){
                            //qp givepoints {name} {amount}

                            Player target = Bukkit.getPlayer(args[1]);

                            if(target == null){
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&7VexonQuest&7] &eCouldn't find &c" + args[1] + " &e! Please try &canother &euser!"));
                                return false;
                            }

                            int points = Integer.parseInt(args[2]);
                            QuestPlayer qp = Quests.getInstance().getQuestManager().getQuestPlayer(target);

                            if(qp != null && points > 0){
                                qp.setQuestPoints(qp.getQuestPoints() + points);
                            }

                            break;
                        }else if(args[0].equalsIgnoreCase("setpoints")){
                            Player target = Bukkit.getPlayer(args[1]);

                            if(target == null){
                                player.sendMessage("&7[&7VexonQuest&7] &eCouldn't find &c" + args[1] + " &e! Please try &canother &euser!");
                                return false;
                            }

                            int points = Integer.parseInt(args[2]);
                            QuestPlayer qp = Quests.getInstance().getQuestManager().getQuestPlayer(target);

                            if(qp != null){
                                qp.setQuestPoints(points);
                            }

                            break;
                        }
                    default:
                        sendHelpPage(player);
                        break;
                }
            }
        }

        return false;
    }

    public void sendHelpPage(Player player){
        player.sendMessage();
    }
}
