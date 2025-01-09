package com.cars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Config {
    private Browser browser;
    private Environment environment;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Browser {
        private String browser_name;
        private boolean headless;
        private boolean attach_screenshot;
        private int web_element_timeout;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Environment {
        private Test_int test_int;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Test_int {
        private String env_name;
        private String app_url;
        private String title;
    }
}
