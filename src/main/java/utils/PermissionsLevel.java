package utils;

/**
 *
 * @author Rene Vera Apale
 */
public enum PermissionsLevel {
    
    ADMIN(0),
    GUEST(1);
    
    private final int LEVEL;
    
    private PermissionsLevel(int lvl) {
        LEVEL = lvl;
    }
    
    public int getLevel() {
        return LEVEL;
    }

    @Override
    public String toString() {
        switch (LEVEL) {
            case 0:
                return "Administrador";
            default:
                return "Invitado";
        }
    }
    
    public static PermissionsLevel fromIntegerLevel(int level) {
        switch (level) {
            case 0:
                return ADMIN;
            default:
                return GUEST;
        }
    }
}