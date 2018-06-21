package models;

import java.util.Map;
import java.util.TreeMap;

public class AWUser
{
	
	private String id;

    private long createdAt;
    
    private long modifiedAt;
    
    private String accountId;
    
    private String login;
    
    private String firstName;
    
    private String lastName;
    
    private boolean bot;
    
    private boolean active;
    
    private String status;

    
    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }
    
    public long getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (long createdAt)
    {
        this.createdAt = createdAt;
    }
    public long getModifiedAt ()
    {
        return modifiedAt;
    }

    public void setModifiedAt (long modifiedAt)
    {
        this.modifiedAt = modifiedAt;
    }
    public String getAccountId ()
    {
        return accountId;
    }

    public void setAccountId (String accountId)
    {
        this.accountId = accountId;
    }
    public String getLogin ()
    {
        return login;
    }

    public void setLogin (String login)
    {
        this.login = login;
    }
    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }
    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }
    
    public boolean getBot ()
    {
        return bot;
    }

    public void setBot (boolean bot)
    {
        this.bot = bot;
    }
    
    public boolean getActive ()
    {
        return active;
    }
    public void setActive (boolean active)
    {
        this.active = active;
    }
    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
    @Override
    public String toString() {
        return firstName+lastName;
    }    

}
