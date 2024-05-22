package model.algorithme;

import java.util.*;
import model.algorithme.*;

public class NoeudArbre {

   private int ligne,colonne,profondeur,note;
   private ArrayList<NoeudArbre> childs;
   private NoeudArbre father;
 
   public NoeudArbre(int c, int p){
      this.colonne = c;
      this.profondeur = p;
      this.childs = new ArrayList<NoeudArbre>();
      this.father = null;
      this.note = -1;
   }
 
   public void addChild(NoeudArbre noeud){
      noeud.addFather(this);
      this.childs.add(noeud);
   }

   public void addFather(NoeudArbre pere){
      this.father = pere;
   }

   public NoeudArbre getFather(){
      return this.father;
   }

   public ArrayList<NoeudArbre> getChilds(){
      return this.childs;
   }

   public void setNote(int n){
      this.note = n;
   }

   public int getNote(){
      return this.note;
   }

   public String toString(){
      return Integer.toString(this.colonne)+","+Integer.toString(this.profondeur);
   }
}