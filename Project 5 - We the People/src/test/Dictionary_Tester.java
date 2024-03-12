package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import proj5.Dictionary;

public class Dictionary_Tester {
    
    @Rule
    public Timeout timeout = Timeout.millis(100);

    private Dictionary dictionary;
   
    
    @Before
    public void setUp() throws Exception {
       
    }

    @After
    public void tearDown() throws Exception {
     
    }

}
