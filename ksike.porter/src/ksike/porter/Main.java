package ksike.porter;

import ksike.mvc.KsModule;
import ksike.plugin.KsMetadata;
import ksike.porter.controller.Default;
import ksike.porter.model.User;
import ksike.porter.view.Login;
import ksike.secretary.EntityManager;
import ksike.ui.bite.KsDashboard;

/**
 * @author Antonio Membrides Espinosa, Omar Gonzalez Amor
 * @made 19/04/2019
 * @version 1.0
 */
public class Main extends KsModule {

    public EntityManager em;
    public KsDashboard gui;
    private Default controllerDefault;
    private Login viewLogin;
    private User token;

    public Main() {
        this.token = null;
    }

    public void login() {
        this.gui.center.add(this.viewLogin);
    }

    public User getUser() {
        return this.token;
    }

    public void initViewLogin() {
        this.viewLogin = new Login();
        this.viewLogin.btnAction.addActionListener((java.awt.event.ActionEvent evt) -> {
            this.controllerDefault.login(this.viewLogin.jtfName.getText(), (String)viewLogin.jtfPass.getText());
        });

        this.viewLogin.jtfPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == evt.VK_ENTER) {
                    controllerDefault.login(viewLogin.jtfName.getText(), (String)viewLogin.jtfPass.getText());
                }
            }
        });
    }

    public void setToken(User token) {
        this.token = token;
    }

    @Override
    public void onInit() {
        this.controllerDefault = new Default();
        this.controllerDefault.setHandler(this);
        this.initViewLogin();
        this.login();
        this.helper.loader.subscribe(this.controllerDefault);
    }

    @Override
    public void onLoad() {
        this.gui = (KsDashboard) this.helper.lib.get("gui");
    }

    @Override
    public KsMetadata getMetadata() {
        return new Metadata();
    }

    @Override
    public void setDependency(Object subjet) {
        super.setDependency(subjet);

        if (subjet instanceof EntityManager) {
            this.em = (EntityManager) subjet;
        }
    }

    public boolean isAuthenticated() {
        return (this.getUser() != null);
    }
}
