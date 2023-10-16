package com.heima.model.wemedia.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

@Data
public class WmMaterialDTO extends PageRequestDto {
    /**
     * 是否收集
     * 1 是
     * 0 否
     */
    private Short isCollection;
}
