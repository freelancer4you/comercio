package de.goldmann.comercio.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bankaccount")
public class BankAccount implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long              id;

    /**
     * 
     */
    @Column(nullable = false)
    public String             institute;
    /**
     * 
     */
    @Column(nullable = false, unique = true)
    public String             iban;
    /**
     * 
     */
    @Column(nullable = false)
    public String             bic;

    public BankAccount()
    {}

    /**
     * Getter of bic
     */
    public String getBic()
    {
        return bic;
    }

    /**
     * Getter of iban
     */
    public String getIban()
    {
        return iban;
    }

    /**
     * @return the institute
     */
    public String getInstitute()
    {
        return institute;
    }

    /**
     * Setter of bic
     */
    public void setBic(String bic)
    {
        this.bic = bic;
    }

    /**
     * Setter of iban
     */
    public void setIban(String iban)
    {
        this.iban = iban;
    }

    /**
     * @param institute
     *            the institute to set
     */
    public void setInstitute(String institute)
    {
        this.institute = institute;
    }

}
