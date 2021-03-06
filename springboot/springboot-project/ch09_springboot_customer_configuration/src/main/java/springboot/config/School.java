package springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component//将此类交给spring容器进行管理
@ConfigurationProperties(prefix = "school")
public class School {

    private String name;

    private String website;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
