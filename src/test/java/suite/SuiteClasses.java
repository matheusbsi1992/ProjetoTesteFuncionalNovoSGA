package suite;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import regrasdenegocios.TestePaginaCargo;
import regrasdenegocios.TestePaginaLocal;
import regrasdenegocios.TestePaginaServico;
import regrasdenegocios.TestePaginaTriagemAlternativa;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestePaginaLocal.class
        , TestePaginaServico.class
        , TestePaginaTriagemAlternativa.class
        , TestePaginaCargo.class
})
public class SuiteClasses {

}
