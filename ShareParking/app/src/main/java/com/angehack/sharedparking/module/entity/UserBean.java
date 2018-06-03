package com.angehack.sharedparking.module.entity;


/**
 * Created by xingle on 2017/12/26.
 */

public class UserBean extends BaseBean {

    private String country_code;
    private String mobile;
    private String verify_code;



    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }


}
