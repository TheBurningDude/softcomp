/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bulletclient;

import dk.sdu.mmmi.cbse.services.IEntityProcessingService;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;

/**
 *
 * @author nasib
 */
public class BulletActivator {
    
    public static ComponentContext context;
    public static ServiceReference bulletReference;
    public static IEntityProcessingService bulletProcessor;

    public void activate(ComponentContext context)
    {
        if (bulletReference != null)
        {
            bulletProcessor = (IEntityProcessingService)context.
                    locateService(
                            "IEntityProcessingService", bulletReference);
        }

    }

    public void gotService(ServiceReference reference)
    {
        bulletReference = reference;
    }

    public void lostService(ServiceReference reference)
    {
        bulletReference = null;
    }
    
}
