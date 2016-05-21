package de.goldmann.comercio.transfer.down;

import java.io.Serializable;

public class UserRow implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private long              userId;
    private String            login;

    // JSON!!
    public UserRow()
    {}

    public UserRow(long userId, String login)
    {
        this.userId = userId;
        this.login = login;
    }

    /**
     * @return the login
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * @return the userId
     */
    public long getUserId()
    {
        return userId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "UserRow [userId=" + userId + ", " + (login != null ? "login=" + login : "") + "]";
    }

}
