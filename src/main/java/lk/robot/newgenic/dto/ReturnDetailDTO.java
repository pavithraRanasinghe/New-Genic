package lk.robot.newgenic.dto;

public class ReturnDetailDTO {

    private long combinationId;
    private String reason;
    private int qty;

    public ReturnDetailDTO() {
    }

    public ReturnDetailDTO(long combinationId, String reason, int qty) {
        this.combinationId = combinationId;
        this.reason = reason;
        this.qty = qty;
    }

    public long getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(long combinationId) {
        this.combinationId = combinationId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ReturnDetailDTO{" +
                "combinationId=" + combinationId +
                ", reason='" + reason + '\'' +
                ", qty=" + qty +
                '}';
    }
}
