package lk.robot.newgenic.dto.user.request;

public class ReturnRequestDTO {

    private long orderId;
    private long productId;
    private String reason;
    private int returnQty;

    public ReturnRequestDTO() {

    }

    public ReturnRequestDTO(long orderId,
                            long productId,
                            String reason,
                            int returnQty) {
        this.orderId = orderId;
        this.productId = productId;
        this.reason = reason;
        this.returnQty = returnQty;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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
                ", productId=" + productId +
                ", reason='" + reason + '\'' +
                ", returnQty=" + returnQty +
                '}';
    }
}
