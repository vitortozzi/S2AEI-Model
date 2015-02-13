/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Vítor
 */
public class StringUtils {
    
    public boolean isEmpty(String string){
        if(string.equals("")){
            return true;
        }
        else return false;
    }
    
    public boolean outOfBounds(String string, int max){
        if(string.length() > max){
            return true;
        }
        else return false;
    }
    
    public boolean isStatusValid(String status){
        if(status.equals("Ativo") || status.equals("Inativo") || status.equals("Bloqueado")){
            return true;
        }
        else return false;
    }
    
}
