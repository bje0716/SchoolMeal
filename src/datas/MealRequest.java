package datas;

import com.google.gson.annotations.SerializedName;

public class MealRequest {

    @SerializedName("schYmd") // 급식 날짜 yyyy.MM.dd
    private String schYmd;
    @SerializedName("schMmealScCode") // 급식 유형 | 중식 - 2, 석식 - 3
    private String schMmealScCode;
    @SerializedName("insttNm") // 학교 이름
    private String insttNm;
    @SerializedName("schulCode") // 학교 코드
    private String schulCode;
    @SerializedName("schulKndScCode") // 학교 종류 | 중학교 - 03, 고등학교 - 04
    private String schulKndScCode;
    @SerializedName("schulCrseScCode") // 학교 분류 | 중학교 - 3, 고등학교 - 4
    private String schulCrseScCode;

    public MealRequest setSchYmd(String schYmd) {
        this.schYmd = schYmd;
        return this;
    }

    public MealRequest setSchMmealScCode(String schMmealScCode) {
        this.schMmealScCode = schMmealScCode;
        return this;
    }

    public MealRequest setInsttNm(String insttNm) {
        this.insttNm = insttNm;
        return this;
    }

    public MealRequest setSchulCode(String schulCode) {
        this.schulCode = schulCode;
        return this;
    }

    public MealRequest setSchulKndScCode(String schulKndScCode) {
        this.schulKndScCode = schulKndScCode;
        return this;
    }

    public MealRequest setSchulCrseScCode(String schulCrseScCode) {
        this.schulCrseScCode = schulCrseScCode;
        return this;
    }

    public String getSchYmd() {
        return schYmd;
    }

    public String getSchMmealScCode() {
        return schMmealScCode;
    }

    public String getInsttNm() {
        return insttNm;
    }

    public String getSchulCode() {
        return schulCode;
    }

    public String getSchulKndScCode() {
        return schulKndScCode;
    }

    public String getSchulCrseScCode() {
        return schulCrseScCode;
    }
}
