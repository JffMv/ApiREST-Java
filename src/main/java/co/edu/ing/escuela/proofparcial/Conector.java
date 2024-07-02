package co.edu.ing.escuela.proofparcial;

import java.io.IOException;

/**
 * The Conector interface defines a method for consulting data based on time and company parameters.
 */
public interface Conector {

    /**
     * Consults data based on the specified time and company parameters.
     *
     * @param time    the time parameter for the consultation.
     * @param company the company parameter for the consultation.
     * @return a String representing the consultation result.
     * @throws IOException if an I/O error occurs during the consultation.
     */
    public String consult(String time, String company) throws IOException;
}
