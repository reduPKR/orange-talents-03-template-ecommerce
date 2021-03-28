package com.mercado.livre.compra;

public enum Gateway {
    PAYPAL("paypal.com?buyerId={idCompra}&redirectUrl=", "/retorno-paypal/{idGateway}"),
    PAGSEGURO("pagseguro.com?returnId={idCompra}&redirectUrl=", "/retorno-pagseguro/{idGateway}");

    private String url1;
    private String url2;

    Gateway(String url1, String url2) {
        this.url1 = url1;
        this.url2 = url2;
    }

    public String getUrl1() {
        return url1;
    }

    public String getUrl2() {
        return url2;
    }
}
