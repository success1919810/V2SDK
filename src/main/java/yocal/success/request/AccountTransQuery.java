package yocal.success.request;

import com.google.gson.Gson;
import yocal.success.response.BaseResponse;
/**
 * @author success
 * @date 2025/9/10
 * @version v2.0.0
 * @description: 银行查询接口
 */
public class AccountTransQuery extends BaseRequest<BaseResponse> {

    private String pageNo;
    private String pageSize;

    public AccountTransQuery() {

    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageSize() {
        return pageSize;
    }

    @Override
    public void buildBizContent() {
        Gson gson = new Gson();
        java.util.Map<String, String> bizMap = new java.util.HashMap<>();
        bizMap.put("pageNo", pageNo);
        bizMap.put("pageSize", pageSize);
        this.bizContent = gson.toJson(bizMap);
    }

    @Override
    public Class<BaseResponse> getResponseClass() {
        return BaseResponse.class;
    }

    @Override
    public String getCommand() {
        return "yocyl.account.trans.query";
    }


}
