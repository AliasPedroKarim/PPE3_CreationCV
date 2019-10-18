/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.karim.main.utils;

import java.util.ArrayList;

/**
 *
 * @author karim
 */
public class CV {
    private ArrayList<Formation> formations;
    private ArrayList<ExperiencePro> experiencePros;

    public CV(ArrayList<Formation> formations, ArrayList<ExperiencePro> experiencePros) {
        this.formations = formations;
        this.experiencePros = experiencePros;
    }

    public CV() {
        
    }
    
    
}
