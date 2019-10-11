package datas;

import com.google.gson.annotations.SerializedName;

public class SchoolRequest {

    @SerializedName("kraOrgNm")
    private String kraOrgNm;

    public SchoolRequest setKraOrgNm(String kraOrgNm) {
        this.kraOrgNm = kraOrgNm;
        return this;
    }
}
