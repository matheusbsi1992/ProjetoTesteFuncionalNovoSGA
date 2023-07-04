package org.sga.teste.funcional.core;

public class Propriedades {

    public static boolean FINALIZA = true;

    public static Browser browser = Browser.CHROME;

    public static TipoExecucao tipodeExecucao = TipoExecucao.NUVEM;

    public enum Browser {
        CHROME,
        FIREFOX
    }

    public enum TipoExecucao{
        LOCAL,
        GRID,
        NUVEM
    }

}
