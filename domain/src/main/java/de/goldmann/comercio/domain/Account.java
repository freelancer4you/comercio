package de.goldmann.comercio.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_table")
public class Account implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long              id;

    /**
     * 
     */
    @Column(nullable = false, unique = true)
    private String            email;

    /**
     * 
     */
    @Column(nullable = false, unique = true)
    private String            login;

    /**
     * 
     */
    @Column(nullable = false)
    private String            password;

    public Account()
    {}

    /**
     * Getter of email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @return the id
     */
    public long getId()
    {
        return id;
    }

    /**
     * Getter of login
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * Getter of password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Setter of email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Setter of login
     */
    public void setLogin(String login)
    {
        this.login = login;
    }

    /**
     * Setter of password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "Account [" + (email != null ? "email=" + email + ", " : "")
                + (login != null ? "login=" + login + ", " : "")
                + (password != null ? "password=" + password : "") + "]";
    }

}
