package lk.robot.newgenic.dto;

public class CartUpdateDetailRequestDTO {

    private long combinationId;
    private int qty;

    public CartUpdateDetailRequestDTO() {
    }

    public CartUpdateDetailRequestDTO(long combinationId, int qty) {
        this.combinationId = combinationId;
        this.qty = qty;
    }

    public long getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(long combinationId) {
        this.combinationId = combinationId;
    }

    public int getQty() {
        return qty;
    }

    public void setStock(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "CartUpdateDetailRequestDTO{" +
                "combinationId=" + combinationId +
                ", qty=" + qty +
                '}';
    }
}
