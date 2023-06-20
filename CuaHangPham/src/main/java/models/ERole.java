package models;

public enum ERole {
    ADMIN("ADMIN"), GUEST("GUEST");

    private String value;

    ERole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static ERole fromValue(String value){
        ERole[] values = values();
        for (ERole role:values) {
            if (role.value.equals(value))
                return role;
        }
        throw new IllegalArgumentException("invalid");
    }
}
