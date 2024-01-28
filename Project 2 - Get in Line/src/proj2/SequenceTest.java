package proj2;

import org.junit.*;
import org.junit.rules.Timeout;
import static org.junit.Assert.*;


import proj2.Sequence;

public class SequenceTest {


    @Rule // a test will fail if it takes longer than 1/10 of a second to run
    public Timeout timeout = Timeout.millis(100);



}