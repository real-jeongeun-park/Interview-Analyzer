package com.interviewanalyzer.dto;

public class Result {
    private String strengths;
    private String weaknesses;
    private String recap;

    public Result(String strengths, String weaknesses, String recap){
        this.strengths = strengths;
        this.weaknesses = weaknesses;
        this.recap = recap;
    }

    public String getStrengths() {
        return strengths;
    }

    public String getWeaknesses() {
        return weaknesses;
    }

    public String getRecap() {
        return recap;
    }
}
