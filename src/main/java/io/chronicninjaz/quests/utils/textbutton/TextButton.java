package io.chronicninjaz.quests.utils.textbutton;


import io.chronicninjaz.quests.quest.Button;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class TextButton {
    private TextComponent textComponent;

    public TextButton(){
        textComponent = new TextComponent();
    }

    public TextButton setText(String text){
        textComponent.setText(text);
        return this;
    }

    public TextButton setClickAction(ClickEvent clickAction){
        textComponent.setClickEvent(clickAction);
        return this;
    }

    public TextButton setHoverAction(HoverEvent hoverAction){
        textComponent.setHoverEvent(hoverAction);
        return this;
    }

    public TextButton setBold(boolean bold){
        textComponent.setBold(bold);
        return this;
    }

    public TextComponent build(){
        return textComponent;
    }
}
