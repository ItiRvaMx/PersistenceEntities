package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Rene Vera Apale
 */
@Entity
public class CustomerAddress implements IPersistent, Serializable {
    
    private Integer id;
    private Customer customer;
    private String street, zip, name;
    private boolean active;

    @Override
    @Id
    @SequenceGenerator(name = "customeraddress_genid", sequenceName = "customeraddressid_seq", allocationSize = 1)
    @GeneratedValue(generator = "customeraddress_genid", strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public CustomerAddress makeDeepCopy() {
        CustomerAddress clone = new CustomerAddress();
        clone.setActive(active);
        clone.setCustomer(customer);
        clone.setId(id);
        clone.setName(name);
        clone.setStreet(street);
        clone.setZip(zip);
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (name != null)
            builder.append(name);
        if (street != null) {
            if (builder.length() > 0)
                builder.append(", ");
            builder.append(street);
        }
        if (zip != null) {
            if (builder.length() > 0)
                builder.append(", ");
            builder.append("ZIP: ").append(zip);
        }
        return builder.toString();
    }

    @Override
    public String fieldInfo() {
        return new StringBuffer()
            .append("Id: ").append(id).append("\n")
            .append("Customer: ").append(customer != null ? customer.getId() : null).append("\n")
            .append("Name: ").append(name)
            .append("Street: ").append(street).append("\n")
            .append("Zip: ").append(zip).append("\n")
            .append("Active: ").append(active)
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o.getClass() != getClass())) return false;
        CustomerAddress addr = (CustomerAddress)o;
        return ((addr.getCustomer() == null & customer == null) | (addr.getCustomer() != null && customer != null && addr.getCustomer().equals(customer))) &&
               ((addr.getName() == null & name == null) | (addr.getName() != null && name != null && addr.getName().equals(name))) &&
               ((addr.getId() == null & id == null) | (addr.getId() != null && id != null && addr.getId().equals(id))) &&
               ((addr.getStreet() == null & street == null) | (addr.getStreet() != null && street != null && addr.getStreet().equals(street))) &&
               ((addr.getZip() == null & zip == null) | (addr.getZip() != null && zip != null && addr.getZip().equals(zip))) &&
               addr.isActive() == active;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        if (id != null)
            hash = 97 * hash + id;
        if (name != null)
            hash = 97 * hash + name.hashCode();
        if (customer != null)
            hash = 97 * hash + customer.hashCode();
        if (street != null)
            hash = 97 * hash + street.hashCode();
        if (zip != null)
            hash = 97 * hash + zip.hashCode();
        hash = 97 * hash + (active ? 1 : 0);
        return hash;
    }
}