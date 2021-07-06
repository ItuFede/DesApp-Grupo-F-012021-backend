package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration;

public enum PlatformType {
    NETFLIX("Netflix"),
    DISNEY_PLUS("Disney Plus"),
    AMAZON_PRIME("Amazon Prime");

    public final String platformString;

    PlatformType(String platformType) {
        this.platformString = platformType;
    }

    public String getPlatformString() {
        return this.platformString;
    }
}
