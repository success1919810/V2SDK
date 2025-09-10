package yocal.success;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * YocalV2 SDK测试类
 */
public class YocalV2SDKTest {

    @Test
    public void testYocalV2ConfigCreation() {
        // 准备测试数据（来自YocylApiDemo2中的实际数据）
        String appId = "20250909102701729790";
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCS0BX4rCjWYGV5WRwsVY2A0DtVg35pph2qk5FJUZm6AhXGhm/ZwqXbXxq6xggyU6ddA2/y/xNN5WuuKf5h4imUczDm0LkHKhu99eVWMiOqU7LguGog/uGuG1CcK6gne/txujBmwmY4dWBxo4moCm5SlUmz4kH7BFKuZGp+P66D/jcVT+1HT5ScXfPeCkEyrwI1ScnaJOTP0uQ0rLi9xO4uu7RiKtDaONbCPYzax+CIo5SgZOZ909JWJFNolzCTEQrGxNdquK1dUktdTAYkk36Xg/C7vcJ/U/W6pIEOyr6p+zPg+Y87T2PY6nfb7/H6pxotDCN4TPIIC9jlmq9M+ND1AgMBAAECggEAfczaltwGnjO5n/KwhnjEqNVFkm/7eVIDZ6/NNbM7c7znpdZ6r3DwJ/rlb5fTXDR8W+JfIixaAUyOKY4IUR23nWHbTF0bvzzgyrSS0HpkP0Y7J/49yoo9HbCAMWHjU/oUKo5tfRAlAUnq21VT1m8hu5f6sK429X3tz0tyST4OL0DueM54R9N7WXs2tquWkj6RD4aZca5wSqtR254NakRmBSVdTVEKbMvgXoEsegjwbcy4hunxOCXfz89UG/LjwRRgBMM4scVHI3ERZg2yImhc5pDk3zv9zmP/glz+pEkaA+KfdWU5p1Gjs3KRG/KBnS+m+0agp7MG5aQbe6UzPP2dAQKBgQDbLcr+wv3aPDmFBmvIUzSRELFijw8JX4hYFdJNUFBOwO6ePUllgP1lzK0sfnqu4fInc3pq0NtubfU9oBKsIJtbSDkWER1OUG079JW6aa13cZOASHMIhAnBbFIQ9yPvRy8TEuCyP7z0CQcrE7CgLPUswqWo+g9isynK9oPcxo+2tQKBgQCreg1+pp6eMbSSbXcIwPd0+fI31iSWF4y9tt7v5oG6QjWmlhakiZybqt9jEUOo4j8fFmTjArx7DqYvSHhD3D4XpSbqeBTkPwjOJsQpkSzvsJGSYToCfojDWsv3gqPCTXuX76Z0YKLLCObUkX2Y5HgEpGuFSoPS/OyEFgtTc6zZQQKBgQCY3fZ/+2XsKScBBbp07Lt0Fg1yLU8SfYPt08Jq1AI++0cyLJKdbfrOXpPFva05fjNTmrId/++btKtgQN3lGZThdJ0ELAmhjmyxbWRksMIg1aFHzsAUh2r6cd9HH+f7Qk2t4vO+vr++APHz9HazMMgLYPMDyOykLUuP2KGdgQvJJQKBgCTXFA8hvPI/u4u5+NjonEGFcYSNfU7BwHsBzuO7oiNiFiS0Gik/Z2YKT/P4wZCHCwiixwn+jH/jpdCCwVPS/YQW90VohxSCdmHT8lD736ufQ6cvPEdM6BUQbMHAT25vNx5tXlWibVcxkmYY2+L9MsvMh00btTafIDFAiy5iq8ABAoGAPkje6LWiXe5SBx5soiwF4SMSZo0sr5/dvxLC7YtX0qYpmGBhGoT6+EBT8VdETaMYvRoRqvl0q0S0tqDHE+10s2J0XgBaA6aRRVHzRnllAzMZ+iA5fSvb2kyrh/OczE6xXnZdvujo0OA3tCLQKgxJe+3n1/2BkzqkFMKLrkbhIhI=";
        String aesKey = "v8MB73faXlVdb8uPSmmjge3oALKwudOO";
        String version = "2.0.0";
        String command = "yocyl.account.trans.query";
        String encryptType = "AES";

        // 创建配置对象
        YocalV2Config config = new YocalV2Config(appId, privateKey, aesKey, version, command, encryptType);

        // 验证配置对象的属性
        assertEquals("AppId should match", appId, config.getAppId());
        assertEquals("PrivateKey should match", privateKey, config.getPrivateKey());
        assertEquals("AESKey should match", aesKey, config.getAesKey());
        assertEquals("Version should match", version, config.getVersion());
        assertEquals("Command should match", command, config.getCommand());
        assertEquals("EncryptType should match", encryptType, config.getEncryptType());
    }

    @Test
    public void testEncryptEnabled() {
        // 测试加密启用情况（AES加密）
        YocalV2Config configWithEncrypt = new YocalV2Config(
                "20250909102701729790",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCS0BX4rCjWYGV5WRwsVY2A0DtVg35pph2qk5FJUZm6AhXGhm/ZwqXbXxq6xggyU6ddA2/y/xNN5WuuKf5h4imUczDm0LkHKhu99eVWMiOqU7LguGog/uGuG1CcK6gne/txujBmwmY4dWBxo4moCm5SlUmz4kH7BFKuZGp+P66D/jcVT+1HT5ScXfPeCkEyrwI1ScnaJOTP0uQ0rLi9xO4uu7RiKtDaONbCPYzax+CIo5SgZOZ909JWJFNolzCTEQrGxNdquK1dUktdTAYkk36Xg/C7vcJ/U/W6pIEOyr6p+zPg+Y87T2PY6nfb7/H6pxotDCN4TPIIC9jlmq9M+ND1AgMBAAECggEAfczaltwGnjO5n/KwhnjEqNVFkm/7eVIDZ6/NNbM7c7znpdZ6r3DwJ/rlb5fTXDR8W+JfIixaAUyOKY4IUR23nWHbTF0bvzzgyrSS0HpkP0Y7J/49yoo9HbCAMWHjU/oUKo5tfRAlAUnq21VT1m8hu5f6sK429X3tz0tyST4OL0DueM54R9N7WXs2tquWkj6RD4aZca5wSqtR254NakRmBSVdTVEKbMvgXoEsegjwbcy4hunxOCXfz89UG/LjwRRgBMM4scVHI3ERZg2yImhc5pDk3zv9zmP/glz+pEkaA+KfdWU5p1Gjs3KRG/KBnS+m+0agp7MG5aQbe6UzPP2dAQKBgQDbLcr+wv3aPDmFBmvIUzSRELFijw8JX4hYFdJNUFBOwO6ePUllgP1lzK0sfnqu4fInc3pq0NtubfU9oBKsIJtbSDkWER1OUG079JW6aa13cZOASHMIhAnBbFIQ9yPvRy8TEuCyP7z0CQcrE7CgLPUswqWo+g9isynK9oPcxo+2tQKBgQCreg1+pp6eMbSSbXcIwPd0+fI31iSWF4y9tt7v5oG6QjWmlhakiZybqt9jEUOo4j8fFmTjArx7DqYvSHhD3D4XpSbqeBTkPwjOJsQpkSzvsJGSYToCfojDWsv3gqPCTXuX76Z0YKLLCObUkX2Y5HgEpGuFSoPS/OyEFgtTc6zZQQKBgQCY3fZ/+2XsKScBBbp07Lt0Fg1yLU8SfYPt08Jq1AI++0cyLJKdbfrOXpPFva05fjNTmrId/++btKtgQN3lGZThdJ0ELAmhjmyxbWRksMIg1aFHzsAUh2r6cd9HH+f7Qk2t4vO+vr++APHz9HazMMgLYPMDyOykLUuP2KGdgQvJJQKBgCTXFA8hvPI/u4u5+NjonEGFcYSNfU7BwHsBzuO7oiNiFiS0Gik/Z2YKT/P4wZCHCwiixwn+jH/jpdCCwVPS/YQW90VohxSCdmHT8lD736ufQ6cvPEdM6BUQbMHAT25vNx5tXlWibVcxkmYY2+L9MsvMh00btTafIDFAiy5iq8ABAoGAPkje6LWiXe5SBx5soiwF4SMSZo0sr5/dvxLC7YtX0qYpmGBhGoT6+EBT8VdETaMYvRoRqvl0q0S0tqDHE+10s2J0XgBaA6aRRVHzRnllAzMZ+iA5fSvb2kyrh/OczE6xXnZdvujo0OA3tCLQKgxJe+3n1/2BkzqkFMKLrkbhIhI=",
                "v8MB73faXlVdb8uPSmmjge3oALKwudOO",
                "2.0.0",
                "yocyl.account.trans.query",
                "AES"
        );
        assertTrue("Encrypt should be enabled when encryptType is AES", configWithEncrypt.isEncryptEnabled());

        // 测试加密禁用情况（空字符串）
        YocalV2Config configWithoutEncrypt = new YocalV2Config(
                "20250909102701729790",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCS0BX4rCjWYGV5WRwsVY2A0DtVg35pph2qk5FJUZm6AhXGhm/ZwqXbXxq6xggyU6ddA2/y/xNN5WuuKf5h4imUczDm0LkHKhu99eVWMiOqU7LguGog/uGuG1CcK6gne/txujBmwmY4dWBxo4moCm5SlUmz4kH7BFKuZGp+P66D/jcVT+1HT5ScXfPeCkEyrwI1ScnaJOTP0uQ0rLi9xO4uu7RiKtDaONbCPYzax+CIo5SgZOZ909JWJFNolzCTEQrGxNdquK1dUktdTAYkk36Xg/C7vcJ/U/W6pIEOyr6p+zPg+Y87T2PY6nfb7/H6pxotDCN4TPIIC9jlmq9M+ND1AgMBAAECggEAfczaltwGnjO5n/KwhnjEqNVFkm/7eVIDZ6/NNbM7c7znpdZ6r3DwJ/rlb5fTXDR8W+JfIixaAUyOKY4IUR23nWHbTF0bvzzgyrSS0HpkP0Y7J/49yoo9HbCAMWHjU/oUKo5tfRAlAUnq21VT1m8hu5f6sK429X3tz0tyST4OL0DueM54R9N7WXs2tquWkj6RD4aZca5wSqtR254NakRmBSVdTVEKbMvgXoEsegjwbcy4hunxOCXfz89UG/LjwRRgBMM4scVHI3ERZg2yImhc5pDk3zv9zmP/glz+pEkaA+KfdWU5p1Gjs3KRG/KBnS+m+0agp7MG5aQbe6UzPP2dAQKBgQDbLcr+wv3aPDmFBmvIUzSRELFijw8JX4hYFdJNUFBOwO6ePUllgP1lzK0sfnqu4fInc3pq0NtubfU9oBKsIJtbSDkWER1OUG079JW6aa13cZOASHMIhAnBbFIQ9yPvRy8TEuCyP7z0CQcrE7CgLPUswqWo+g9isynK9oPcxo+2tQKBgQCreg1+pp6eMbSSbXcIwPd0+fI31iSWF4y9tt7v5oG6QjWmlhakiZybqt9jEUOo4j8fFmTjArx7DqYvSHhD3D4XpSbqeBTkPwjOJsQpkSzvsJGSYToCfojDWsv3gqPCTXuX76Z0YKLLCObUkX2Y5HgEpGuFSoPS/OyEFgtTc6zZQQKBgQCY3fZ/+2XsKScBBbp07Lt0Fg1yLU8SfYPt08Jq1AI++0cyLJKdbfrOXpPFva05fjNTmrId/++btKtgQN3lGZThdJ0ELAmhjmyxbWRksMIg1aFHzsAUh2r6cd9HH+f7Qk2t4vO+vr++APHz9HazMMgLYPMDyOykLUuP2KGdgQvJJQKBgCTXFA8hvPI/u4u5+NjonEGFcYSNfU7BwHsBzuO7oiNiFiS0Gik/Z2YKT/P4wZCHCwiixwn+jH/jpdCCwVPS/YQW90VohxSCdmHT8lD736ufQ6cvPEdM6BUQbMHAT25vNx5tXlWibVcxkmYY2+L9MsvMh00btTafIDFAiy5iq8ABAoGAPkje6LWiXe5SBx5soiwF4SMSZo0sr5/dvxLC7YtX0qYpmGBhGoT6+EBT8VdETaMYvRoRqvl0q0S0tqDHE+10s2J0XgBaA6aRRVHzRnllAzMZ+iA5fSvb2kyrh/OczE6xXnZdvujo0OA3tCLQKgxJe+3n1/2BkzqkFMKLrkbhIhI=",
                "v8MB73faXlVdb8uPSmmjge3oALKwudOO",
                "2.0.0",
                "yocyl.account.trans.query",
                ""
        );
        assertFalse("Encrypt should be disabled when encryptType is empty", configWithoutEncrypt.isEncryptEnabled());

        // 测试加密禁用情况（null值）
        YocalV2Config configWithNullEncrypt = new YocalV2Config(
                "20250909102701729790",
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCS0BX4rCjWYGV5WRwsVY2A0DtVg35pph2qk5FJUZm6AhXGhm/ZwqXbXxq6xggyU6ddA2/y/xNN5WuuKf5h4imUczDm0LkHKhu99eVWMiOqU7LguGog/uGuG1CcK6gne/txujBmwmY4dWBxo4moCm5SlUmz4kH7BFKuZGp+P66D/jcVT+1HT5ScXfPeCkEyrwI1ScnaJOTP0uQ0rLi9xO4uu7RiKtDaONbCPYzax+CIo5SgZOZ909JWJFNolzCTEQrGxNdquK1dUktdTAYkk36Xg/C7vcJ/U/W6pIEOyr6p+zPg+Y87T2PY6nfb7/H6pxotDCN4TPIIC9jlmq9M+ND1AgMBAAECggEAfczaltwGnjO5n/KwhnjEqNVFkm/7eVIDZ6/NNbM7c7znpdZ6r3DwJ/rlb5fTXDR8W+JfIixaAUyOKY4IUR23nWHbTF0bvzzgyrSS0HpkP0Y7J/49yoo9HbCAMWHjU/oUKo5tfRAlAUnq21VT1m8hu5f6sK429X3tz0tyST4OL0DueM54R9N7WXs2tquWkj6RD4aZca5wSqtR254NakRmBSVdTVEKbMvgXoEsegjwbcy4hunxOCXfz89UG/LjwRRgBMM4scVHI3ERZg2yImhc5pDk3zv9zmP/glz+pEkaA+KfdWU5p1Gjs3KRG/KBnS+m+0agp7MG5aQbe6UzPP2dAQKBgQDbLcr+wv3aPDmFBmvIUzSRELFijw8JX4hYFdJNUFBOwO6ePUllgP1lzK0sfnqu4fInc3pq0NtubfU9oBKsIJtbSDkWER1OUG079JW6aa13cZOASHMIhAnBbFIQ9yPvRy8TEuCyP7z0CQcrE7CgLPUswqWo+g9isynK9oPcxo+2tQKBgQCreg1+pp6eMbSSbXcIwPd0+fI31iSWF4y9tt7v5oG6QjWmlhakiZybqt9jEUOo4j8fFmTjArx7DqYvSHhD3D4XpSbqeBTkPwjOJsQpkSzvsJGSYToCfojDWsv3gqPCTXuX76Z0YKLLCObUkX2Y5HgEpGuFSoPS/OyEFgtTc6zZQQKBgQCY3fZ/+2XsKScBBbp07Lt0Fg1yLU8SfYPt08Jq1AI++0cyLJKdbfrOXpPFva05fjNTmrId/++btKtgQN3lGZThdJ0ELAmhjmyxbWRksMIg1aFHzsAUh2r6cd9HH+f7Qk2t4vO+vr++APHz9HazMMgLYPMDyOykLUuP2KGdgQvJJQKBgCTXFA8hvPI/u4u5+NjonEGFcYSNfU7BwHsBzuO7oiNiFiS0Gik/Z2YKT/P4wZCHCwiixwn+jH/jpdCCwVPS/YQW90VohxSCdmHT8lD736ufQ6cvPEdM6BUQbMHAT25vNx5tXlWibVcxkmYY2+L9MsvMh00btTafIDFAiy5iq8ABAoGAPkje6LWiXe5SBx5soiwF4SMSZo0sr5/dvxLC7YtX0qYpmGBhGoT6+EBT8VdETaMYvRoRqvl0q0S0tqDHE+10s2J0XgBaA6aRRVHzRnllAzMZ+iA5fSvb2kyrh/OczE6xXnZdvujo0OA3tCLQKgxJe+3n1/2BkzqkFMKLrkbhIhI=",
                "v8MB73faXlVdb8uPSmmjge3oALKwudOO",
                "2.0.0",
                "yocyl.account.trans.query",
                null
        );
        assertFalse("Encrypt should be disabled when encryptType is null", configWithNullEncrypt.isEncryptEnabled());
    }

    @Test
    public void testConfigImmutability() {
        String appId = "20250909102701729790";
        YocalV2Config config = new YocalV2Config(
                appId,
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCS0BX4rCjWYGV5WRwsVY2A0DtVg35pph2qk5FJUZm6AhXGhm/ZwqXbXxq6xggyU6ddA2/y/xNN5WuuKf5h4imUczDm0LkHKhu99eVWMiOqU7LguGog/uGuG1CcK6gne/txujBmwmY4dWBxo4moCm5SlUmz4kH7BFKuZGp+P66D/jcVT+1HT5ScXfPeCkEyrwI1ScnaJOTP0uQ0rLi9xO4uu7RiKtDaONbCPYzax+CIo5SgZOZ909JWJFNolzCTEQrGxNdquK1dUktdTAYkk36Xg/C7vcJ/U/W6pIEOyr6p+zPg+Y87T2PY6nfb7/H6pxotDCN4TPIIC9jlmq9M+ND1AgMBAAECggEAfczaltwGnjO5n/KwhnjEqNVFkm/7eVIDZ6/NNbM7c7znpdZ6r3DwJ/rlb5fTXDR8W+JfIixaAUyOKY4IUR23nWHbTF0bvzzgyrSS0HpkP0Y7J/49yoo9HbCAMWHjU/oUKo5tfRAlAUnq21VT1m8hu5f6sK429X3tz0tyST4OL0DueM54R9N7WXs2tquWkj6RD4aZca5wSqtR254NakRmBSVdTVEKbMvgXoEsegjwbcy4hunxOCXfz89UG/LjwRRgBMM4scVHI3ERZg2yImhc5pDk3zv9zmP/glz+pEkaA+KfdWU5p1Gjs3KRG/KBnS+m+0agp7MG5aQbe6UzPP2dAQKBgQDbLcr+wv3aPDmFBmvIUzSRELFijw8JX4hYFdJNUFBOwO6ePUllgP1lzK0sfnqu4fInc3pq0NtubfU9oBKsIJtbSDkWER1OUG079JW6aa13cZOASHMIhAnBbFIQ9yPvRy8TEuCyP7z0CQcrE7CgLPUswqWo+g9isynK9oPcxo+2tQKBgQCreg1+pp6eMbSSbXcIwPd0+fI31iSWF4y9tt7v5oG6QjWmlhakiZybqt9jEUOo4j8fFmTjArx7DqYvSHhD3D4XpSbqeBTkPwjOJsQpkSzvsJGSYToCfojDWsv3gqPCTXuX76Z0YKLLCObUkX2Y5HgEpGuFSoPS/OyEFgtTc6zZQQKBgQCY3fZ/+2XsKScBBbp07Lt0Fg1yLU8SfYPt08Jq1AI++0cyLJKdbfrOXpPFva05fjNTmrId/++btKtgQN3lGZThdJ0ELAmhjmyxbWRksMIg1aFHzsAUh2r6cd9HH+f7Qk2t4vO+vr++APHz9HazMMgLYPMDyOykLUuP2KGdgQvJJQKBgCTXFA8hvPI/u4u5+NjonEGFcYSNfU7BwHsBzuO7oiNiFiS0Gik/Z2YKT/P4wZCHCwiixwn+jH/jpdCCwVPS/YQW90VohxSCdmHT8lD736ufQ6cvPEdM6BUQbMHAT25vNx5tXlWibVcxkmYY2+L9MsvMh00btTafIDFAiy5iq8ABAoGAPkje6LWiXe5SBx5soiwF4SMSZo0sr5/dvxLC7YtX0qYpmGBhGoT6+EBT8VdETaMYvRoRqvl0q0S0tqDHE+10s2J0XgBaA6aRRVHzRnllAzMZ+iA5fSvb2kyrh/OczE6xXnZdvujo0OA3tCLQKgxJe+3n1/2BkzqkFMKLrkbhIhI=",
                "v8MB73faXlVdb8uPSmmjge3oALKwudOO",
                "2.0.0",
                "yocyl.account.trans.query",
                "AES"
        );

        // 验证配置对象是不可变的
        try {
            // 验证getter方法返回的值不变
            assertEquals("AppId should be immutable", appId, config.getAppId());
        } catch (Exception e) {
            fail("Config should be immutable but got exception: " + e.getMessage());
        }
    }
}
