package de.goldmann.comercio.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.goldmann.comercio.domain.order.Order;

@Entity
@Table(name = "user_table")
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long              id;

    @Column(nullable = false, name = "firstname")
    private String            firstName;

    @Column(nullable = false, name = "lastname")
    private String            lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order>       orders;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account           account;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "BANKACCOUNT_ID")
    private BankAccount       bankAccount;

    public User()
    {}

    public User(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * @return the account
     */
    public Account getAccount()
    {
        return account;
    }

    /**
     * @return the bankAccount
     */
    public BankAccount getBankAccount()
    {
        return bankAccount;
    }

    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @return the id
     */
    public long getId()
    {
        return id;
    }

    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @return the orders
     */
    public List<Order> getOrders()
    {
        return orders;
    }

    /**
     * @param account
     *            the account to set
     */
    public void setAccount(Account account)
    {
        this.account = account;
    }

    /**
     * @param bankAccount
     *            the bankAccount to set
     */
    public void setBankAccount(BankAccount bankAccount)
    {
        this.bankAccount = bankAccount;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "User [id=" + id + ", " + (firstName != null ? "firstName=" + firstName + ", " : "")
                + (lastName != null ? "lastName=" + lastName + ", " : "")
                + (orders != null ? "orders=" + orders : "") + "]";
    }

}
