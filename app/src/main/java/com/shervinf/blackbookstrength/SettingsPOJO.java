package com.shervinf.blackbookstrength;

public class SettingsPOJO {
    private String settingsLabel;
    private String settingsSubLabel;


    public SettingsPOJO() {
    }

    public SettingsPOJO(String settingsLabel, String settingsSubLabel) {
        this.settingsLabel = settingsLabel;
        this.settingsSubLabel = settingsSubLabel;
    }

    public String getSettingsLabel() {
        return settingsLabel;
    }

    public void setSettingsLabel(String settingsLabel) {
        this.settingsLabel = settingsLabel;
    }

    public String getSettingsSubLabel() {
        return settingsSubLabel;
    }

    public void setSettingsSubLabel(String settingsSubLabel) {
        this.settingsSubLabel = settingsSubLabel;
    }
}
