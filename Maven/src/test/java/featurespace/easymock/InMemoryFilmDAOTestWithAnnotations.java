package featurespace.easymock;

import entity.Film;
import org.easymock.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import session.InMemoryFilmDAO;
import session.Serializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.easymock.EasyMock.expect;

//@RunWith(EasyMockRunner.class)
public class InMemoryFilmDAOTestWithAnnotations extends EasyMockSupport {

	@Rule
	public EasyMockRule rule = new EasyMockRule(this);
	//Mock injection is done on fields (by EasyMockRule) so you won't need a setter only used for testing

	@Mock
	private Serializer serializer; // 1 Create the mock

	@Mock(type = MockType.NICE) // ignore unexpected method call
	//The annotation has an optional element, 'type', to refine the mock as a 'nice' mock or a 'strict' mock.
	private Map<Long, Film> films; // 1 Create the mock


	//Annotation to set on a field so that EasyMockRule will inject mocks created with Mock on its fields.
	@TestSubject
	private InMemoryFilmDAO classUnderTest = new InMemoryFilmDAO(); // 2 Have it set to the tested class

	@Test
	public void selectAllShouldCallDeserializeMethodOfSerializer() {
		expect(serializer.deserialize()).andReturn(new HashMap<>());// 3 Record what we expect the mock to do
		replayAll(); // 4 Tell all mocks we are now doing the actual testing
		classUnderTest.selectAll(); // 5 Test
		verifyAll(); // 6 Make sure everything that was supposed to be called was called
	}

	@Test
	public void insertShouldCallSerializeMethodOfSerializer() {
		serializer.serialize(new HashMap<>()); // 3 Record what we expect the mock to do
		expect(films.values()).andReturn(new ArrayList<Film>());// 3 Record what we expect the mock to do
		replayAll(); // 4 Tell all mocks we are now doing the actual testing
		classUnderTest.insert(new Film()); // 5 Test
		verifyAll(); // 6 Make sure everything that was supposed to be called was called
	}

}
