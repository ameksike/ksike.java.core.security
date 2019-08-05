package ksike.porter.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import ksike.mvc.KsController;
import ksike.mvc.KsModule;
import ksike.plugin.KsLoader;
import ksike.plugin.KsLoaderSubscribtor;
import ksike.plugin.KsPluginException;
import ksike.porter.Main;
import ksike.porter.model.User;

/**
 * @author Antonio Membrides Espinosa, Omar Gonzalez Amor
 * @made 19/04/2019
 * @version 1.0
 */
public class Default extends KsController implements KsLoaderSubscribtor{

    public void login(String user, String pass) {
        User usr = new User();
        usr.setEntityManager(((Main)this.handler).em);
        usr.loadBy(user);

        if (usr.password.equals(pass)) {
            ((Main) this.handler).setToken(usr);
            this.loginSuccess(user);
        } else {
            this.loginFailed(user);
        }
    }

    public void loginSuccess(String user) {
        try {
            ((Main) this.handler).gui.log("Auth messages", "login action for user:" + user + " was", "successful");
            //this.handler.helper.loader.load("ksike.dashboard.home", "home");
            this.handler.helper.loader.init();
        } catch (KsPluginException ex) {
            Logger.getLogger(Default.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loginFailed(String user) {
        ((Main) this.handler).gui.log("Auth messages", "user:" + user + " or password are", "incorrect");
    }

    @Override
    public void onEvent(String event, KsModule subject, Object handler, Object[] params) {
        
    }

    @Override
    public boolean checkPlugin(String name, KsModule mod, KsLoader handler) {
        
        System.out.println("CHECK <<<<<<< " + ((Main) this.handler).getUser().username + " <<< " + mod.getMetadata().getId() + " << " + mod.getMetadata().getName());
        return true;
    }
    
}
