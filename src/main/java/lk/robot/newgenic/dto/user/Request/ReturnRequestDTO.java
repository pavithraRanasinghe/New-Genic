package lk.robot.newgenic.dto.user.Request;

public class ReturnRequestDTO {

    private long orderId;
    private long productId;
    private String reason;

    public ReturnRequestDTO() {

    }

    public ReturnRequestDTO(long orderId,
                            long productId,
                            String reason) {
        this.orderId = orderId;
        this.productId = productId;
        this.reason = reason;
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

    @Override
    public String toString() {
        return "ReturnRequestDTO{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", reason='" + reason + '\'' +
                '}';
    }
}
