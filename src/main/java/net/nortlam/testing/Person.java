package net.nortlam.testing;

import java.io.Serializable;
import java.util.Objects;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.bson.Document;
import org.bson.types.ObjectId;

@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable {
    
    @XmlElement(name="_id", type=String.class, required=false, nillable = true)
    private String ID;
    
    @XmlElement(name="firstName", type=String.class, required=true, nillable = false)
    private String firstName;
    
    @XmlElement(name="lastName", type=String.class, required=true, nillable = false)
    private String lastName;
    
    @XmlElement(name="email", type=String.class, required=true, nillable = false)
    private String email;

    public Person() {
    }
    
    public Person(Document document) {
        ObjectId objectID = document.getObjectId("_id");
        this.ID = objectID.toString();
        this.firstName = document.getString("firstName");
        this.lastName = document.getString("lastName");
        this.email = document.getString("email");
    }

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    public boolean isValid() {
        return getFirstName() != null && getLastName() != null && getEmail() != null;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.ID);
        hash = 23 * hash + Objects.hashCode(this.firstName);
        hash = 23 * hash + Objects.hashCode(this.lastName);
        hash = 23 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        if(this.ID != null) builder.add("_id", this.ID);
        if(this.firstName != null) builder.add("firstName", this.firstName);
        if(this.lastName != null) builder.add("lastName", this.lastName);
        if(this.email != null) builder.add("email", this.email);
        
        return builder.build().toString();
    }
}
