package datas;

public class School {

    private String kraOrgNm;
    private String zipAdres;
    private String schulCode;
    private String schulCrseScCode;
    private String schulKndScCode;

    public String getKraOrgNm() {
        return kraOrgNm;
    }

    public School setKraOrgNm(String kraOrgNm) {
        this.kraOrgNm = kraOrgNm;
        return this;
    }

    public String getZipAdres() {
        return zipAdres;
    }

    public School setZipAdres(String zipAdres) {
        this.zipAdres = zipAdres;
        return this;
    }

    public String getSchulCode() {
        return schulCode;
    }

    public School setSchulCode(String schulCode) {
        this.schulCode = schulCode;
        return this;
    }

    public String getSchulCrseScCode() {
        return schulCrseScCode;
    }

    public School setSchulCrseScCode(String schulCrseScCode) {
        this.schulCrseScCode = schulCrseScCode;
        return this;
    }

    public String getSchulKndScCode() {
        return schulKndScCode;
    }

    public School setSchulKndScCode(String schulKndScCode) {
        this.schulKndScCode = schulKndScCode;
        return this;
    }
}
