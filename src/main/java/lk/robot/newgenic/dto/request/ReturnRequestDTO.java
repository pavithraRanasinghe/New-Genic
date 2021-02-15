package lk.robot.newgenic.dto.request;

public class ReturnRequestDTO {

    private String orderId;
    private long combinationId;
    private String reason;
    private int returnQty;

    public ReturnRequestDTO() {

    }

    public ReturnRequestDTO(String orderId,
                            long combinationId,
                            String reason,
                            int returnQty) {
        this.orderId = orderId;
        this.combinationId = combinationId;
        this.reason = reason;
        this.returnQty = returnQty;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public int getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(int returnQty) {
        this.returnQty = returnQty;
    }

    @Override
    public String toString() {
        return "ReturnRequestDTO{" +
                "orderId=" + orderId +
                ", combinationId=" + combinationId +
                ", reason='" + reason + '\'' +
                ", returnQty=" + returnQty +
                '}';
    }
}
