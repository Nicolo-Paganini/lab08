package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

import static it.unibo.deathnote.api.DeathNote.RULES;
import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class TestDeathNote {

    private DeathNote deathNote;

    @BeforeEach
    void init() {
        deathNote = new DeathNoteImpl();
    }

    @Test
    void testNegativeRule() {
        try {
            deathNote.getRule(0);
            fail("Get 0 rule is possible but shouldn't be");
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        try {
            deathNote.getRule(-1);
            fail("Get negative rule is possible but shouldn't be");
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    }

    @Test
    void testRules() {
        for (int i = 1; i <= RULES.size(); i++) {
            final var rule = deathNote.getRule(i);
            assertNotNull(rule);
            assertFalse(rule.isBlank());
        }
    }

    @Test
    void testNameWrite(){
        assertFalse(deathNote.isNameWritten("Luigi"));
        deathNote.writeName("Luigi");
        assertTrue(deathNote.isNameWritten("Luigi"));
        assertFalse(deathNote.isNameWritten("Carlo"));
        assertFalse(deathNote.isNameWritten(" "));
    }

    @Test
    void testCauseOfDeath() throws InterruptedException{
        try{
            deathNote.writeDeathCause("Implosione");
            fail("Writing a cause of death before a name should be impossible");
        } catch (final IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        deathNote.writeName("Luigi");
        assertEquals("heart attack", deathNote.getDeathCause("Luigi"));
        deathNote.writeName("Carlo");
        deathNote.writeDeathCause("karting accident");
        assertEquals("karting accident", deathNote.getDeathCause("Carlo"));
        sleep(100);
        assertFalse(deathNote.writeDeathCause("Inciampamento"));
        assertEquals("karting accident", deathNote.getDeathCause("Carlo"));
    }

    @Test
    void testDetailsOfDeath() throws InterruptedException{
        try{
            deathNote.writeDeathCause("ran for too long");
            fail("Writing the details of death before a name should be impossible");
        } catch (final IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        deathNote.writeName("Luigi");
        assertEquals(" ", deathNote.getDeathDetails("Luigi"));
        deathNote.writeDetails("ran for too long");
        assertEquals("ran for too long", deathNote.getDeathDetails("Luigi"));
        deathNote.writeName("Carlo");
        sleep(6100);
        assertFalse(deathNote.writeDetails("wrote many tests before dying"));
        assertEquals(" ", deathNote.getDeathDetails("Carlo"));
    } 
}