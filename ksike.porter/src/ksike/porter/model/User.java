/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ksike.porter.model;

import ksike.mvc.KsEntity;
import ksike.secretary.data.DtBase;

/**
 * @author Antonio Membrides Espinosa
 * @made 09/06/2019
 * @version 1.0
 */
public class User extends KsEntity {

    public String id;
    public String dni;
    public String role;
    public String firstname;
    public String lastname;
    public String password;
    public String username;

    public User() {
        this.id = "";
        this.firstname = "";
        this.lastname = "";
        this.password = "";
        this.username = "";
        this.role = "";
        this.dni = "";
        this._table = "rbac_user";
    }

    public User loadBy(String username) {
        if (this.em != null) {
            String sql = this.em.lqm().select(_table, "username", "'" + username + "'").flush();
            this.em.connect();
            DtBase tmp = this.em.query(sql);
            while (tmp.next()) {
                this.id = tmp.get("id");
                this.firstname = tmp.get("firstname");
                this.lastname = tmp.get("lastname");
                this.username = tmp.get("username");
                this.password = tmp.get("password");
                this.dni = tmp.get("dni");
                this.role = tmp.get("role");
            }
            this.em.disconnetc();
            System.out.println(this.username + " -- " + this.password);
        } else {

        }
        return this;
    }
}
