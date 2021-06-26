package ar.edu.unq.desapp.grupof012021.backenddesappapi.model.enumeration;

public enum PlatformType {
    NETFLIX {
        public String toString() {
            return "Netflix";
        }
    },
    DISNEY_PLUS {
        public String toString() {
            return "Disney Plus";
        }
    },
    AMAZON_PRIME {
        public String toString() {
            return "Amazon Prime";
        }
    }
}
