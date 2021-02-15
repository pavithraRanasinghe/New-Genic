package lk.robot.newgenic.dto.request;

import lk.robot.newgenic.dto.VariationDTO;

import java.util.List;

public class CartRequestDTO {
    private long combinationId;
    private int qty;

    public CartRequestDTO() {

    }

    public CartRequestDTO(long combinationId, int qty) {
        this.combinationId = combinationId;
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public long getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(long combinationId) {
        this.combinationId = combinationId;
    }

    @Override
    public String toString() {
        return "CartRequestDTO{" +
                "combinationId=" + combinationId +
                ", qty=" + qty +
                '}';
    }
}
