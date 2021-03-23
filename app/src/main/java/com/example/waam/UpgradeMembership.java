package com.example.waam;

public class UpgradeMembership {
    //"membertype_id": 1,
      //"payment_method_id": "pm_1IVcu3Bgd3O4ny7mLm2lWKR2",
       //"currency": "USD"
    private String membertype_id;
    private String payment_method_id;
    private String currency;


    public String getMembertype_id() {
        return membertype_id;
    }

    public void setMembertype_id(String membertype_id) {
        this.membertype_id = membertype_id;
    }

    public String getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(String payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
