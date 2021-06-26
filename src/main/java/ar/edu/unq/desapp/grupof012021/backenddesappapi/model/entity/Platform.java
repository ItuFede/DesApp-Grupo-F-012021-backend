package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.entity;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration.PlatformType;
import com.sun.istack.NotNull;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class Platform {
    private static final long serialVersionUID = -2343243243242432341L;
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(unique = true, name = "platformType")
    private PlatformType platformType;

    public Platform(String platformName) throws Exception {
        switch (platformName) {
            case "Netflix": {
                this.platformType = PlatformType.NETFLIX;
            }; break;
            case "Amazon Prime": {
                this.platformType = PlatformType.AMAZON_PRIME;
            }; break;
            case "Disney Plus": {
                this.platformType = PlatformType.DISNEY_PLUS;
            }; break;
            default: {
                throw new Exception("Invalid platform name.");
            }
        }
    }

    public Platform() { }

    public String getName() {
        return this.platformType.toString();
    }
}
