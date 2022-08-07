package me.tomaszterlecki.travel.model;

public class MonthsForAGivenYear {
    private int year;
    private String[] months;

    public MonthsForAGivenYear() {
    }

    public MonthsForAGivenYear(int year, String[] months) {
        this.year = year;
        this.months = months;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String[] getMonths() {
        return months;
    }

    public void setMonths(String[] months) {
        this.months = months;
    }
}
