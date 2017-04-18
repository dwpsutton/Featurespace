package featurespace.easymock;

import entity.Film;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Test;
import session.InMemoryFilmDAO;
import session.Serializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.easymock.EasyMock.expect;

//Extending EasyMockSupport is useful but not mandatory.
// It enables replyAll and verifyAll instead of replay(mock1, mock2, ...) and verify(mock1, mock2)
public class InMemoryFilmDAOTest extends EasyMockSupport {

    private InMemoryFilmDAO classUnderTest;
    private Serializer serializer;
    private Map<Long, Film> films;

    @Before
    public void setUp() {
        // 1 Create the mocks
        serializer = mock(Serializer.class);
        //niceMock avoids AssertionError for unexpected method calls
        films = niceMock(HashMap.class);
        //pass mock objects into class under test
        classUnderTest = new InMemoryFilmDAO(films, serializer);
    }

    @Test
    public void selectAllShouldCallDeserializeMethodOfSerializer() {
        // 3 Record what we expect the mock to do
        expect(serializer.deserialize()).andReturn(new HashMap<>());
        // 4 Tell all mocks we are now doing the actual testing
        replayAll();
        // 5 Test
        classUnderTest.selectAll();
        // 6 Make sure everything that was supposed to be called was called
        verifyAll();
    }

    @Test
    public void insertShouldCallSerializeMethodOfSerializer() {
        // 3 Record what we expect the mock to do
        serializer.serialize(new HashMap<>());
        // 3 Record what we expect the mock to do
        expect(films.values()).andReturn(new ArrayList<Film>());
        // 4 Tell all mocks we are now doing the actual testing
        replayAll();
        // 5 Test
        classUnderTest.insert(new Film());
        // 6 Make sure everything that was supposed to be called was called
        verifyAll();
    }
}
