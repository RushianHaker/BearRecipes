package com.service.bearrecipes.dto;


import com.service.bearrecipes.model.StepInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepInfoDTO {
    private long id;

    private String step;

    private byte[] image;

    private ReceiptDTO receiptDTO;

    public StepInfoDTO(String step, byte[] image, ReceiptDTO receiptDTO) {
        this.step = step;
        this.image = image;
        this.receiptDTO = receiptDTO;
    }

    public static StepInfoDTO toDto(StepInfo stepInfo) {
        return new StepInfoDTO(stepInfo.getId(), stepInfo.getStep(), stepInfo.getImage(),
                ReceiptDTO.toDto(stepInfo.getReceipt()));
    }
}
