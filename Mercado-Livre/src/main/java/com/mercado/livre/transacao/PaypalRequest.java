package com.mercado.livre.transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PaypalRequest {
    @Min(0)
    @Max(1)
    private long status;
    @NotNull
    private long compraId;

    public long getStatus() {
        return status;
    }

    public long getCompraId() {
        return compraId;
    }
}
