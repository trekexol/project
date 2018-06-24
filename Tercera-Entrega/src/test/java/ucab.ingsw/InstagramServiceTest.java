 /*
 * Created on @Nov 14, 2012
 * Copyright - Confidential use
 */
 package ucab.ingsw;
 import lombok.extern.slf4j.Slf4j;
 import org.jmock.Expectations;
import org.jmock.Mockery;
 import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
 import ucab.ingsw.service.InstagramService;
import ucab.ingsw.model.User;
import ucab.ingsw.repository.UserRepository;
 import org.jmock.lib.legacy.ClassImposteriser;
 import ucab.ingsw.response.MediaUrlsResponse;

 @Slf4j
@RunWith(JMock.class)
public class InstagramServiceTest {



    private InstagramService serviceInst;

    private UserRepository antenaDAOMock;

    private Mockery context = new JUnit4Mockery();

     private Mockery context3 = new JUnit4Mockery();

    public InstagramServiceTest() {
    }

    /**
     * Fase de inicializacion donde se realiza la puesta a punto de todos los
     * Fixtures involucrados en la prueba.
     */
    @Before
    public void inicializacion() {
        antenaDAOMock = context.mock(UserRepository.class);
        context3.setImposteriser(ClassImposteriser.INSTANCE);
        serviceInst = context3.mock(InstagramService.class);
    }

    /**
     * Fase de finalizacion de los elementos usados durante la ejecucion de cada
     * prueba.
     */
    @After
    public void tearDown() {
        context = null;
        antenaDAOMock = null;
    }

    //--------------------------------------------------------------------------
    // Test Case Scenarios
    //--------------------------------------------------------------------------
    /**
     * Escenario de prueba que permite el registro de antenas con datos validos.
     */
    @Test
    public void instagramServiceTest() {


    }

    @Test
    public void testProbarRespuestaInstagram() {

       User user = new User();
       user.setId(Long.parseLong("1529338943629"));
        context3.checking(new Expectations() {
            {
                oneOf(serviceInst).searchTag2(String.valueOf(user.getId()),"KH");
                will(returnValue(true));
            }
        });
        try {
            boolean respuesta = serviceInst.searchTag2(String.valueOf(user.getId()),"KH");
           Assert.assertEquals(respuesta, true);
     log.info("exito");
        } catch (Throwable FailedTestException) {
            log.info("Excepción.");
        }
    }

}
