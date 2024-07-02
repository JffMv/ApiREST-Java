package co.edu.ing.escuela.proofparcial;

import java.io.IOException;

public interface Conector {
    public String consult(String time, String company) throws IOException;
}
