/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application1;
/**
 *
 * @author ouertani
 */
public class fos_user {
    private int id;			
    private String username;		
    private String username_canonical;			
    private String email;			
    private String email_canonical;			
    private int enabled;			
    private String salt;			
    private String password;		
    private int last_login;			
    private String confirmation_token;		
    private int password_requested_at;
    private String roles;
    private int numcarte;
    private int solde;

    public fos_user(String username, String email, String password, int numcarte) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.numcarte=numcarte;

        
      
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public int getNumcarte() {
        return numcarte;
    }

    public void setNumcarte(int numcarte) {
        this.numcarte = numcarte;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLast_login() {
        return last_login;
    }

    public void setLast_login(int last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public int getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(int password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public fos_user() {
    }
    
    
    public fos_user(int id, String username, String username_canonical, String email, String email_canonical, int enabled, String salt, String password, int last_login, String confirmation_token, int password_requested_at, String roles,int numcarte,int solde) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.last_login = last_login;
        this.confirmation_token = confirmation_token;
        this.password_requested_at = password_requested_at;
        this.roles=roles;
        this.numcarte=numcarte;
        this.solde=solde;
    }

    public fos_user(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        
        return username; //To change body of generated methods, choose Tools | Templates.
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
        final fos_user other = (fos_user) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        return hash;
    }
    
    
    
}
