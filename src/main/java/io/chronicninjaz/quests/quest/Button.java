package io.chronicninjaz.quests.quest;

public class Button {
    private int id;
    private String button;
    private String message;
    private String hover;

    public Button(int id, String button, String message, String hover){
        this.id = id;
        this.button = button;
        this.message = message;
        this.hover = hover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHover() {
        return hover;
    }

    public void setHover(String hover) {
        this.hover = hover;
    }
}
