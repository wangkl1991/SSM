package com.array.model;

public class Girls {
    private Integer id;

    private String sname;

    private String cometime;

    private Integer age;

    private Integer maxscore;

    private String minscore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getCometime() {
        return cometime;
    }

    public void setCometime(String cometime) {
        this.cometime = cometime == null ? null : cometime.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getMaxscore() {
        return maxscore;
    }

    public void setMaxscore(Integer maxscore) {
        this.maxscore = maxscore;
    }

    public String getMinscore() {
        return minscore;
    }

    public void setMinscore(String minscore) {
        this.minscore = minscore == null ? null : minscore.trim();
    }
}