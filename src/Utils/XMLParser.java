/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Vítor
 */
public class XMLParser {

    File f;
    SAXBuilder sb;
    Element mural;
    
    public XMLParser() {
        f = new File("D:\\Vítor\\Documentos\\NetBeansProjects\\S2AEI-Model\\src\\assets\\perguntas.txt");
        sb = new SAXBuilder();
    }
    
    public ArrayList<String> getTitulos() throws JDOMException, IOException {

        //Este documento agora possui toda a estrutura do arquivo.  
        Document d = sb.build(f);

        //Recuperamos o elemento root  
        mural = d.getRootElement();

        //Recuperamos os elementos filhos (children)  
        List elements = mural.getChildren();
        Iterator i = elements.iterator();
        
        ArrayList<String> titulo = new ArrayList<>();
        
        String s;

        //Iteramos com os elementos filhos, e filhos do dos filhos  
        while (i.hasNext()) {
            Element element = (Element) i.next();
            s = element.getChildText("textoExplicativo");
            titulo.add(s);
        }
        return titulo;
    }

    public ArrayList<String> getQuestoes() throws JDOMException, IOException{
        
        //Este documento agora possui toda a estrutura do arquivo.  
        Document d = sb.build(f);

        //Recuperamos o elemento root  
        mural = d.getRootElement();

        //Recuperamos os elementos filhos (children)  
        List elements = mural.getChildren();
        Iterator i = elements.iterator();
        
        ArrayList<String> questoes = new ArrayList<>();
        
        String s;

        //Iteramos com os elementos filhos, e filhos do dos filhos  
        while (i.hasNext()) {
            Element element = (Element) i.next();
            s = element.getChildText("questoesChave");
            questoes.add(s);
        }
        return questoes;
    }
}
