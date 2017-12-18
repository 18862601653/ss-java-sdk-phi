package com.snowsense.antiporn.sdk;

public class ResetCacheString {
    private String Success;
    private String error;
    public String getSuccess(){
        return Success;
    }
    public String getError(){
        return error;
    }
    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder("ResetCacheResult{");
        if(!"".equals(Success)) {
            sb.append("Success=").append(Success);
        }else{
            sb.append("error=").append(error);
        }
        sb.append('}');
        return sb.toString();
    }

}
