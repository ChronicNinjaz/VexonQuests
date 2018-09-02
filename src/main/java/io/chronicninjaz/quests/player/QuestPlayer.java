package io.chronicninjaz.quests.player;

import io.chronicninjaz.quests.Quests;
import io.chronicninjaz.quests.enums.QuestType;
import io.chronicninjaz.quests.quest.Dialog;
import org.bukkit.entity.Player;

public class QuestPlayer {
    private int questPoints;
    private Player player;
    private boolean questStarted;
    private double duration;
    private QuestType questType;
    private Dialog dialog;

    public QuestPlayer(Player player, int questPoints, boolean questStarted, double duration, QuestType type, Dialog dialog){
        this.player = player;
        this.questPoints = questPoints;
        this.questStarted = questStarted;
        this.duration = duration;
        this.questType = type;
        this.dialog = dialog;

        Quests.getInstance().getQuestManager().players.add(this);
    }

    public int getQuestPoints() {
        return questPoints;
    }

    public void setQuestPoints(int questPoints) {
        this.questPoints = questPoints;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean hasQuestStarted() {
        return questStarted;
    }

    public void setQuestStarted(boolean questStarted) {
        this.questStarted = questStarted;
    }

    public boolean isQuestStarted() {
        return questStarted;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public QuestType getQuestType() {
        return questType;
    }

    public void setQuestType(QuestType questType) {
        this.questType = questType;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
}
