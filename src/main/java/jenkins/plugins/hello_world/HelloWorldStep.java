package jenkins.plugins.hello_world;

import hudson.Extension;
import hudson.model.Run;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractStepImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractSynchronousNonBlockingStepExecution;
import org.jenkinsci.plugins.workflow.steps.StepContextParameter;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author Joost van der Griendt
 */
public class HelloWorldStep extends AbstractStepImpl {

    private static final long serialVersionUID = 1L;

    private String name = "Jenkins";

    @DataBoundConstructor
    public HelloWorldStep(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ResponseContentSupplier createGreeting(Run<?,?> run, TaskListener listener)
            throws InterruptedException, IOException
    {
        return new ResponseContentSupplier(name);
    }


    @Override
    public HelloWorldStep.DescriptorImpl getDescriptor() {
        return (HelloWorldStep.DescriptorImpl) super.getDescriptor();
    }

    @Extension
    public static final class DescriptorImpl extends AbstractStepDescriptorImpl {

        public DescriptorImpl() {
            super(Execution.class);
        }

        @Override
        public String getFunctionName() {
            return "helloWorld";
        }

        @Override
        public String getDisplayName() {
            return "Say hello";
        }

    }

    public static final class Execution extends AbstractSynchronousNonBlockingStepExecution<jenkins.plugins.hello_world.ResponseContentSupplier> {

        @Inject
        private transient HelloWorldStep step;

        @StepContextParameter
        private transient Run run;

        @StepContextParameter
        private transient TaskListener listener;

        @Override
        protected jenkins.plugins.hello_world.ResponseContentSupplier run() throws Exception {
            return step.createGreeting(run, listener);
        }

    }

}
