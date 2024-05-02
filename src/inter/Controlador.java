package inter;

import logic.*;

public class Controlador {
    private Lienzo objLienzo; //VISTA
    private HuffmanTree objArbol; //MODELO

    public Controlador(Lienzo objLienzo, HuffmanTree objArbol) {
        this.objLienzo = objLienzo;
        this.objArbol = objArbol;
    }
    
    public void iniciar() {
        objLienzo.setObjArbol(objArbol);
    }
}
