package lk.robot.newgenic.dto.request;

import lk.robot.newgenic.dto.CartUpdateDetailRequestDTO;

import java.util.List;

public class CartUpdateRequestDTO {

    private String orderId;
    private List<CartUpdateDetailRequestDTO> updateDetailRequestList;
    private List<Long> removedCombinationList;

    public CartUpdateRequestDTO() {
    }

    public CartUpdateRequestDTO(String orderId,
                                List<CartUpdateDetailRequestDTO> updateDetailRequestList,
                                List<Long> removedCombinationList) {
        this.orderId = orderId;
        this.updateDetailRequestList = updateDetailRequestList;
        this.removedCombinationList = removedCombinationList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<CartUpdateDetailRequestDTO> getUpdateDetailRequestList() {
        return updateDetailRequestList;
    }

    public void setUpdateDetailRequestList(List<CartUpdateDetailRequestDTO> updateDetailRequestList) {
        this.updateDetailRequestList = updateDetailRequestList;
    }

    public List<Long> getRemovedCombinationList() {
        return removedCombinationList;
    }

    public void setRemovedCombinationList(List<Long> removedCombinationList) {
        this.removedCombinationList = removedCombinationList;
    }

    @Override
    public String toString() {
        return "CartUpdateRequestDTO{" +
                "orderId='" + orderId + '\'' +
                ", updateDetailRequestList=" + updateDetailRequestList +
                ", removedCombinationList=" + removedCombinationList +
                '}';
    }
}
