package info.androidhive.materialtabs.activity;

import java.io.Serializable;

/**
 * Created by rahulranjansinha on 29-06-2017.
 */

public class Interviews implements Serializable {
    String interviewerName;
    String date;
    String companyName;
    String codingRound;
    String interviewRound;

    public Interviews(String interviewerName, String date, String companyName, String codingRound, String interviewRound) {
        this.interviewerName = interviewerName;
        this.date = date;
        this.companyName = companyName;
        this.codingRound=codingRound;
        this.interviewRound=interviewRound;
    }

    public Interviews() {
    }

    public String getInterviewerName() {
        return interviewerName;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getInterviewRound() {
        return interviewRound;
    }

    public String getCodingRound() {
        return codingRound;
    }

    public void setCodingRound(String codingRound) {
        this.codingRound = codingRound;
    }

    public void setInterviewRound(String interviewRound) {
        this.interviewRound = interviewRound;
    }
}
