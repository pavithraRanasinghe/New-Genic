package lk.robot.newgenic.dto.request;

import lk.robot.newgenic.dto.ReturnDetailDTO;

import java.util.List;

public class ReturnRequestDTO {

    private String orderId;
    private List<ReturnDetailDTO> requestList;

    public ReturnRequestDTO() {

    }

    public ReturnRequestDTO(String orderId, List<ReturnDetailDTO> requestList) {
        this.orderId = orderId;
        this.requestList = requestList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<ReturnDetailDTO> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<ReturnDetailDTO> requestList) {
        this.requestList = requestList;
    }

    @Override
    public String toString() {
        return "ReturnRequestDTO{" +
                "orderId='" + orderId + '\'' +
                ", requestList=" + requestList +
                '}';
    }
}
