package com.example.ettie.teachersroom;

/**
 * Created by Ettie on 6/29/2015.
 * Method from: http://www.android-ios-tutorials.com/android/android-custom-listview-example/
 */
public class Entry {
    private int entryId;
    private String entryTitle;
    private String entryBody;
    private String entryPoster;
    private String entryTime;
    private String entryTag;

    public Entry() {
    }

    public Entry(String entryTitle, String entryBody, String entryPoster) {
        this.entryTitle = entryTitle;
        this.entryBody = entryBody;
        this.entryPoster = entryPoster;
    }

    public Entry(String entryTitle, String entryBody, String entryTag, String entryPoster, String entryTime) {
        this.entryTitle = entryTitle;
        this.entryBody = entryBody;
        this.entryTag = entryTag;
        this.entryPoster = entryPoster;
        this.entryTime = entryTime;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public String getEntryTitle() {
        return entryTitle;
    }

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    public String getEntryBody() {
        return entryBody;
    }

    public void setEntryBody(String entryBody) {
        this.entryBody = entryBody;
    }

    public String getEntryTag() {
        return entryTag;
    }

    public void setEntryTag(String entryTag) {
        this.entryTag = entryTag;
    }

    public String getEntryPoster() {
        return entryPoster;
    }

    public void setEntryPoster(String entryPoster) {
        this.entryPoster = entryPoster;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    @Override
    public String toString() {
        return "\n" + getEntryTitle() +
                "\n" + getEntryBody() +
                "\n Tagged as: " + getEntryTag() +
                "\nPosted by " + getEntryPoster()+ " @ " + getEntryTime() ;
    }
}
