/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ez_squeeze;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * changelog:
 * added methods to return the name, description and mod
 * added method to perform the mod on given ids
 */

/**
 *
 * @author Nick Stanish
 */
public class upgradeParser{
	
    private File file;
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document doc;
    private NodeList nodeList;
    public upgradeParser(File a) throws ParserConfigurationException, SAXException, IOException{
          file = new File(a + "");
          dbf = DocumentBuilderFactory.newInstance();
          db = dbf.newDocumentBuilder();
          doc = db.parse(file);
          
          doc.getDocumentElement().normalize();
          nodeList = doc.getElementsByTagName("item");

    }
    public void printNode(int a){
          //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
          //NodeList nodeList = doc.getElementsByTagName("item");
          //System.out.println("Information of all Upgrades");
          //for (int s = 0; s < nodeList.getLength(); s++) {
          if(a<nodeList.getLength()){
              Node fstNode = nodeList.item(a);
              if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element fstElmnt = (Element) fstNode;
                  NodeList name = fstElmnt.getElementsByTagName("name");
                  Element nameElement = (Element) name.item(0);
                  NodeList nameNL = nameElement.getChildNodes();
                  System.out.println("Name : "  + nameNL.item(0).getNodeValue());
                  NodeList description = fstElmnt.getElementsByTagName("description");
                  Element descriptionElement = (Element) description.item(0);
                  NodeList descriptionNL = descriptionElement.getChildNodes();
                  System.out.println("Description : " + descriptionNL.item(0).getNodeValue());
                  NodeList variable = fstElmnt.getElementsByTagName("variable");
                  Element variableElement = (Element) variable.item(0);
                  NodeList variableNL = variableElement.getChildNodes();
                  System.out.println("Variable : " + variableNL.item(0).getNodeValue());
                  NodeList variableId = fstElmnt.getElementsByTagName("varId");
                  Element variableIdElement = (Element) variableId.item(0);
                  NodeList variableIdNL = variableIdElement.getChildNodes();
                  System.out.println("Variable ID: " + variableIdNL.item(0).getNodeValue());
                  NodeList modification = fstElmnt.getElementsByTagName("modification");
                  Element modificationElement = (Element) modification.item(0);
                  NodeList modificationNL = modificationElement.getChildNodes();
                  System.out.println("Modification : " + modificationNL.item(0).getNodeValue());
              }
          }
          else{
              System.out.println("no upgrade found");
          }
    }
    public int getNodeLength(){
    	return nodeList.getLength();
    }
    public String getName(int a){
        //NodeList nodeList = doc.getElementsByTagName("item");
        String nameUpdate = "";
          //System.out.println("Information of all Upgrades");
          //for (int s = 0; s < nodeList.getLength(); s++) {
          if(a < nodeList.getLength()){
              Node fstNode = nodeList.item(a);
              if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element fstElmnt = (Element) fstNode;
                  NodeList name = fstElmnt.getElementsByTagName("name");
                  Element nameElement = (Element) name.item(0);
                  NodeList nameNL = nameElement.getChildNodes();
                  nameUpdate = nameNL.item(0).getNodeValue();

              }
          }
          else{
              System.out.println("no name found");
          }
          return nameUpdate;
    }
    public String getDescription(int a){
        //NodeList nodeList = doc.getElementsByTagName("item");
        String descript = "";
          //System.out.println("Information of all Upgrades");
          //for (int s = 0; s < nodeList.getLength(); s++) {
          if(a < nodeList.getLength()){
              Node fstNode = nodeList.item(a);
              if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element fstElmnt = (Element) fstNode;
                   NodeList description = fstElmnt.getElementsByTagName("description");
                  Element descriptionElement = (Element) description.item(0);
                  NodeList descriptionNL = descriptionElement.getChildNodes();
                  descript = descriptionNL.item(0).getNodeValue();


              }
          }
          else{
              System.out.println("no description found");
          }
          return descript;

    }
    public double getMod(int a){
        //NodeList nodeList = doc.getElementsByTagName("item");
        double mod = 0;
          //System.out.println("Information of all Upgrades");
          //for (int s = 0; s < nodeList.getLength(); s++) {
          if(a < nodeList.getLength()){
              Node fstNode = nodeList.item(a);
              if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element fstElmnt = (Element) fstNode;
                  NodeList modification = fstElmnt.getElementsByTagName("modification");
                  Element modificationElement = (Element) modification.item(0);
                  NodeList modificationNL = modificationElement.getChildNodes();
                  mod = Double.parseDouble(modificationNL.item(0).getNodeValue());

              }
          }
          else{
              System.out.println("no mod found");
          }
          return mod;

    }
        public double getPrice(int a){
	        //NodeList nodeList = doc.getElementsByTagName("item");
	        double cost = 0;
	          //System.out.println("Information of all Upgrades");
	          //for (int s = 0; s < nodeList.getLength(); s++) {
	          if(a < nodeList.getLength()){
	              Node fstNode = nodeList.item(a);
	              if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	                  Element fstElmnt = (Element) fstNode;
	                  NodeList price = fstElmnt.getElementsByTagName("price");
	                  Element priceElement = (Element) price.item(0);
	                  NodeList priceNL = priceElement.getChildNodes();
	                  cost = Double.parseDouble(priceNL.item(0).getNodeValue());

	              }
	          }
	          else{
	              System.out.println("no price found");
	          }
	          return cost;

    }
      public void applyUpgrade(int a){
          //NodeList nodeList = doc.getElementsByTagName("item");
          //System.out.println("Information of all Upgrades");
          //for (int s = 0; s < nodeList.getLength(); s++) {
          if(a<nodeList.getLength()){
              Node fstNode = nodeList.item(a);
              if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                  Element fstElmnt = (Element) fstNode;
                  NodeList variableId = fstElmnt.getElementsByTagName("varId");
                  Element variableIdElement = (Element) variableId.item(0);
                  NodeList variableIdNL = variableIdElement.getChildNodes();
                  System.out.println("Variable ID: " + variableIdNL.item(0).getNodeValue());
                  NodeList modification = fstElmnt.getElementsByTagName("modification");
                  Element modificationElement = (Element) modification.item(0);
                  NodeList modificationNL = modificationElement.getChildNodes();
                  System.out.println("Modification : " + modificationNL.item(0).getNodeValue());
                  int id = Integer.parseInt(variableIdNL.item(0).getNodeValue());
                  double mod = Double.parseDouble(modificationNL.item(0).getNodeValue());
                  id(id,mod);
              }
          }
          else{
              System.out.println("no upgrade found");
          }

      }

      public void id(int x, double y){
          /*
           * to add an item to be able to be modded create a varId for the item and
           * that id corresponds with a case id:
           * use a get method then add the return from that to the mod y, then set the
           * variable, may have to change variables and getter/setter to static in
           * EZ_Squeeze_EmpireView, make sure the value is the correct format: i.e. int or double
           */
    	  /*
          switch(x){
              case 0:
                  int a0 = EZ_Squeeze_EmpireView.getIceStart();
                  EZ_Squeeze_EmpireView.setIceStart(EZ_Squeeze_EmpireView.formatInt(a0 + y));
                  EZ_Squeeze_EmpireView.setIce(EZ_Squeeze_EmpireView.formatInt(y));
                  break;
              case 1:
                  double a1 = EZ_Squeeze_EmpireView.getWaitTime();
                  EZ_Squeeze_EmpireView.setWaitTime(a1 + y);
                  break;
              case 2:
            	  
            	  //minweather
            	  break;
              case 3:
            	  //minIceFactor
            	  break;
              case 4:
            	  //supersize
            	  break;
              case 5:
            	  //minRecipe
            	  break;
              case 6:
            	  EZ_Squeeze_EmpireView.setCupsPerPitcher(EZ_Squeeze_EmpireView.formatInt(y));
            	  break;
              default:
                  System.out.println("id points to no variable");
                  JFrame frame = new JFrame();
                  JOptionPane.showMessageDialog(frame, "Unable to apply upgrade \nID found:" + x +"\nID Pointer Exception");


          } */
    }


}