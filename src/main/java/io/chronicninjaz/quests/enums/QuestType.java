package io.chronicninjaz.quests.enums;

public enum QuestType {
    GATHER_STEAK(2), ATTACK(3);

    private int points;

    QuestType(int points){
        this.points = points;
    }

    public static QuestType getFromName(String name) {
        for (QuestType questType : values()) {
            if (questType.name().equalsIgnoreCase(name)) {
                return questType;
            }
        }
        return null;
    }

    public int getPoints() {
        return points;
    }
}
