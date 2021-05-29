package com.example.waam;

public class SpinnerResponse {
    private String career;
    private int education_id;
    private int body_type_id;
    private int ethnicity_id;
    private int faith_id;
    private int politics_id;
    private int children_id;
    private int smoke_id;
    private int drink_id;
    private int income_id;

    public SpinnerResponse(String career, int education_id, int body_type_id, int ethnicity_id, int faith_id, int politics_id, int children_id, int smoke_id, int drink_id, int income_id) {
        this.career = career;
        this.education_id = education_id;
        this.body_type_id = body_type_id;
        this.ethnicity_id = ethnicity_id;
        this.faith_id = faith_id;
        this.politics_id = politics_id;
        this.children_id = children_id;
        this.smoke_id = smoke_id;
        this.drink_id = drink_id;
        this.income_id = income_id;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public int getEducation_id() {
        return education_id;
    }

    public void setEducation_id(int education_id) {
        this.education_id = education_id;
    }

    public int getBody_type_id() {
        return body_type_id;
    }

    public void setBody_type_id(int body_type_id) {
        this.body_type_id = body_type_id;
    }

    public int getEthnicity_id() {
        return ethnicity_id;
    }

    public void setEthnicity_id(int ethnicity_id) {
        this.ethnicity_id = ethnicity_id;
    }

    public int getFaith_id() {
        return faith_id;
    }

    public void setFaith_id(int faith_id) {
        this.faith_id = faith_id;
    }

    public int getPolitics_id() {
        return politics_id;
    }

    public void setPolitics_id(int politics_id) {
        this.politics_id = politics_id;
    }

    public int getChildren_id() {
        return children_id;
    }

    public void setChildren_id(int children_id) {
        this.children_id = children_id;
    }

    public int getSmoke_id() {
        return smoke_id;
    }

    public void setSmoke_id(int smoke_id) {
        this.smoke_id = smoke_id;
    }

    public int getDrink_id() {
        return drink_id;
    }

    public void setDrink_id(int drink_id) {
        this.drink_id = drink_id;
    }

    public int getIncome_id() {
        return income_id;
    }

    public void setIncome_id(int income_id) {
        this.income_id = income_id;
    }
}
