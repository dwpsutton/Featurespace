package featurespace.easymock;

import examples.featurespace.easymock.ClassUnderTest;
import examples.featurespace.easymock.Collaborator;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;

//Extending EasyMockSupport is useful but not mandatory.
// It enables replyAll and verifyAll instead of replay(mock1, mock2, ...) and verify(mock1, mock2)
public class ExampleTest extends EasyMockSupport {
    private ClassUnderTest classUnderTest;
    private Collaborator collaborator;

    @Before
    public void setUp() {
        //1 Create the mock
        collaborator = mock(Collaborator.class);

        //2 Pass the mock into the class under test
        classUnderTest = new ClassUnderTest();
        classUnderTest.setListener(collaborator);
    }

    @Test
    public void addDocument() {
        // 3 Record what we expect the collaborator to do
        collaborator.documentAdded("New Document");
        // 4 Tell all mocks we are now doing the actual testing
        replayAll();
        // 5 Test
        //We are testing that documentAdded is called only once and receiving this exact parameter.
        //Any other call to our collaborator is a test failure
        classUnderTest.addDocument("New Document", "content");
        // 6 Make sure everything that was supposed to be called was called
        verifyAll();
    }
}