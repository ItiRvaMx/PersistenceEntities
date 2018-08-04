package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import jpa.converters.PermissionsLevelConverter;
import utils.PermissionsLevel;

/**
 *
 * @author Rene Vera Apale
 */
@Entity
public class Sysuser implements IPersistent, Serializable {
    
    private Integer id;
    private String name, login, password, passwordSalt;
    private PermissionsLevel permissions;

    /**
     * Gets the database ID for the user
     * @return Database ID for the user
     */
    @Override
    @Id
    @SequenceGenerator(name = "sysuser_idgen", sequenceName = "sysuserid_seq", allocationSize = 1)
    @GeneratedValue(generator = "sysuser_idgen", strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    /**
     * Sets the database ID for the user
     * @param id Database ID for the user
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Gets the real name stored for the user
     * @return The real name of the user
     */
    @Column
    public String getName() {
        return name;
    }

    /**
     * Sets the real name for the user in the database
     * @param name The real name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name used to perform a login
     * @return The user's login name
     */
    @Column
    public String getLogin() {
        return login;
    }

    /**
     * Sets the name that will be used to perform a login
     * @param login The user's login name
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets the user's password
     * @return The user's password
     */
    @Column
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's login password 
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the Base64 representation of the data used to salt the user's password
     * @return Base64 representation of the salt
     */
    @Column
    public String getPasswordSalt() {
        return passwordSalt;
    }

    /**
     * Sets the Base64 representation of the data used to salt the user's password
     * @param passwordSalt Base64 representation of the salt
     */
    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    /**
     * Gets the level of permissions that the user is granted
     * @return Level of permissions for the user
     */
    @Column
    @Convert(converter = PermissionsLevelConverter.class)
    public PermissionsLevel getPermissions() {
        return permissions;
    }

    /**
     * Sets the level of permissions that will be granted to the user
     * @param permissions Level of permissions for the user
     */
    public void setPermissions(PermissionsLevel permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (name != null)
            builder.append(name);
        if (login != null) {
            if (builder.length() > 0)
                builder.append(" ");
            builder.append("[").append(login).append("]");
        }
        return builder.toString();
    }

    @Override
    public String fieldInfo() {
        return new StringBuffer()
                .append("ID: ").append(id).append("\n")
                .append("Name: ").append(name).append("\n")
                .append("Login: ").append(login).append("\n")
                .append("Password: ").append(password).append("\n")
                .append("Password salt: ").append(passwordSalt).append("\n")
                .append("Permissions level: ").append(permissions.toString()).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o.getClass() != getClass())) return false;
        Sysuser usr = (Sysuser)o;
        return ((usr.getId() == null & id == null) | (usr.getId() != null && id != null && usr.getId().equals(id))) &&
               ((usr.getLogin() == null & login == null) | (usr.getLogin() != null && login != null && usr.getLogin().equals(login))) &&
               ((usr.getPermissions() == null && permissions == null) | (usr.getPermissions() != null && permissions != null && usr.getPermissions().equals(permissions)));
    }

    @Override
    public int hashCode() {
        int hash = 37;
        if (id != null)
            hash = 71 * hash + id;
        if (login != null)
            hash = 71 * hash + login.hashCode();
        if (permissions != null)
            hash = 71 * hash + permissions.hashCode();
        return hash;
    }
}