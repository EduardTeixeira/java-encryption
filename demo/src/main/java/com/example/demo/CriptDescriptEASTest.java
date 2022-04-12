import com.diebold.servcore.webservice.util.CriptDescriptEAS;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CriptDescriptEASTest {

    private String campoSenha;
    private String senaCriptografada;
    private CriptDescriptEAS decriptor;

    public CriptDescriptEASTest(){
        this.campoSenha =  "06.06.06.06.06.06.06.06";
        this.senaCriptografada = "T3mtkO21XWl4bYyVMINzGayFpIBp1kP5ciYafwQxUW4=";
        this.decriptor = new CriptDescriptEAS();
    }
    @Test
    public void deveIncriptarString(){
        String newEncryptedString = decriptor.encrypt(campoSenha);
        assertEquals(senaCriptografada,newEncryptedString);
    }
    @Test
    public void deveDescriptarString(){
       String newOriginalString = decriptor.decrypt(senaCriptografada);
       assertEquals(campoSenha,newOriginalString);
    }
    @Test
    public void deveEntrarExceptionMetodoDecrypt(){
        String errorString = decriptor.decrypt(null);
        assertNull(errorString);
    }
    @Test
    public void deveEntrarExceptionMetodoEncrypt(){
        String errorString = decriptor.encrypt(null);
        assertNull(errorString);
    }
    @Test
    public void naoDeveDescriptarStrinJaDescriptada(){
        String testString = decriptor.decrypt(campoSenha);
        assertEquals(testString, campoSenha);
    }
}

