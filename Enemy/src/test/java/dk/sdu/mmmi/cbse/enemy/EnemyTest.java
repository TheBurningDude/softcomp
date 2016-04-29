/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nasib
 */
public class EnemyTest {
    
    public EnemyTest() {
    }
    //metoden bliver kaldt før testen kører en gang
   /** @BeforeClass
    public static void setUpClass() {
        System.out.println("setup class");
    }
    //efter testen bliver den kaldt
    @AfterClass
    public static void tearDownClass() {
        System.out.println("teardown class");
    }
    //kaldt før hvert unit test
    @Before
    public void setUp() {
        System.out.println("setup");
    }
    //kaldt efter hvert unit test
    @After
    public void tearDown() {
        System.out.println("teardown");
    }

    /**
     * Test of process method, of class Enemy.
     */
    /*@org.junit.Test
    public void testProcess() {
        System.out.println("process");
        //Set up
        GameData gameData = null;
        Map<String, Entity> world = null;
        Entity entity = null;
        
        //execute test
        Enemy instance = new Enemy();
        instance.process(gameData, world, entity);
        
        //assert - asserter om testen har opfyldt det forventede
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        //assert.
    }

    /**
     * Test of shouldRemove method, of class Enemy.
     */
    /*@org.junit.Test
    public void testShouldRemove() {
        System.out.println("shouldRemove");
        Enemy instance = new Enemy();
        boolean expResult = false;
        boolean result = instance.shouldRemove();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }*/
    
    
}
