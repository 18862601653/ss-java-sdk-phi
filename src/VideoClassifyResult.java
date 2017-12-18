package com.snowsense.antiporn.sdk;


public class VideoClassifyResult {
    private String result;
    private String error;

    public String getResult(){
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VideoClassifyResult{");
        if(!"".equals(result)) {
            sb.append("result=").append(result);
        }else{
            sb.append("error=").append(error);
        }
        sb.append('}');
        return sb.toString();
    }
}
