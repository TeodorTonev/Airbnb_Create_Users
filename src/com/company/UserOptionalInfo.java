package com.company;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class UserOptionalInfo {
    private String userSchool;
    private String work;
    private LocalTime timeZone;
    private List<String> languages = new ArrayList<String>();

    public void addLanguages(String language) throws UserException {
        if (language != null && language.trim().length() > 0)
            this.languages.add(language);
        throw new UserException("Invalid language!");
    }

    public void listAllLanguage() {
        for (String language : this.languages) {
            System.out.println(language);
        }
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public LocalTime getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(LocalTime timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        return "[Optional information: " + getUserSchool() + " " + getWork() + " " + getTimeZone() + "]";
    }
}
