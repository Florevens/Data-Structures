package app;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *  This class provides methods that perform operations on a list of generic objects.
 *  The Objects are assumed to have an appropriate implementation of the equals method.
 */
public class ArrayListServices <T> {

   /**
    * This method takes an ArrayList as a parameter and returns a new ArrayList that 
    * does not contain any duplicate data. For example, if this list was passed in: 
    * [A, B, D, A, A, E, B], the method would return a list containing: [A, B, D, E]. 
    * The ordering of the data does not matter. 
    * Assume that the parameter is not null, but it may be an empty ArrayList, in which case 
    * your method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the 
    * same as the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> deDuplicate(ArrayList<T> inputList){
      ArrayList<T> newList = new ArrayList<T>();
      
      for(int i = 0; i < inputList.size(); i++){
         if(!newList.contains(inputList.get(i))){
            newList.add(inputList.get(i));
         }
      }
      
      return newList;
   }

   /**
    * This method takes two ArrayLists as parameters and returns a new ArrayList that 
    * contains the intersection of the data in the ArrayLists passed in. The intersection 
    * contains the elements that occur in both lists.
    * For example, if these lists were passed in: [A, B, D, A, A, E, B], [B, E, C], the method 
    * would return a list containing: [B, E]. The ordering of the data does not matter. Note that 
    * the result does not contain any duplicates.
    * Assume that the parameters are not null, but one or both may be an empty ArrayList, in which 
    * case your method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the same as 
    * the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> getSetIntersection(ArrayList<T> listA, ArrayList<T> listB){
      ArrayList<T> thisNewList = new ArrayList<T>();
      if(listB.size() >= listA.size()){
         for(int i = 0; i < listB.size(); i++){
            if(listA.contains(listB.get(i)) && !thisNewList.contains(listB.get(i)) && !thisNewList.contains(listB.get(i))){
               thisNewList.add(listB.get(i));
            }
         }
         return thisNewList;
      }
            else if(listA.size() >= listB.size()){
               for( int i = 0; i < listA.size();i++){
                  if(listB.contains(listA.get(i)) && !thisNewList.contains(listA.get(i)) && !thisNewList.contains(listB.get(i))){
                     thisNewList.add(listA.get(i));
                  }

               }
               return thisNewList;
            }
               return thisNewList;

         }
            
         
            
         
      
            
            
          


   /**
    *  This method takes two ArrayLists as parameters and returns a new ArrayList that 
    * contains the set difference of the data in the ArrayLists passed in. The set difference 
    * contains the elements that occur only in one or the other list, but not in both.
    * For example, if these lists were passed in: [A, B, D, A, A, E, B], [B, E, C], the method 
    * would return a list containing: [A, C]. The ordering of the data does not matter. Note 
    * that the result does not contain any duplicates.
    * Assume that the parameters are not null, but one or both may be an empty ArrayList. In the 
    * case where one list is empty, your method returns a new ArrayList that contains all of the 
    * elements on the other list- with no duplicates. In the case where both lists are empty, your 
    * method returns a new, empty ArrayList.
    * Also note that the ArrayList that is returned must be a new ArrayList which is not the same 
    * as the ArrayList passed in. In other words, the parameter must not be changed by your method code.
    */
   public ArrayList<T> getSetDifference(ArrayList<T> listA, ArrayList<T> listB){
      ArrayList<T> thisNewList2 = new ArrayList<T>();
      for(int i = 0; i < listA.size(); i++){
            if(!listB.contains(listA.get(i)) && !thisNewList2.contains(listA.get(i))){
            thisNewList2.add(listA.get(i));
         }
      }
      for(int j = 0; j < listB.size(); j++){
         if(!listA.contains(listB.get(j)) && !thisNewList2.contains(listB.get(j))){
            thisNewList2.add(listB.get(j));
         }
      }
      return thisNewList2;
   }
}


       
      
 
 
            
