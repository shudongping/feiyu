package com.feiyu.common.core.exception;

import com.feiyu.common.core.domain.CodeMessage;
import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

    private CodeMessage cm;

    public ServiceException(CodeMessage cm) {
        super(cm.getMsg());
        this.cm = cm;
    }

}
