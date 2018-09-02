package io.chronicninjaz.quests.quest;

import java.util.ArrayList;

public class Dialog {
    private boolean waitingForResponce;
    private ArrayList<String> dialog;
    private int stage;

    public Dialog(boolean waitingForResponce, ArrayList<String> dialog, int stage){
        this.dialog = dialog;
        this.waitingForResponce = waitingForResponce;
        this.stage = stage;
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
