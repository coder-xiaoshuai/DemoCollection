package com.zhangs.javabasicuse;

public class Base64Test {
    public static void main(String[] args){
        String json="9Yrs/tSkgmsdASg5Sge2ThDMU/xQO8RV0eOTrVxeJFbOZKz/FOaGppq47AJGaUwcY7MYuUiSkAinCX9oMcGmohqcE1bsJuVSfQbW9Rdhb5Dn9QoSTTzeaoZM63KcRly94OneCdKLvpJj4yZh9rPeazRn5mE/bOLMp5rj79toVcT18mEvMxKfux4GyuxZNhGYVp2mJYuhIxKsntu0q5U5R8kKlFqgA2fGW4jvc09UbJujx5/VClKorfknBTGtSMc9P8w6O/3vRxc0d3BKtjVtV4xgHG2gLXPShfpBZcRxjI2ZBtwbPFJGgE9APV8c1pCejdyVP94Ren4PgETD8yxbb09aVZdgkfQKvoJ607X86fRuxOKok4Pj2VWWel7n8CVdoIFR/Vb2MjxfjxSJflQqR8xC3oqIpy0jLlrEE38SpzFqINlBAuiXZkremlNFtm0K9FDWv6WpC8b41Z4RmGUSjL+eeAAILVcyHYPFqFISUcMCEZ1W30gzHFtPmaWBZ8aw";
        String result=new String(Base64.decode(json));
        System.out.print("解码结果"+result);
    }
}
