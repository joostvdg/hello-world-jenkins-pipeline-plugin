package jenkins.plugins.hello_world;

import org.jenkinsci.plugins.scriptsecurity.sandbox.whitelists.Whitelisted;


import java.io.Serializable;

/**
 * @author Joost van der Griendt
 */
public class ResponseContentSupplier implements Serializable {

    private static final long serialVersionUID = 1L;

    private String greeting;

    public ResponseContentSupplier(String name) {
       greeting = String.format("Hello World, says %s", name);
    }

    @Whitelisted
    public String getGreeting() {
        return greeting;
    }


    @Override
    public String toString() {
        return greeting;
    }
}