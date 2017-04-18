package featurespace.easymock;

import examples.featurespace.easymock.ClassUnderTest;
import examples.featurespace.easymock.Collaborator;
import org.easymock.*;
import org.junit.*;

//Extending EasyMockSupport is useful but not mandatory.
// It enables replyAll and verifyAll instead of replay(mock1, mock2, ...) and verify(mock1, mock2)
public class ExampleTestWithAnnotations extends EasyMockSupport {

    @Rule
    public EasyMockRule rule = new EasyMockRule(this);
    //Mock injection on fields, so no need for a setter

    @Mock
    private Collaborator collaborator; // 1 Create the mock

    //Annotation to set on a field so that EasyMockRule will inject mocks created with Mock on its fields.
    @TestSubject
    private ClassUnderTest classUnderTest = new ClassUnderTest(); // 2 Have it set to the tested class

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