package xxgam.oracle.apps.inv.moveorder;

import xxgam.oracle.apps.inv.moveorder.utils.XxGamInvConstantes;

public class Pruebas {
    public Pruebas() {
    }

public static void main(String args[]){
    Long lSoliId = Long.valueOf(0L);
    Long lKitId = Long.valueOf(1L); 
    String cadena = "aa:1";
    String cadena2 = "<";
    //"xXGamInvSolicitudAM.xXGamInvSoliDtlVO1:xXGamInvSoliDtlVO1{1090898}";
    //"xXGamInvSolicitudAM.xXGamInvSoliDtlVO1:xXGamInvSoliDtlVO1{1090988}";    
    int m=0;
    int i;
    int indice = 0;
    int contador = 0;
    boolean  y;
    y = true;
    System.out.println(cadena2);
    
    System.out.println("y: " + y); 
    
    for(i=0;i<cadena.length();i++){
    System.out.println("- " + cadena.substring(m,i+1));
    if(cadena.substring(m,i+1).equals(":")){
        //System.out.println("i: " + i);
        indice = i + 1;
        contador = contador + 1;
    }
    
               //System.out.println(cadena.substring(m,i+1));
               m++;
    }
    System.out.println("contador: " + contador);
    System.out.println("indice: " + indice);       
    System.out.println(cadena.substring(indice,cadena.length() -1));
    System.out.println("getSoliDtl : "  + getSoliDtl(cadena));
    
   /* System.out.println("lSoliId: " + lSoliId);
    System.out.println("lKitId: " + lKitId);
    System.out.println("(long)-1: " + (long)-1);
    System.out.println(" Se presento un error con el PUESTO en la asignaci\u00f3n de KIT. ");
*/


}

    public static String getSoliDtl (String p_cadena){
        int m=0;
        int i;
        int v_indice = 0;
        
        for(i=0;i<p_cadena.length();i++){
            if(p_cadena.substring(m,i+1).equals("{")){
                v_indice = i + 1;
            }
            m++;
        }
        
        return p_cadena.substring(v_indice,p_cadena.length() -1).toString();
    }

   
}
