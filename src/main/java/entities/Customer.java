package entities;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import org.hibernate.annotations.Formula;

/**
 *
 * @author Rene Vera Apale
 */
@Entity
public class Customer implements IPersistent, Serializable {

    private Integer id;
    private String name, lastName, vat, email;
    private Set<CustomerAddress> addresses;
    private boolean active;

    @Override
    @Id
    @SequenceGenerator(name = "customer_genid", sequenceName = "customerid_seq", allocationSize = 1)
    @GeneratedValue(generator = "customer_genid", strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column
    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    @Column
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    @Transient
    @Formula("name || lastName")
    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(name)
          .append(" ")
          .append(lastName);
        return sb.toString();
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<CustomerAddress> getAddresses() {
        if (addresses == null)
            addresses = new LinkedHashSet<>();
        return addresses;
    }

    public void setAddresses(Set<CustomerAddress> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String fieldInfo() {
        return new StringBuffer()
                .append("ID: ").append(id).append("\n")
                .append("Name: ").append(name).append("\n")
                .append("Last name: ").append(lastName).append("\n")
                .append("Vat: ").append(vat).append("\n")
                .append("Email: ").append(email).append("\n")
                .append("Active: ").append(active).toString();
    }
    
    public Customer makeDeepCopy() {
        Customer clone = new Customer();
        clone.setActive(active);
        clone.setAddresses(addresses);
        clone.setEmail(email);
        clone.setId(id);
        clone.setLastName(lastName);
        clone.setName(name);
        clone.setVat(vat);
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (name != null)
            builder.append(name);
        if (name != null && lastName != null)
            builder.append(" ").append(lastName);
        if (vat != null) {
            if (builder.length() == 0)
                builder.append("---");
            builder.append("[").append(vat).append("]");
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o.getClass() != getClass())) return false;
        Customer other = (Customer)o;
        return ((other.getId() == null & id == null) | (other.getId() != null && id != null && other.getId().equals(id))) &&
               ((other.getVat() == null & vat == null) | (other.getVat() != null && vat != null && other.getVat().equals(name))) &&
               other.isActive() == active;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        if (id != null)
            hash = 41 * hash + id;
        if (name != null)
            hash = 41 * hash + name.hashCode();
        if (vat != null)
            hash = 41 * hash + vat.hashCode();
        hash = 41 * hash + (active ? 1 : 0);
        return hash;
    }
}