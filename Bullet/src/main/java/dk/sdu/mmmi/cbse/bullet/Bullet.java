/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullet;

import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
//import dk.sdu.mmmi.cbse.asteroidsapi.IBulletService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

/**
 *
 * @author nasib
 */
public class Bullet {
    
    ComponentContext context;
    ServiceReference reference;
    //IBulletService bulletService ;
    
    public void activate(ComponentContext context){
        if (reference != null) {
            //bulletService = (IBulletService) context.locateService(
              //      "IBulletService", reference);

            System.out.println("WUDIAAAAAAAAAAAAAAAAAAAAA");

        }
        
    }
    
    public void gotService(ServiceReference reference) {
        System.out.println("Bind Service");
        this.reference = reference;
    }

    public void lostService(ServiceReference reference) {
        System.out.println("unbind Service");
        this.reference = null;
    }

}
