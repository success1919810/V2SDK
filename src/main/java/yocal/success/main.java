package yocal.success;

import yocal.success.client.YocalV2Client;
import yocal.success.config.YocalV2Config;
import yocal.success.exception.YocalException;
import yocal.success.request.AccountTransQuery;
import yocal.success.response.BaseResponse;

/**
 * @author: success
 * @date: 2025/9/12 11:50
 * @version: v1.0.0
 * @description: 使用示例
 **/
public class main {
    public static void main(String[] args) {
         YocalV2Config config = new YocalV2Config.Builder("20250909102701729790",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC0sVV+twhuJuCPtXell7ZVSZDEDUcLQZi4E77Y+i2WgCMzvf13eC4JbLaoxYqkH1N3cobj8LLdqA4ctXVfNJeShzAjIAWJ/0mrGVkGqwXEwdF7IxLWue0CHL0183LYqswpoXIdZnsdYZna0NIMwzKFNV/EcY9xZPrBSlnDMUyAiAdrOEO9lk3X2h8q7H99Ps1drJ6F59kmqH+WJjz9euF2XHYmqXHhntPpBbBwl1Tq4ejlmMKWLZYRQuRTEcdFRRMFiDRB8i9nlH6Ib/sx+dCEn0KcK/jQEENJkkPL5jMwIvm5LmfkVzN+4cQ4XJQ5uL10YN/oMZcd6H7/RJIjqgbnAgMBAAECggEAbNTWisDRWg8eeFAzZVCQaKQrhnn8zWHQCycRbvyYUqqRCUwoizmeeNy608JPc/TOQAaCCm5iPTFIdrAwpow1svKr4qwZyfkyNNTKk0KU8eJPOtxwL1bC0t/ecs1T4f5Vg8mSwvSDuLmdgnb93OQA3My+BsLjQ8gvZBZVJETgMBU3vMEJCMI93wXGVlFN34IVCjk/kViG6hEnd6We07EM2eXMSYdHSWIjTRpkxD7vnx3jG4vDH5CLHYeFeoDUlpBmJ1A6CC3vCStMY6jwm+WPxx9gomSYxWaxvifVPUSAieKspdLqbZoKUI9T2TAiD4rFMKenblXLOiP9OU6JhW8IGQKBgQD+BAf4Ex1sbbrOOxdiVjoXmWNQ1NOR2SV8SZ5lVUm2MDR3BuY86KFDGFG7RsbEM5g8FORDlccOsSuO8ZjjWeblNqBD/31peEsjLDsj+C/L4oKfCSVcVbj4hKntxUhmU1EN0xCJgFb3COfviDbm0P7lvcyeBNxrRjAbjOM//aTSzQKBgQC2GqzCVoKDiTdCElS1EZc9Fw7nRQtGaRFnY98F72PcvFLF/DSlIlUbuA3DMgBTMdcHxv2fJC0ndi0rgrOfNcTdZaUO8InoaSIIMOuf8eJGmUi/gIwbSa8kmnGQxTZdePf80prUIj+ZekqB0fgJpa8KJCoq+0M1nDOxoidL9irIgwKBgCscngmhnOXRPdpQtyhOgC+PLgcDCCGB3FXa3NJQBmGwjscA2cTOqsgLcZu6aHPWWekglplN62n38e+h25QCpyiR++Nukmd7Li17DVKOhfYxrH+3eqrQNcOgKIMdb6tGSsRw5SahLKFi1pKV6NOF9y0g6ICNTKe8Ut/qKouqzfWlAoGBAJKul9uN/WDgRP9Wbp7PBYhyq1GEHtnfReKG4p0QP9sMYowRY4JkTjOepI9HFlXs/mzIT4BBjpGLskSq6e5jTCideD+5Zwwqqvv4E8No/b+qNpvyemh8iLQuJy3afLj7+JzQM2COGbaRd7RsIaP7riu4B7NJYtCq+kSp/YdFSR7rAoGAXMf7vhgmq2ffqg1J1oI+B4s56XN852YstUc+P6ZcZoBXS1oKSPUCABjJJcs8byaWfGa9ASAs6gaAgPfw79MEEfo9wpfROS/6TzkP6LPhpWklLImPzGdyNtWcnNbmKDBnVNcT8opoj2QITG3WkV8K/l2mq7ZmaDYKcBS3kfNYkZk=",
                 "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqTjySr+KBH88ei8Ys4pHLXM9HB7OF+ELx+j9jRS9ZO0jKr4ybBFiGWSCf8DlziW0rzV6kQWOsczDRUE4m7iyuYPTizKMpajNmGci7Qrj4xly6Znwx3Ji9XmQDLfpekhs1Tv9kRVeoa3xt6xgT64xC5UBSQ1/5k/I3748BNGBD5vKkQgq9y4+Pxzj5fzLoCe1OZ3E8/+jyoWnVsabxNvfjtHbTXK05RPR+b9srkUWKGxkzDZET1qj0wz0HrRbS2fD7FWkLaeplFR0avJZUoKSZ0mZwGzZAqFbZNATTU+t6s/P+yZlBU7nSmcScaKdeY1noBfv6qUeHhVs3+1HKR5yjQIDAQAB",
                 "https://openapi-test.yocyl.com/api",
                "mmfBb6SjyOs5rSRTHCSFJE2OgFSPMki9",
                "AES",
                "2.0.0").build();
         YocalV2Client client = new YocalV2Client(config);
         AccountTransQuery request= new AccountTransQuery();
         request.setPageNo("1");
         request.setPageSize("1");
         request.buildBizContent();
        try {
            BaseResponse response = client.execute(request);
            System.out.println("响应结果：" + response);
        } catch (YocalException e) {
            System.err.println("请求执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
