package tw.com.wd.obj;

import com.google.gson.Gson;

public class FireAlert {
    private static final Gson GSON_INSTANCE = new Gson();
    // 受理案件時間
    private String caseTime;
    // 案類
    private String caseType;
    // 派遣分隊
    private String caseTeam;
    // 狀態
    private String caseStatus;
    // 案發地址
    private String caseAddress;


    public String getCaseTime() {
        return caseTime;
    }

    public FireAlert setCaseTime(String caseTime) {
        this.caseTime = caseTime;
        return this;
    }

    public String getCaseType() {
        return caseType;
    }

    public FireAlert setCaseType(String caseType) {
        this.caseType = caseType;
        return this;
    }

    public String getCaseTeam() {
        return caseTeam;
    }

    public FireAlert setCaseTeam(String caseTeam) {
        this.caseTeam = caseTeam;
        return this;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public FireAlert setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
        return this;
    }

    public String getCaseAddress() {
        return caseAddress;
    }

    public FireAlert setCaseAddress(String caseAddress) {
        this.caseAddress = caseAddress;
        return this;
    }

    public String toJson() {
        return GSON_INSTANCE.toJson(this);
    }
}
