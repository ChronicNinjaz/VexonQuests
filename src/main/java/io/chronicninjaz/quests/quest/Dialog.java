package io.chronicninjaz.quests.quest;

import io.chronicninjaz.quests.utils.textbutton.TextButton;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Dialog {
    private boolean waitingForResponce;
    private ArrayList<String> dialog;
    private int stage;

    public Dialog(ArrayList<String> dialog){
        this.dialog = dialog;
        this.waitingForResponce = false;
        this.stage = 0;
    }

    public Dialog(boolean waitingForResponce, ArrayList<String> dialog, int stage){
        this.dialog = dialog;
        this.dialog.add("<action> {ID:1;Text:[Accept];Message:You-have-accepted-the-quest!;Hover:this-is-message-one;/ID:2;Text:[Deny];Message:You-have-not-accepted-the-quest!;Hover:this-is-message-two;} </action> this is the message {1} and this is 2 {2}");
        this.waitingForResponce = waitingForResponce;
        this.stage = stage;
    }

    /**
     * <action> {ID:1;Text:[Accept];Message:You have accepted the quest!;/Text:[Deny];Message:You have not accepted the quest!;} </action>
     */
    public void sendDialog(Player player){
        String text = dialog.get(stage > dialog.size() ? dialog.size()-1 : stage);
        String[] args = text.split(" ");
        ArrayList<Button> buttonArrayList = new ArrayList<>();
        int position = 0;
        if(args[0].startsWith("<action>")){
            String actions = "";

            while (!args[position].equalsIgnoreCase("</action>")) {
                actions += args[position];
                position++;
            }

            String[] buttons = actions.replace("<action>", "").replace("</action>", "").replace("{", "").replace("}", "").split("/");

            // Text:[Accept];Message:You have accepted the quest!;
            // Text:[Deny];Message:You have not accepted the quest!;

            Arrays.stream(buttons).forEach(button -> {

                // remove the Text: get the message until ; make that a string and store it
                String[] values = button.split(";");
                Integer id = Integer.parseInt(values[0].replace(";", "").replace("ID:", ""));
                String displayButton = values[1].replace("Text:", "");
                String buttonMessage = values[2].replace("Message:", "").replace("-", " ");
                String buttonHover = values[3].replace("Hover:", "").replace("-", " ");

                buttonArrayList.add(new Button(id, displayButton, buttonMessage, buttonHover));

                player.sendMessage(id + " / " + displayButton + " / " + buttonMessage + " / " + buttonHover);
            });
        }



        /*

        String builder = "";
        while (position < args.length){
            builder += args[position];
            position++;
        }




        stage++;

        if(args[0].equalsIgnoreCase("<action>Feedback</action>")) {
            TextComponent tp = new TextButton().setText("[Accept]")
                    .setClickAction(new ClickEvent(ClickEvent.Action.RUN_COMMAND, ""))
                    .setHoverAction(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "Click to accept the challenge" ).create() ))
                    .build();

            tp.addExtra( " Be sure to click!");

            player.spigot().sendMessage(tp);
        }

        */
    }

    public boolean isWaitingForResponce() {
        return waitingForResponce;
    }

    public void setWaitingForResponce(boolean waitingForResponce) {
        this.waitingForResponce = waitingForResponce;
    }

    public ArrayList<String> getDialog() {
        return dialog;
    }

    public void setDialog(ArrayList<String> dialog) {
        this.dialog = dialog;
    }
}
