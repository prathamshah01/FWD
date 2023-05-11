package com.example.fwd;

public class FeedbackModel {

    private String key, Name, Number, uiValue, issueValue, shareValue, usageValue, futureValue, suggetstion;

    public FeedbackModel(String name, String number, String uiValue, String issueValue, String shareValue, String usageValue, String futureValue, String suggetstion) {
        Name = name;
        Number = number;
        this.uiValue = uiValue;
        this.issueValue = issueValue;
        this.shareValue = shareValue;
        this.usageValue = usageValue;
        this.futureValue = futureValue;
        this.suggetstion = suggetstion;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getUiValue() {
        return uiValue;
    }

    public void setUiValue(String uiValue) {
        this.uiValue = uiValue;
    }

    public String getIssueValue() {
        return issueValue;
    }

    public void setIssueValue(String issueValue) {
        this.issueValue = issueValue;
    }

    public String getShareValue() {
        return shareValue;
    }

    public void setShareValue(String shareValue) {
        this.shareValue = shareValue;
    }

    public String getUsageValue() {
        return usageValue;
    }

    public void setUsageValue(String usageValue) {
        this.usageValue = usageValue;
    }

    public String getFutureValue() {
        return futureValue;
    }

    public void setFutureValue(String futureValue) {
        this.futureValue = futureValue;
    }

    public String getSuggetstion() {
        return suggetstion;
    }

    public void setSuggetstion(String suggetstion) {
        this.suggetstion = suggetstion;
    }

    @Override
    public String toString() {
        return "FeedbackModel{" +
                "key='" + key + '\'' +
                ", Name='" + Name + '\'' +
                ", Number='" + Number + '\'' +
                ", uiValue='" + uiValue + '\'' +
                ", issueValue='" + issueValue + '\'' +
                ", shareValue='" + shareValue + '\'' +
                ", usageValue='" + usageValue + '\'' +
                ", futureValue='" + futureValue + '\'' +
                ", suggetstion='" + suggetstion + '\'' +
                '}';
    }
}
